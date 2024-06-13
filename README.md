# Standard Banking Demo
This is the repository for the micro frontends, microservices (or plugins) and other components required to install and run the Entando Standard Banking Demo.

See <https://developer.entando.com/next/tutorials/solution/install-standard-demo.html> for a higher level description of the building blocks used in the Demo and for instructions to install a compiled version of the Demo.

## Bundles
There are currently 4 sub-projects used to set up the Standard Banking Demo. The first three can be installed in any order but all four need to be installed to get the full functionality applied to an Entando Application. See the README files for each bundle for information on building/installing them from source.
1. `sd-banking` 
2. `sd-customer`
3. `sd-manage-users`
4. `sd-content`

## Misc
The `alert-lambda` subproject is not actively maintained right now. This is a Quarkus-based implementation of the alerts/statements microservices. 
