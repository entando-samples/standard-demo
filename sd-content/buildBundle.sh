#!/bin/bash

function syncFiles() {
    local CPCMD

    rsync --version 1>/dev/null 2>&1
    [[ $? -eq 0 ]] && CPCMD="rsync -a" || CPCMD="cp -ra"

    $CPCMD "$@"
}

echo "- Copying bundle source into the deployable bundle"
syncFiles "bundle_src bundle"

echo "All done! Bundle available in the $(pwd)/bundle folder"

