/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.cxf.tests;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.project.ResolverConfiguration;
import org.eclipse.m2e.tests.common.AbstractMavenProjectTestCase;

@SuppressWarnings( "restriction" )
public class CXFGenerationTest extends AbstractMavenProjectTestCase {
	public void test_p001_simple() throws Exception {
		ResolverConfiguration configuration = new ResolverConfiguration();
		IProject project1 = importProject("projects/cxf/pom.xml", configuration);
		waitForJobsToComplete();

		project1.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
		project1.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
		waitForJobsToComplete();

		assertNoErrors(project1);

		IJavaProject javaProject1 = JavaCore.create(project1);
		IClasspathEntry[] cp1 = javaProject1.getRawClasspath();
		boolean found = false;

		for (IClasspathEntry iClasspathEntry : cp1) {
			if (iClasspathEntry.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				if (iClasspathEntry.getPath().equals(new Path("/cxf-test-project/target/generated-sources/cxf"))) {
					found = true;
				}
			}
		}
		assertTrue(found);

		assertTrue(project1.getFile("target/generated-sources/cxf/com/example/xsd/ComplexType.java").isSynchronized(IResource.DEPTH_ZERO));
		assertTrue(project1.getFile("target/generated-sources/cxf/com/example/xsd/ComplexType.java").isAccessible());
	}

}
