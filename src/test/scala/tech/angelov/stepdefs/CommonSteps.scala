package tech.angelov.stepdefs

import java.io.File
import scala.collection.JavaConverters._

import cucumber.api.Scenario
import org.apache.commons.io.FileUtils
import org.openqa.selenium.logging.LogType
import org.openqa.selenium.{OutputType, TakesScreenshot, WebDriver}

class CommonSteps extends Steps {

  /** Moving the Before and After steps in a different class to avoid running them multiple times */
  //  Before { _ ⇒
  //
  //  }

  private def takeScreenshot(scenario: Scenario, s: String, dr: WebDriver with TakesScreenshot): Unit = {
    val name = scenario.getName
    if (!new java.io.File(s"./target/screenshots/$name$s.png").exists) {
      dr.manage().window().maximize()
      val scr = dr.getScreenshotAs(OutputType.FILE)
      FileUtils.copyFile(scr, new File(s"./target/screenshots/$name$s.png"))
      val byteFile = dr.getScreenshotAs(OutputType.BYTES)
      scenario.embed(byteFile, "image/png")
    }
  }

  After { scenario ⇒
    if (scenario.isFailed) {
      driver match {
        case a: TakesScreenshot ⇒
          takeScreenshot(scenario, "-page-on-failure", a)
          a.navigate().back()
          takeScreenshot(scenario, "-previous-page", a)
        case _ ⇒ throw new Exception("Driver cannot take screenshot.")
      }
    }

    /** You can print out the logs you have accumulated throughout the test here */
//    val logs = driver.manage().logs().get(LogType.BROWSER).asScala.toList
//    println("\n******************************* START OF BROWSER LOGS *******************************")
//    logs.foreach(println)
//    println("*******************************  END OF BROWSER LOGS  *******************************")

    driver.manage().deleteAllCookies()
  }

}
