# Details Widget

## Running locally

Widgets use Keycloak for authentication. To use widget locally follow these steps (where prompted for port, use defaults):

1. Download and install Keycloak standalone server (this widget has been tested with Keycloak 7.0.1) from https://www.keycloak.org/downloads.html
1. Go to <KEYCLOAK_SERVER_FOLDER>/bin and run `./standalone.sh -Djboss.socket.binding.port-offset=1000` - this will offset the Keycloak port to 9080 (which is a default port JHipster uses)
1. Setup Keycloak by visiting http://localhost:9080/auth/ (administrator login `admin/admin`)
    1. Create realm named `jhipster`
    1. Add client with
        - name - `jhipster-entando-react-client`
        - client protocol - `openid-connect`
        - root URL - `http://localhost:3000/`
        - valid redirect URIs - `http://localhost:3000/*`
    1. Add user with all roles assigned
1. Create microservice application, add entity (this should create the widgets) and start the application.
1. If needed (should be automated in future releases), update SecurityConfiguration.java file (<MICROSERVICE_FOLDER>/src/main/java/<PACKAGE_NAME_FOLDERS>/config/SecurityConfiguration.java) to allow OPTIONS requests by adding:
    1. `import org.springframework.http.HttpMethod;` at the top
    1. `.antMatchers(HttpMethod.OPTIONS,"/api/**").permitAll()` after `.antMatchers("/api/auth-info").permitAll()`
1. If needed (should be automated in future releases), uncomment CORS configurations at `<MICROSERVICE_FOLDER>/src/main/resources/config/application-dev.yml` and/or `<MICROSERVICE_FOLDER>/src/main/resources/config/application.yml`
1. Authenticate at http://localhost:9080/auth/realms/jhipster/account/ using the user you have created.
1. Go to widget source (<MICROSERVICE_FOLDER>/ui/widgets/seedscard/detailsWidget/>):
    1. Run `npm i`
    1. Add `.env` file with `REACT_APP_DOMAIN=http://localhost:<MICROSERICE_PORT=8081>/services/<MICROSERVICE_APPLICATION_NAME>/api`
    1. Run `npm start`

## API

### Attributes

-   **locale** (default: `en`)
-   **id**

### Environment variables

There are several environment variables used in the widget that provide initial configuration of the widget. To set that up, create `.env` file in the root folder following constants:

-   REACT_APP_DOMAIN - API endpoint for data (e.g., http://localhost:8081/services/jhipster/api)

## i18n

To add a new locale:

-   add a new locale file under `src/i18n/locales/[newLocaleName].json`
-   add the created locale file to `src/i18n/locales/index.js`
-   locale file should contain `translation` as its root object

## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.<br>

Open [http://localhost:3000](http://localhost:3000) to view it in the browser.

The page will reload if you make edits.<br>

You will also see any lint errors in the console.

### `npm test`

Launches the test runner in the interactive watch mode.<br>

See the section about [running tests](https://facebook.github.io/create-react-app/docs/running-tests) for more information.

### `npm run build`

Builds the app for production to the `build` folder.<br>

It correctly bundles React in production mode and optimizes the build for the best performance.

The build is minified and the filenames include the hashes.<br>

Your app is ready to be deployed!

See the section about [deployment](https://facebook.github.io/create-react-app/docs/deployment) for more information.

### `npm run lint`

Runs Eslint to the root folder and fix all the issues that can be handled automatically.

## Linter

This project is extending the [Airbnb Style Guide](https://github.com/airbnb/javascript) eslint rules, and applying custom rules (see package.json) to improve code quality.

## Folder structure

-   ./src
    -   ./api `--> api calls, grouped by feature: the structure should mimic the api call itself`
    -   ./components
        -   ./\_\_tests\_\_ `sample test folder`
            -   App.test.js `--> this way test files are closer to other ones, but in a separate folder in order to keep the folder structure cleaner`
        -   ./\_mocks `--> mock data for tests`
        -   ./\_types `--> PropTypes for components`
        -   ./common `--> folder containing common components`
            -   CommonComponent.js
        -   ./App `--> example of component`
            -   App.css
            -   App.js `--> keep the same name as component folder so we can find it easily when doing a file search`
            -   AppContainer.js `--> container for the App component, adds state`
        -   ./RootComponent.js `--> parent presentational component that is wraps all other components`
        -   ./RootComponentContainer.js `--> parent container component that is wraps parental presentational component`
    -   ./state `--> application state (e.g. redux), if any`
        -   ./sample-feature `--> grouping by feature`
            -   sample-feature.actions.js
            -   sample-feature.reducer.js
            -   sample-feature.selectors.js
            -   sample-feature.types.js
        -   store.js `--> configure redux store`
    -   index.js `--> entry point`
    -   index.css `--> global styling rules`
