# Introduction #

This connector will help you run the following maven plugin:
```
<groupId>org.apache.cxf</groupId>
<artifactId>cxf-common-xsd</artifactId>
<versionRange>[2.0,3.0)</versionRange>
<goal>xsdtojava</goal>
```

# Details #

The connector will tell m2e to run the maven plugin and will add the “sourceRoot” to the classpath.