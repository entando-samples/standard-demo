<#assign wp=JspTaglibs["/aps-core"]>
<#-- entando_resource_injection_point -->
<#-- Don't add anything above this line. The build scripts will automatically link the compiled JS and CSS for you and add them above this line so that the widget can be loaded-->

<script nonce="<@wp.cspNonce />">
<!-- TODO: this code needs to be refactored to avoid hardcoded URLs -->
    const keycloakUrl = '/entando-de-app/keycloak.json';
    const serviceUrl = 'http://nshaw.apps.rd.entando.org';
    document.addEventListener("DOMContentLoaded", function () {
        const wrapper = document.getElementById('sd-manage-users');
        fetch(keycloakUrl).then(function (response) {
            return response.json();
        }).then(function (data) {
            console.log(data);
            const mfe = document.createElement('sd-manage-users');
            mfe.setAttribute('realm', data.realm);
            mfe.setAttribute('keycloak-url', data['auth-server-url'].replace('/auth', ''));
            mfe.setAttribute('service-url', serviceUrl);
            mfe.setAttribute('pagination-mode', 'pagination');
            wrapper.appendChild(mfe);
        }).catch(function (error) {
            wrapper.innerHTML("Unable to load widget.");
        });
    });
</script>
<!-- TODO: should be able to just inject the serviceUrl here instead of hardcoding in a DOMContentLoaded event-->
<div id="sd-manage-users"></div>
