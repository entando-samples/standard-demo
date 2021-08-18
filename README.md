# Standard Banking Demo
This is the repository for the micro frontends, microservices (or plugins) and other components required to install and run the Entando Standard Banking Demo.

See <https://dev.entando.org/next/tutorials/samples/install-standard-demo.html> for a higher level description of the building blocks used in the Demo.

## Bundles
There are currently 4 sub-projects used to set up the Standard Banking Demo. The first three can be installed in any order but all four need to be installed to get the full functionality applied to an Entando Application. See the README files for each bundle for information on building/installing them.
1. `banking-plugin` 
2. `customer-plugin`
3. `manage-users`
4. `sd-content`

There is also the `alert-demo` subproject which is not actively maintained right now. This is a Quarkus-based implementation of the alerts/statements microservices. 
