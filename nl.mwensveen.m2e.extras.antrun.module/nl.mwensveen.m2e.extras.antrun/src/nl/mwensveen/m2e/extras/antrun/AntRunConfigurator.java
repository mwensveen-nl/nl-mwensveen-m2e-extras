/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.antrun;

import nl.mwensveen.m2e.extras.antrun.config.Config;
import nl.mwensveen.m2e.extras.antrun.config.ConfigUtil;

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.lifecyclemapping.model.IPluginExecutionMetadata;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.AbstractBuildParticipant;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;
import org.eclipse.m2e.jdt.AbstractSourcesGenerationProjectConfigurator;
import org.eclipse.m2e.jdt.IClasspathDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AntRunConfigurator extends AbstractSourcesGenerationProjectConfigurator {
	private static final Logger LOG = LoggerFactory.getLogger(AntRunConfigurator.class);
	private static Config config;

	public AntRunConfigurator() {
		LOG.debug("AndRunConfigurator constructor");
		config = ConfigUtil.getConfig();
	}

	@Override
	public AbstractBuildParticipant getBuildParticipant(IMavenProjectFacade projectFacade, MojoExecution execution, IPluginExecutionMetadata executionMetadata) {
		String groupId = projectFacade.getMavenProject().getGroupId();
		String artifactId = projectFacade.getMavenProject().getArtifactId();
		String id = execution.getExecutionId();

		LOG.debug("Checking execution for " + groupId + ":" + artifactId + " - " + id);
		if (id == null) {
			LOG.warn("Not running AntRun for " + groupId + ":" + artifactId + " because no id");
			return null;
		}
		Boolean execute = ConfigUtil.mustRun(config, groupId, artifactId, id);
		if (!execute) {
			LOG.warn("Not running AntRun for " + groupId + ":" + artifactId + " - " + id);
			return null;
		}
		LOG.debug("Returning Build Participant for " + groupId + ":" + artifactId + " - " + id);
		return new AntRunBuildParticipant(execution);
	}

	/** {@inheritDoc} */
	@Override
	public void configureRawClasspath(ProjectConfigurationRequest request, IClasspathDescriptor classpath, IProgressMonitor monitor) throws CoreException {
		// The plugin does not use a claspath.
		return;
	}

}
