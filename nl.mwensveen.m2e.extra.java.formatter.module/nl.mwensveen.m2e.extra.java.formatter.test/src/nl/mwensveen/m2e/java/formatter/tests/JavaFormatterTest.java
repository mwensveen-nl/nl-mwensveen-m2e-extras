/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.java.formatter.tests;

import java.io.File;
import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.m2e.core.project.ResolverConfiguration;
import org.eclipse.m2e.tests.common.AbstractMavenProjectTestCase;
import org.junit.Assert;

@SuppressWarnings( "restriction" )
public class JavaFormatterTest extends AbstractMavenProjectTestCase {
	public void test_p001_simple() throws Exception {
		ResolverConfiguration configuration = new ResolverConfiguration();
		IProject project1 = importProject("projects/formatter/pom.xml", configuration);
		waitForJobsToComplete();

		project1.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
		project1.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
		waitForJobsToComplete();

		assertNoErrors(project1);

		IFile file1 = project1.getFile("src/main/java/nl/mwensveen/javaformatter/testproject/Unformatted1.java");
		URI uri1 = file1.getLocationURI();
		File f1 = new File(uri1.getRawPath());
		Assert.assertNotNull(f1);
	}

}
