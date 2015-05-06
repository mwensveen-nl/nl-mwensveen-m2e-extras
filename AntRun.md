# Introduction #

This connector will help you run the following maven plugin:
```
<groupId>org.apache.maven.plugins</groupId>
<artifactId>maven-antrun-plugin</artifactId>
<versionRange>[1.0,2.0)</versionRange>
<goal>run</goal>
```

# Details #
The default behavior of this connector is to not let M2E run the maven-ant-run plugin inside Eclipse. But this behavior can be overriden by creating a configuration file inside the .m2 folder in the users home folder.

Example:
A part of your pom.xml defines the maven-antrun-plugin (like below). In the configuration file you can define which execution to run.

Pom example:
```
<project>
...
   <build>
      <plugins>
         <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-antrun-plugin</artifactId>
            <version>1.7</version>
            <executions>
               <execution>
                  <id>test-one</id>
                  <phase>generate-resources</phase>
                  <goals>
                     <goal>run</goal>
                  </goals>
                  <configuration>
                     <target>
                        <echo message="This Ant script is used for testing the AntRun M2E Connector." />
                        <echo message="Performing task: copying index.html." />
                        <copy file="${basedir}/src/main/otherresources/index.html" tofile="${basedir}/target/classes/index.html" overwrite="true"/>
                        <echo message="Done with all tasks." />
                     </target>
                  </configuration>
               </execution>
               <execution>
                  <id>test-two</id>
                  <phase>generate-resources</phase>
                  <goals>
                     <goal>run</goal>
                  </goals>
                  <configuration>
                     <target>
                        <echo message="This Ant script is used for testing the AntRun M2E Connector." />
                        <echo message="Performing task: copying index.html." />
                        <copy file="${basedir}/src/main/otherresources/index2.html" tofile="${basedir}/target/classes/index2.html" overwrite="true"/>
                        <echo message="Done with all tasks." />
                     </target>
                  </configuration>
               </execution>
            </executions>
         </plugin>
...
</project>
```

configuration file: /home/someuser/.m2/nl.mwensveen.m2e.extras.antrun.config.xml

```
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<ns2:antrunconfig xmlns:ns2="nl.mwensveen.m2e.extras.antrun">
	<config-items>
		<config-item>
			<groupid>nl.mwensveen</groupid>
			<artifactid>antrun-test-project</artifactid>
			<executionid>test-one</executionid>
			<run>true</run>
		</config-item>
		<config-item>
			<groupid>nl.mwensveen</groupid>
			<artifactid>antrun-test-project</artifactid>
			<executionid>test-two</executionid>
			<run>false</run>
		</config-item>
	</config-items>
</ns2:antrunconfig>
```

This will run the 'test-one' execution in Eclipse and will not run the 'test-two' execution. You can also remove the config-item for 'test-two' as the default is to not run an execution.

Note that you can only define executions to run if you assign an  execution id in the pom.