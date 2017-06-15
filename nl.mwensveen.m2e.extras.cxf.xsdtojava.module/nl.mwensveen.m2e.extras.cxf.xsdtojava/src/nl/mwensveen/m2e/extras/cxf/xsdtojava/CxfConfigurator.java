/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.cxf.xsdtojava;

import org.apache.maven.plugin.MojoExecution;
import org.eclipse.m2e.core.lifecyclemapping.model.IPluginExecutionMetadata;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.AbstractBuildParticipant;
import org.eclipse.m2e.jdt.AbstractJavaProjectConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CxfConfigurator extends AbstractJavaProjectConfigurator {
    private static final Logger LOG = LoggerFactory.getLogger(CxfConfigurator.class);
    
    @Override
    public AbstractBuildParticipant getBuildParticipant(IMavenProjectFacade projectFacade,
            MojoExecution execution, IPluginExecutionMetadata executionMetadata) {
    	LOG.info("Getting Build Participant");
        return new CxfBuildParticipant(execution);
    }

    @Override
    protected String getOutputFolderParameterName() {
        return "sourceRoot";
    }
}
