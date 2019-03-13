This project contains a number of Eclipse M2E project connectors.


Configurators like these are needed to prevent the 'Plugin execution not covered by lifecycle configuration' errors you can get from the Eclipse M2E plugin.

For more information on project connectors see [m2e wiki](https://www.eclipse.org/m2e/documentation/m2e-execution-not-covered.html)

The connectors can be installed in Eclipse by using the update site.
See [here](http://mwensveen-nl.github.io/nl-mwensveen-m2e-extras/)

In the repository there are connectors for:

  * org.apache.cxf:cxf-common-xsd:[2.0,3.0):xsdtojava
  * org.apache.maven.plugins:maven-antrun-plugin:[1.0,2.0):run
  * org.codehaus.mojo:xml-maven-plugin[1.0,2.0):transform
  * org.apache.cxf:cxf-codegen-plugin:[2.0,3.0):wsdl2java
  * com.googlecode.maven-java-formatter-plugin:maven-java-formatter-plugin:[0,1):format

See the [Wiki pages](https://github.com/mwensveen-nl/nl-mwensveen-m2e-extras/wiki) for more information about a specific connector.

This project is licensed under the terms of the [Eclipse Public License - Version 1.0](https://www.eclipse.org/legal/epl-v10.html)
