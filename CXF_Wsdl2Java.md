# Introduction #

This connector will help you run the following maven plugin:
```
<groupId>org.apache.cxf</groupId>
<artifactId>cxf-codegen-plugin</artifactId>
<versionRange>[2.0,3.0)</versionRange>
<goal>wsdl2java</goal>
```

# Details #

The connector will tell m2e to run the maven plugin and will add the “sourceRoot” to the classpath.