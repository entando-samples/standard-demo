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
- The keycloak admin UI is available at http://localhost:9080. User admin/admin can be used for testing
- Start the microservice - `ent bundle run sd-banking-ms`. The dev profile uses an in-memory H2 database. It will fail to startup if keycloak is not available
- The MS Swagger UI is available at `http://localhost:8081/banking/`
- Start the MFE of your choice - `ent bundle run sd-cards-react`. Make sure you copy env.local.template to env.local and modify it to match your local settings.

# Banking

This application was generated using JHipster 6.9.0, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v6.9.0](https://www.jhipster.tech/documentation-archive/v6.9.0).

This is a "microservice" application intended to be part of a microservice architecture, please refer to the [Doing microservices with JHipster][] page of the documentation for more information.

This application is configured for Service Discovery and Configuration with . On launch, it will refuse to start if it is not able to connect to keycloak.

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].
