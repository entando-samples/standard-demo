#!/bin/bash

if [[ "$OSTYPE" == "darwin"* ]]; then
  function sedReplace() {
    sed -i '' "$@"
  }
else
  function sedReplace() {
    sed -i'' "$@"
  }
fi

function syncFiles() {
    local CPCMD

    rsync --version 1>/dev/null 2>&1
    [[ $? -eq 0 ]] && CPCMD="rsync -a" || CPCMD="cp -ra"

    $CPCMD "$@"
}

function syncResources() {
    local widgetFolder="$1"

    echo "> Processing widgets $(echo $widgetFolder | cut -d/ -f3-)"

    echo "- Preparing target folder structure"
    mkdir -p bundle{,"/$widgetFolder"}/resources

    echo "- Copying bundle descriptor"
    syncFiles "$widgetFolder"/bundle/* bundle/"$widgetFolder"/
    if [ -d "$widgetFolder/build/static" ]; then
        echo "- Copying bundle static resources"
        syncFiles "$widgetFolder/build/static" bundle/resources 2>/dev/null
        syncFiles "$widgetFolder/build/static" "bundle/$widgetFolder/resources" 2>/dev/null
    #CUSTOM START - add bundle support for angular, expecting a dist folder with folder matching the widget name
    elif [ -d "$widgetFolder/dist" ]; then
        local widgetName="$(echo $widgetFolder | cut -d/ -f4-)"
        echo "- Copying bundle static resources for angular widget $widgetName"
        rm -rf "bundle/resources/static/$widgetName"
        syncFiles "$widgetFolder/dist/$widgetName" "bundle/resources/static/$widgetName" 2>/dev/null
        syncFiles "$widgetFolder/dist/$widgetName" "bundle/$widgetFolder/resources" 2>/dev/null
    #CUSTOM END
    else
        echo " > no build/static folder found for $widgetFolder"
    fi
}

function createFolderTree() {
    local widgetFolder="$1"

    echo "- Creating folder structure for $widgetFolder"
    mkdir -p bundle/"$widgetFolder"/resources/static/{js,css}

    # Copy bundle metadata and template
    cp "$widgetFolder"/bundle/* bundle/"$widgetFolder"/

    # Copying resources for widgets
    cp -r "$widgetFolder"/build/static/js/*.js bundle/"$widgetFolder"/resources/static/js

    local jsExitStatus=$?
    if [ $jsExitStatus -ne 0 ]; then
        echo " > no js found for $widgetFolder"
    fi

    cp "$widgetFolder"/build/static/css/*.css bundle/"$widgetFolder"/resources/static/css 2>/dev/null

    local cssExitStatus=$?
    if [ $cssExitStatus -ne 0 ]; then
        echo " > no css found for $widgetFolder"
    fi

}

function injectResource() {
    local resource="$1"
    local destFile="$2"
    local injectionPoint="$3"

    local _NL=$'\\\n'
    echo "- Injecting resource $resource in $destFile"
    sedReplace 's|'"$injectionPoint"'|'"$resource$_NL$injectionPoint"'|g' "$destFile"
}

function getServiceUrlFromDockerImage() {
    # Convert a docker image to the ingressPath which is /<organization>/<image-name>/<version> where
    # each field only contains lowercase numbers and letters and "-"

    shopt -s nullglob # Set the results of globs in forloop to emptylist if no file is found
    local dockerImage="$1"

    [ -z "$dockerImage" ] && echo ""
    echo "$dockerImage" | tr : / | sed 's:[^a-zA-Z0-9/]:-:g' | tr "[:upper:]" "[:lower:]" | sed 's:^:/:g'
}

function updateFTLTemplate() {
    shopt -s nullglob # Set the results of globs in forloop to emptylist if no file is found
    local dir="$1"
    local bundleCode="$2"
    local dockerImage="$3"

    widgetName=$(basename "$dir")
    ingressPath=$(getServiceUrlFromDockerImage "$dockerImage")

    echo ""
    echo "> Updating ${widgetName} micro-frontend resources for $dir"

    #CUSTOM START
    # Handle React-style build
    if [ -d "$dir/resources/static" ]; then
        # JS resources
        for jspath in "$dir"/resources/static/js/*.js;
        do
            jsfile=$(basename "$jspath")
            js_resources=${js_resources}"<script src=\"<@wp.resourceURL />${bundleCode}/static/js/${jsfile}\"></script>$_NL"
            #SPECIAL Skip config_ui for the dashboard-card-react since it has a shared MFE for config
            if [ "$widgetName" != "dashboard-card-react" ]; then
                config_ui_resources=${config_ui_resources}"    - ${bundleCode}/static/js/${jsfile}$_NL"
            fi
        done

        # CSS resources
        for csspath in "$dir"/resources/static/css/*.css;
        do
            cssfile=$(basename "$csspath")
            css_resources=${css_resources}"<link href=\"<@wp.resourceURL />${bundleCode}/static/css/${cssfile}\" rel=\"stylesheet\">$_NL"
        done
    # Handle Angular-style build
    elif [ -d "$dir/resources/$widgetName" ]; then
        # JS resources
        for jspath in "$dir"/resources/"$widgetName"/*.js;
        do
            jsfile=$(basename "$jspath")
            js_resources=${js_resources}"<script src=\"<@wp.resourceURL />${bundleCode}/static/${widgetName}/${jsfile}\"></script>$_NL"
        done

        # CSS resources
        for csspath in "$dir"/resources/"$widgetName"/*.css;
        do
            cssfile=$(basename "$csspath")
            css_resources=${css_resources}"<link href=\"<@wp.resourceURL />${bundleCode}/static/${widgetName}/${cssfile}\" rel=\"stylesheet\">$_NL"
        done
    else
        echo " > No build files found for $dir"
    fi

    #SPECIAL START Set the configUI using the dashboard-card-config app
    if [ "$widgetName" == "dashboard-card-angular" ] || [ "$widgetName" == "dashboard-card-react" ]; then
        echo "Adding config UI resources for $widgetName"
        # JS resources - use the full path since the temporary bundle resources are already removed
        for jspath in ui/widgets/banking-widgets/dashboard-card-config/build/static/js/*.js;
        do
            jsfile=$(basename "$jspath")
            config_ui_resources=${config_ui_resources}"    - ${bundleCode}/static/js/${jsfile}$_NL"
        done
    fi
    #SPECIAL END

    # Inject resources on ftl files
    echo "- Injecting resources for FTL files"
    for ftlName in "$dir"/*.ftl;
    do
        [ -e "$ftlName" ] || continue

        if [ -n "$ingressPath" ]; then
            # Replace the service path with the correct ingressPath
            sedReplace "s|service-url=\".*\"|service-url=\"$ingressPath\"|g" "$ftlName"
        fi

        injectResource "$js_resources" "$ftlName" "$INJECTION_POINT"
        injectResource "$css_resources" "$ftlName" "$INJECTION_POINT"
    done

    # Inject resources on descriptor files
    echo "- Injecting resources for config UI"
    for descriptorName in "$dir"/*.yaml;
    do
        injectResource "$config_ui_resources" "$descriptorName" "$INJECTION_POINT_CONFIG"
    done

    #CUSTOM END

    #Cleanup the resources that were copied into the widget folders specifically. They are now copied into the main bundle folder
    echo ""
    echo "> Cleaning temporary resource folders - $dir/resources"
    rm -rvf "$dir/resources"
    shopt -u nullglob
}

export -f sedReplace
export -f syncFiles
export -f createFolderTree
export -f injectResource
export -f updateFTLTemplate
export -f syncResources
export -f getServiceUrlFromDockerImage
export INJECTION_POINT="<#-- entando_resource_injection_point -->"
#CUSTOM START
export INJECTION_POINT_CONFIG="# entando_resource_injection_point"
export _NL=$'\\\n'
#CUSTOM END

BUNDLE_NAME=$(awk -F': ' '/^code/{print $2}' ./bundle/descriptor.yaml)
#CUSTOM START - no plugins
#DOCKER_IMAGE=$(awk -F': ' '/^image/{print $2}' ./bundle/plugins/*-plugin.yaml | head -1)
#CUSTOM END
WIDGET_FOLDER="ui/widgets"

#CUSTOM START - start by copying over the bundle_src. This isn't included in the standard blueprint but allows the source to
# be applied each time the frontend is built
BUNDLE_SRC="bundle_src"
echo "---"
echo "Copying the bundle_source into the bundle dir"
mkdir -p bundle
cp -r ${BUNDLE_SRC}/* bundle/
#CUSTOM END

find "$WIDGET_FOLDER" -maxdepth 2 -mindepth 2 -type d -not -path "*utils*" > /dev/null 2>&1
HAS_WIDGETS=$?

if [ $HAS_WIDGETS -eq 0 ]; then
    # This command assumes that the widgets are all under ui/widgets/<entity>/<widget>. The command finds all of the micro-frontends in those folders and
    # copies the result of the build into the bundle resources folder so that the bundle can be deployed to a digital exchange instance (or imported on an existing page).
    # The command also copies css optionally with this structure since some widgets will be js only 2>/dev/null || :
    echo "---"
    echo "Generating the bundle folder tree for the micro-frontends"
    echo ""
    find "$WIDGET_FOLDER" -maxdepth 2 -mindepth 2 -type d -not -path "*utils*" -exec bash -c 'syncResources "$@"' bash {} \;
    mkdir -p bundle/resources/static/{js,css}
    echo ""

    # #Fetch the top level service name from the pom and use this as the context directory for the publishing of assets specific to the project when building the bundle
    # artifactId=$(awk -F'[><]' '/<artifactId>.*<\/artifactId>/ {print $3; exit}' pom.xml)
    # echo "---"
    # echo "Updating bundle for service ${artifactId}"

    # For each widget under the structure ui/widgets/<entity>/<widget> generate an FTL file that imports the css and js that goes with that widget.
    # The FTL file from the widget itself is preserved and the imports are added at the top of the widget
    echo "---"
    echo "Updating micro-frontend templates to include static resources"
    echo ""
    find bundle/ui/widgets -maxdepth 2 -mindepth 2 -type d -not -path "*utils*" -exec bash -c 'updateFTLTemplate "$@"' bash {} "$BUNDLE_NAME" "$DOCKER_IMAGE" \;

    echo ""
else
    echo "No micro-frontend has been found in the $WIDGET_FOLDER, skipping this step"
fi
