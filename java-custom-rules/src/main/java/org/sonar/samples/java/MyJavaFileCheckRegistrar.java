/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.samples.java;

import java.util.Arrays;

import org.sonar.plugins.java.api.CheckRegistrar;
import org.sonar.plugins.java.api.JavaCheck;
import org.sonar.samples.java.checks.AvoidAnnotationRule;
import org.sonar.samples.java.checks.AvoidBrandInMethodNamesRule;
import org.sonar.samples.java.checks.AvoidMethodDeclarationRule;
import org.sonar.samples.java.checks.AvoidSuperClassRule;
import org.sonar.samples.java.checks.AvoidUnmodifiableListRule;
import org.sonar.samples.java.checks.MyCustomSubscriptionRule;
import org.sonar.samples.java.checks.SecurityAnnotationMandatoryRule;
import org.sonar.samples.java.checks.SpringControllerRequestMappingEntityRule;
import org.sonarsource.api.sonarlint.SonarLintSide;

/**
 * Provide the "checks" (implementations of rules) classes that are gonna be executed during
 * source code analysis.
 *
 * This class is a batch extension by implementing the {@link org.sonar.plugins.java.api.CheckRegistrar} interface.
 */
@SonarLintSide
public class MyJavaFileCheckRegistrar implements CheckRegistrar {

  /**
   * Register the classes that will be used to instantiate checks during analysis.
   */
  @Override
  public void register(RegistrarContext registrarContext) {
    // Call to registerClassesForRepository to associate the classes with the correct repository key
    registrarContext.registerClassesForRepository(MyJavaRulesDefinition.REPOSITORY_KEY, Arrays.asList(checkClasses()), Arrays.asList(testCheckClasses()));
  }

  /**
   * Lists all the checks provided by the plugin
   */
  public static Class<? extends JavaCheck>[] checkClasses() {
    return new Class[] {
      SpringControllerRequestMappingEntityRule.class,
      AvoidAnnotationRule.class,
      AvoidBrandInMethodNamesRule.class,
      AvoidMethodDeclarationRule.class,
      AvoidSuperClassRule.class,
      AvoidUnmodifiableListRule.class,
      MyCustomSubscriptionRule.class,
      SecurityAnnotationMandatoryRule.class
    };
  }

  /**
   * Lists all the test checks provided by the plugin
   */
  public static Class<? extends JavaCheck>[] testCheckClasses() {
    return new Class[] {};
  }
}
