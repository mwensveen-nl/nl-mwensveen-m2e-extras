/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.java.formatter;

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.lifecyclemapping.model.IPluginExecutionMetadata;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.AbstractBuildParticipant;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;
import org.eclipse.m2e.jdt.AbstractJavaProjectConfigurator;
import org.eclipse.m2e.jdt.IClasspathDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatterConfigurator extends AbstractJavaProjectConfigurator {
	private static final Logger LOG = LoggerFactory.getLogger(FormatterConfigurator.class);
	@Override
	public AbstractBuildParticipant getBuildParticipant(IMavenProjectFacade projectFacade, MojoExecution execution,
			IPluginExecutionMetadata executionMetadata) {
		LOG.info("Getting Build Participant");
		return null;
	}
	
	/** {@inheritDoc} */
	@Override
	public void configureRawClasspath(ProjectConfigurationRequest request, IClasspathDescriptor classpath, IProgressMonitor monitor)
			throws CoreException {
		// The plugin does not use a claspath.
		return;
	}

}
