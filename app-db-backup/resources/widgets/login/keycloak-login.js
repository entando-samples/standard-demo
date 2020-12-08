function login() {
 window.entando.keycloak.login();
 location.href = '<@wp.info key="systemParam" paramName="applicationBaseURL" />do/login?redirectTo=<@wp.url page="homepage"/>';
}

document.addEventListener('DOMContentLoaded', function () {
  document.getElementById('login')
    .addEventListener('click', login-button);
});
