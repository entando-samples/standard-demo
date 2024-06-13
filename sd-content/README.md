# Overview
This is the content bundle for the Standard Demo. It contains the "non-code" parts of the Standard Demo including assets, content, simple widgets, pages, etc.

# Deployment and installation

With this configuration, you can use the ent cli (https://developer.entando.com/next/docs/getting-started/entando-cli.html) to perform the full deployment sequence:

## Prerequisites

1. Docker account
2. attach ent to an Entando platform (e.g. ent attach-kubeconfig config-file)

## Build and publish steps

1. ent bundle pack
2. ent bundle publish
3. ent bundle deploy
4. ent bundle install

See https://developer.entando.com for more information.

# Development tips
Bundle IDs are used in a couple ways in this bundle:
- references to CSS and JS files delivered through this bundle, e.g., those resources under `platform/resources/static`. For example, `widgets/brand_insurance_descriptor.yaml` which includes this img path `<@wp.resourceURL />bundles/sd-content-7d1d7104/resources/static/img/insurance-logo.svg`
- references to MFEs/widgets delivered via other bundles. For example, `pages/dashboard-descriptor.yaml` references `sd-seeds-card-react-2f9a1555` to set up that MFE on the page.

Bundle IDs are generated from the URLs of each bundle so, if the Standard Banking Demo code is republished to different URLs, the corresponding IDs in this bundle will need to be updated.
