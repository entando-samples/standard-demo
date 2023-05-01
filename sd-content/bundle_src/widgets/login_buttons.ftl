<#assign wp=JspTaglibs["/aps-core"]>
<#if (Session.currentUser !="guest" )>
    <script nonce="<@wp.cspNonce />">
        function logout() {
            const redirectUri = '<@wp.info key="systemParam" paramName="applicationBaseURL" />';
            window.entando.keycloak.logout({redirectUri:redirectUri});
        }

        document.addEventListener('DOMContentLoaded', function() {
            const logoutElement = document.getElementById('logout-button');
            if (logoutElement) {
                logoutElement.addEventListener('click', logout);
            }
        });
    </script>
    <div class="btn-group login-group">
        <button type="button" class="btn login dropdown-toggle px-3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            ${Session.currentUser}
            <span class="sr-only">
                ${Session.currentUser}
            </span>
        </button>
        <div class="dropdown-menu login-drop">
            <@wp.ifauthorized permission="enterBackend"> <a class="dropdown-item" href="/app-builder/">
                    <@wp.i18n key="SD_ESLF_ADMINISTRATION" />
                </a> <a class="dropdown-item" href='<@wp.url page="manage_users" />' >
                <@wp.i18n key="SD_ESLF_MANAGE_USERS" /> </a>
            </@wp.ifauthorized> 
            <a class="dropdown-item" id="logout-button">
                <@wp.i18n key="ESLF_SIGNOUT" /> 
            </a>
            <@wp.pageWithWidget var="editProfilePageVar" widgetTypeCode="userprofile_editCurrentUser" />
            <#if (editProfilePageVar??)><a class="dropdown-item" href="<@wp.url page=" ${editProfilePageVar.code}" />" >
                <@wp.i18n key="ESLF_PROFILE_CONFIGURATION" /></a>
            </#if>
        </div>
    </div>
    <a class="dropdown-item d-none d-sm-block d-md-none" ${Session.currentUser}
        <span class="sr-only">
        ${Session.currentUser}
        </span></a>
<#else>
    <script nonce="<@wp.cspNonce />">
        function login() {
            window.entando.keycloak.login();
        }
        document.addEventListener('DOMContentLoaded', function() {
            const loginElement = document.getElementById('login-button');
            if (loginElement) {
                loginElement.addEventListener('click', login);
            }
        });
    </script>
    <div class="btn-group login-drop"> 
        <button id="login-button" type="button" class="btn login dropdown-toggle mr-3">
            <@wp.i18n key="ESLF_SIGNIN" />
        </button> 
        <a class="btn sign-up " style="line-height: 37px;" href="<@wp.url page="sign_up" />" >
            <@wp.i18n key="SD_SIGN_UP" />
        </a>
    </div>
</#if>