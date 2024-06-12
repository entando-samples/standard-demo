# Deployment and installation

With this configuration, you can use the ent cli (https://dev.entando.org/next/docs/reference/entando-cli.html) to perform the full deployment sequence:

# Deployment and installation

With this configuration, you can use the ent cli (https://dev.entando.org/next/docs/reference/entando-cli.html) to perform the full deployment sequence:

## Prerequisites

1. Docker account
2. attach ent to an Entando platform (e.g. ent attach-kubeconfig config-file)

## Build and publish steps

1. ent bundle pack
2. ent bundle publish
3. ent bundle deploy
4. ent bundle install

See https://developer.entando.com for more information.

# Development tips for local testing

- Start keycloak - `ent bundle svc start keycloak`
- The keycloak admin UI is available at http://localhost:9080.
  - User admin/admin can be used for testing
  - Grant ROLE_ADMIN and realm-management:manage-users permission to the internal client in order to test locally.
- Start the microservice - `ent bundle run sd-customer-ms`. The dev profile uses an in-memory H2 database. It will fail to startup if keycloak is not available
- The MS Swagger UI is available at `http://localhost:8082/customer/swagger-ui.html?urls.primaryName=entando`. Note this MS runs on 8082 in Dev but the standard 8081 in prod. To test the full user-form, both the sd-customer-ms and sd-banking-ms services need to be running.
- Start the MFE - `ent bundle run sd-user-form`. Make sure you copy env.local.template to env.local and modify it to match your local settings.

# Customer

This application was generated using JHipster 6.9.0, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v6.9.0](https://www.jhipster.tech/documentation-archive/v6.9.0).

This is a "microservice" application intended to be part of a microservice architecture, please refer to the [Doing microservices with JHipster][] page of the documentation for more information.

This application is configured for Service Discovery and Configuration with . On launch, it will refuse to start if it is not able to connect to .

## Development

To start your application in the dev profile, run:

    ./mvnw

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].

## Building for production

### Packaging as jar

To build the final jar and optimize the customer application for production, run:

    ./mvnw -Pprod clean verify

To ensure everything worked, run:

    java -jar target/*.jar

Refer to [Using JHipster in production][] for more details.

### Packaging as war

To package your application as a war in order to deploy it to an application server, run:

    ./mvnw -Pprod,war clean verify

## Testing

To launch your application's tests, run:

    ./mvnw verify

For more information, refer to the [Running tests page][].

### Code quality

Sonar is used to analyse code quality. You can start a local Sonar server (accessible on http://localhost:9001) with:

```
docker-compose -f src/main/docker/sonar.yml up -d
```

You can run a Sonar analysis with using the [sonar-scanner](https://docs.sonarqube.org/display/SCAN/Analyzing+with+SonarQube+Scanner) or by using the maven plugin.

Then, run a Sonar analysis:

```
./mvnw -Pprod clean verify sonar:sonar
```

If you need to re-run the Sonar phase, please be sure to specify at least the `initialize` phase since Sonar properties are loaded from the sonar-project.properties file.

```
./mvnw initialize sonar:sonar
```

or

For more information, refer to the [Code quality page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your JHipster development experience. A number of docker-compose configuration are available in the [src/main/docker](src/main/docker) folder to launch required third party services.

For example, to start a postgresql database in a docker container, run:

    docker-compose -f src/main/docker/postgresql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/postgresql.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw -Pprod verify jib:dockerBuild

Then run:

    docker-compose -f src/main/docker/app.yml up -d

For more information refer to [Using Docker and Docker-Compose][], this page also contains information on the docker-compose sub-generator (`jhipster docker-compose`), which is able to generate docker configurations for one or several JHipster applications.

## Continuous Integration (optional)

To configure CI for your project, run the ci-cd sub-generator (`jhipster ci-cd`), this will let you generate configuration files for a number of Continuous Integration systems. Consult the [Setting up Continuous Integration][] page for more information.

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 6.9.0 archive]: https://www.jhipster.tech/documentation-archive/v6.9.0
[doing microservices with jhipster]: https://www.jhipster.tech/documentation-archive/v6.9.0/microservices-architecture/
[using jhipster in development]: https://www.jhipster.tech/documentation-archive/v6.9.0/development/
[using docker and docker-compose]: https://www.jhipster.tech/documentation-archive/v6.9.0/docker-compose
[using jhipster in production]: https://www.jhipster.tech/documentation-archive/v6.9.0/production/
[running tests page]: https://www.jhipster.tech/documentation-archive/v6.9.0/running-tests/
[code quality page]: https://www.jhipster.tech/documentation-archive/v6.9.0/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/documentation-archive/v6.9.0/setting-up-ci/
