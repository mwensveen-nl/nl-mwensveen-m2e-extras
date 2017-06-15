/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.xml.transform;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.MojoExecution;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2e.core.lifecyclemapping.model.IPluginExecutionMetadata;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.AbstractBuildParticipant;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;
import org.eclipse.m2e.jdt.AbstractJavaProjectConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlConfigurator extends AbstractJavaProjectConfigurator {
	private static final Logger LOG = LoggerFactory.getLogger(XmlConfigurator.class);
	@Override
	public AbstractBuildParticipant getBuildParticipant(IMavenProjectFacade projectFacade, MojoExecution execution,
			IPluginExecutionMetadata executionMetadata) {
		LOG.info("Getting Build Participant");
		List<String> allOutputDirs = retrieveOutputDirs(execution);
		
		XmlBuildParticipant participant = new XmlBuildParticipant(execution, allOutputDirs);
		return participant;
	}

	private List<String> retrieveOutputDirs(MojoExecution execution) {
		List<String> outputDirs = new ArrayList<String>();
		Xpp3Dom config = execution.getConfiguration();
		if (config != null) {
			Xpp3Dom transformations = config.getChild("transformationSets");
			if (transformations != null) {
				Xpp3Dom[] transformationArray = transformations.getChildren("transformationSet");
				if (transformationArray != null) {
					for (Xpp3Dom transformation : transformationArray) {
						boolean add = getBooleanChild(transformation, "addedToClasspath", true);
						if (add) {
							String outputDir = getStringChild(transformation, "outputDir", "target/generated-resources/xml/xslt");
							LOG.debug("OutputDir to add to the Classpath: " + outputDir);
							outputDirs.add(outputDir);
						}
					}
				}
			}
		}
		return outputDirs;
	}

	private String getStringChild(Xpp3Dom transformation, String name, String defaultValue) {
		Xpp3Dom child = transformation.getChild(name);
		if (child != null) {
			String value = child.getValue();
			if (value != null && !value.trim().equals("")) {
				return value;
			}
		}
		return defaultValue;
	}

	private boolean getBooleanChild(Xpp3Dom transformation, String name, boolean defaultValue) {
		Xpp3Dom child = transformation.getChild(name);
		if (child != null) {
			String value = child.getValue();
			if (value != null && !value.trim().equals("")) {
				return Boolean.valueOf(value);
			}
		}
		return defaultValue;
	}

	/** {@inheritDoc} */
	@Override
	protected File[] getSourceFolders(ProjectConfigurationRequest request, MojoExecution mojoExecution) throws CoreException {
		List<String> outputDirs = retrieveOutputDirs(mojoExecution);
		LOG.debug("Found outputdir to use as sourcefolder: " + outputDirs.size());
		File[] files = new File[outputDirs.size()];
		for (int i = 0; i < outputDirs.size(); i++) {
			String dirName = outputDirs.get(i);
			files[i] = new File(dirName);
			LOG.debug(i + ": " + files[i]);
		}
		return files;
	}

}
