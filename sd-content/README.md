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
Bundle IDs are generated from the URLs of each bundle so, if the Standard Banking Demo code is republished to different URLs, the corresponding IDs in this bundle will need to be updated. Examples:
- sd-banking-bundle
  - `pages/dashboard-descriptor.yaml` references `sd-seeds-card-react-ceb320f3` and others
- sd-customer-bundle
  - `pages/sign_up-descriptor.yaml` references `sd-user-form-a38a6b50`
- sd-manage-users-bundle
  - `pages/manage_users-descriptor.yaml` references `sd-manage-users-953ade37`
- sd-content
  - `pages/insurance_inclusions-descriptor.yaml` and other pages reference `sd-content-bundle-20d9c12f` for CSS and JS files
 
