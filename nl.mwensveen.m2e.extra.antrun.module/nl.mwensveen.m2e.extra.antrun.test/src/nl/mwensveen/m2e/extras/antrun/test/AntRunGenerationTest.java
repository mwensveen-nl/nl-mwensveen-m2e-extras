/*
 * Copyright (c) 2012, M. Wensveen (mwensveen.nl)
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package nl.mwensveen.m2e.extras.antrun.test;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.m2e.core.project.ResolverConfiguration;
import org.eclipse.m2e.tests.common.AbstractMavenProjectTestCase;

import com.google.common.io.ByteSource;
import com.google.common.io.Files;
import com.google.common.io.Resources;

@SuppressWarnings("restriction")
public class AntRunGenerationTest extends AbstractMavenProjectTestCase {
	public void test_p001_simple() throws Exception {
		String property = System.getProperty("user.home");

		File tempDir = Files.createTempDir();
		File m2Dir = new File(tempDir, ".m2");
		File outputFile = new File(m2Dir, "nl.mwensveen.m2e.extras.antrun.config.xml");
		assertTrue(outputFile.getParentFile().mkdirs());
		ByteSource asByteSource = Resources.asByteSource(AntRunGenerationTest.class.getClassLoader().getResource("nl.mwensveen.m2e.extras.antrun.config.xml"));
		Files.copy(asByteSource, outputFile);

		System.setProperty("user.home", tempDir.getAbsolutePath());

		ResolverConfiguration configuration = new ResolverConfiguration();
		IProject project1 = importProject("projects/antruntestproj/pom.xml", configuration);
		waitForJobsToComplete();

		project1.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
		project1.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
		waitForJobsToComplete();

		System.setProperty("user.home", property);
		outputFile.delete();
		m2Dir.delete();
		tempDir.delete();
		assertNoErrors(project1);

		// IJavaProject javaProject1 = JavaCore.create(project1);
		// IClasspathEntry[] cp1 = javaProject1.getRawClasspath();

		assertTrue(project1.getLocation().addTrailingSeparator().append("target/index.html").toFile().exists());
		assertTrue(!project1.getLocation().addTrailingSeparator().append("target/index2.html").toFile().exists());
	}

}
