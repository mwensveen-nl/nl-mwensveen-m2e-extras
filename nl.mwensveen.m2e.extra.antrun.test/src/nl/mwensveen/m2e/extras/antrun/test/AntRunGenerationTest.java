/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.antrun.test;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.m2e.core.project.ResolverConfiguration;
import org.eclipse.m2e.tests.common.AbstractMavenProjectTestCase;

@SuppressWarnings( "restriction" )
public class AntRunGenerationTest extends AbstractMavenProjectTestCase {
	public void test_p001_simple() throws Exception {
		ResolverConfiguration configuration = new ResolverConfiguration();
		IProject project1 = importProject("projects/antruntestproj/pom.xml", configuration);
		waitForJobsToComplete();

		project1.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
		project1.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
		waitForJobsToComplete();
		assertNoErrors(project1);

		// IJavaProject javaProject1 = JavaCore.create(project1);
		// IClasspathEntry[] cp1 = javaProject1.getRawClasspath();

		// assertTrue(project1.getFile("target/classes/index.html").exists());
		assertTrue(!project1.getFile("target/classes/index2.html").exists());
	}

}
