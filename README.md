This project contains a number of Eclipse M2E project connectors.


Configurators like these are needed to prevent the 'Plugin execution not covered by lifecycle configuration' errors you can get from the Eclipse M2E plugin.

For more information on project connectors see http://wiki.eclipse.org/M2E_plugin_execution_not_covered

The connectors can be installed in Eclipse (Luna) by adding a new repository to the available update sides (help/Install New Software/Add).
Create a repository:
  * Name: nl-mwensveen-m2e-extras
  * Location: http://nl-mwensveen-m2e-extras.googlecode.com/git/p2/2.0.0

Other versions:
  * Eclipse Juno with M2E 1.3 use location: http://nl-mwensveen-m2e-extras.googlecode.com/git/p2/0.8.0
  * Eclipse Juno with M2E 1.4 use location: http://nl-mwensveen-m2e-extras.googlecode.com/git/p2/0.9.0
  * Eclipse Kepler with M2E 1.4 use location: http://nl-mwensveen-m2e-extras.googlecode.com/git/p2/1.0.0

In the repository there are connectors for:

  * org.apache.cxf:cxf-common-xsd:[2.0,3.0):xsdtojava
  * org.apache.maven.plugins:maven-antrun-plugin:[1.0,2.0):run
  * org.codehaus.mojo:xml-maven-plugin[1.0,2.0):transform
  * org.apache.cxf:cxf-codegen-plugin:[2.0,3.0):wsdl2java
  * com.googlecode.maven-java-formatter-plugin:maven-java-formatter-plugin:[0,1):format

See the Wiki pages for more information about a specific connector.