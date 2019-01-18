import sbt.ExclusionRule

sbtVersion := "0.13.15"

name := "lil-selenium-repo"

version := "0.1"

// This seems to be the latest version compatible with Cucumber (as of 18.01.2019)
// https://github.com/cucumber/cucumber-jvm/issues/1087
scalaVersion := "2.11.11"

val CucumberVersion = "1.2.5"

libraryDependencies ++= Seq(
  "info.cukes"                   % "cucumber-junit"         % CucumberVersion % "test",
  "info.cukes"                   % "cucumber-picocontainer" % CucumberVersion % "test",
  "info.cukes"                   %% "cucumber-scala"        % CucumberVersion % "test",
  "com.typesafe.play"            %% "play-test"             % "2.7.0-RC9"     % "test",
  "org.scalatest"                %% "scalatest"             % "3.0.4" excludeAll ExclusionRule(organization = "org.seleniumhq.selenium"),
  "org.seleniumhq.selenium"      % "selenium-java"          % "3.7.1"
)