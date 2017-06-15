/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.java.formatter;

import java.util.Set;

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatterBuildParticipant extends MojoExecutionBuildParticipant {
	private static final Logger LOG = LoggerFactory.getLogger(FormatterBuildParticipant.class);

	public FormatterBuildParticipant(MojoExecution execution) {
		super(execution, true);
		LOG.debug("Created new instance for execution: " + execution);
	}

	@Override
	public Set<IProject> build(int kind, IProgressMonitor monitor) throws Exception {
		// execute mojo
		Set<IProject> result = super.build(kind, monitor);
		return result;
	}

}
