/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.cxf.wsdl2java;

import java.io.File;
import java.util.Set;

import org.apache.maven.plugin.MojoExecution;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.MojoExecutionBuildParticipant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.plexus.build.incremental.BuildContext;

public class CxfBuildParticipant extends MojoExecutionBuildParticipant {
	private static final Logger LOG = LoggerFactory.getLogger(CxfBuildParticipant.class);
	private String sourceRoot = null;

	public CxfBuildParticipant(MojoExecution execution) {
		super(execution, true);
		LOG.debug("Created new instance for execution: " + execution);
		Xpp3Dom config = execution.getConfiguration();
		sourceRoot = config.getChild("sourceRoot").getValue();
		LOG.debug("source root: " + sourceRoot);

	}

	@Override
	public Set<IProject> build(int kind, IProgressMonitor monitor) throws Exception {
		IMaven maven = MavenPlugin.getMaven();
		// execute mojo
		Set<IProject> result = super.build(kind, monitor);

		BuildContext buildContext = getBuildContext();
		IMavenProjectFacade mproj = getMavenProjectFacade();

		IProject proj = mproj.getProject();

		proj.refreshLocal(IResource.DEPTH_INFINITE, monitor);
		IFile generatedSource = proj.getFile(sourceRoot);
		File generatedSourceFolder = generatedSource.getFullPath().toFile();
		buildContext.refresh(generatedSourceFolder);
		// tell m2e builder to refresh generated files
		File generated = maven.getMojoParameterValue(getSession(), getMojoExecution(), sourceRoot, File.class);
		if (generated != null) {
			buildContext.refresh(generated);
		}

		return result;
	}
}
