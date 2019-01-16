import sbt.ExclusionRule

name := "lil-selenium-repo"

version := "0.1"

scalaVersion := "2.12.8"

val CucumberVersion = "1.2.5"

libraryDependencies ++= Seq(
  "info.cukes"                   % "cucumber-junit"         % CucumberVersion % "test",
  "info.cukes"                   % "cucumber-picocontainer" % CucumberVersion % "test",
  "info.cukes"                   %% "cucumber-scala"        % CucumberVersion % "test",
  "com.typesafe.play"            %% "play-test"             % "2.7.0-RC9"     % "test",
  "org.scalatest"                %% "scalatest"             % "3.0.4" excludeAll ExclusionRule(organization = "org.seleniumhq.selenium"),
  "org.seleniumhq.selenium"      % "selenium-java"          % "3.7.1"
)