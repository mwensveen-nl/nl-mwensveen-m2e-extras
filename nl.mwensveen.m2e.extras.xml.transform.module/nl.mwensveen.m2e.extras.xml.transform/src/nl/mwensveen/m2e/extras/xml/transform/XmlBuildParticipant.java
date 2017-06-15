/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.xml.transform;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.apache.maven.plugin.MojoExecution;
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

public class XmlBuildParticipant extends MojoExecutionBuildParticipant {
	private static final Logger LOG = LoggerFactory.getLogger(XmlBuildParticipant.class);
	private List<String> outputDirs;

	public XmlBuildParticipant(MojoExecution execution, List<String> outputDirs) {
		super(execution, true);
		this.outputDirs = outputDirs;
		LOG.debug("Created new instance for execution: " + execution);
	}

	@Override
	public Set<IProject> build(int kind, IProgressMonitor monitor) throws Exception {
		// execute mojo
		Set<IProject> result = super.build(kind, monitor);
		if (true) {
			IMaven maven = MavenPlugin.getMaven();
			BuildContext buildContext = getBuildContext();
			IMavenProjectFacade mproj = getMavenProjectFacade();

			IProject proj = mproj.getProject();

			proj.refreshLocal(IResource.DEPTH_INFINITE, monitor);

			for (String dirName : outputDirs) {
				IFile generatedSource = proj.getFile(dirName);
				File generatedSourceFolder = generatedSource.getFullPath().toFile();
				buildContext.refresh(generatedSourceFolder);
				// tell m2e builder to refresh generated files
				File generated = maven.getMojoParameterValue(getSession(), getMojoExecution(), dirName, File.class);
				if (generated != null) {
					buildContext.refresh(generated);
				}
			}
		}
		return result;
	}

}
