package tech.angelov.util

import java.awt.Robot
import java.awt.event.{InputEvent, KeyEvent}

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.scalatest.exceptions.TestFailedException
import tech.angelov.pages.WebPage
import scala.collection.JavaConverters._

object Wave extends WebPage {

  def useWave(): Unit = {

    // To ensure it works consistently, you need to have all other windows minimized on your current display
    def focusMainWindow(r: Robot): Unit = {
      r.mouseMove(200, 200)
      r.mousePress(InputEvent.BUTTON1_MASK)
      r.mouseRelease(InputEvent.BUTTON1_MASK)
      r.delay(250) // Quickest way it works consistently

      val windows = driver.getWindowHandles.asScala.toList
      driver.switchTo().window(windows.head)
    }

    def activateWave(r: Robot): Unit = {
      System.getProperty("os.name") match {
        case "Linux" ⇒
          // WAVE shortcut on Linux is Shift+Ctrl+U
          r.keyPress(KeyEvent.VK_SHIFT)
          r.keyPress(KeyEvent.VK_CONTROL)
          r.keyPress(KeyEvent.VK_U)
          r.delay(10) // Quickest way it works consistently

          r.keyRelease(KeyEvent.VK_SHIFT)
          r.keyRelease(KeyEvent.VK_CONTROL)
          r.keyRelease(KeyEvent.VK_U)
          r.delay(250) // Quickest way it works consistently
      }

      try {
        assert(find(id("wave_sidebar_container")).isDefined)
      } catch {
        case _: TestFailedException ⇒
          println("\nAttempting to activate WAVE again...")
          activateWave(r)
        case e ⇒
          println(s"Exception thrown was: ${e.getMessage}")
      }
    }

    val r = new Robot()

    // You need to click somewhere, before returning focus to the window
    focusMainWindow(r)
    activateWave(r)
  }

  def checkWaveResults(): Unit = {
    val waveFrame = w.until(ExpectedConditions.presenceOfElementLocated(By.id("wave_sidebar_container")))
    driver.switchTo().frame(waveFrame)
    println(s"\nOn page: $currentUrl")
    val errors: String = find(id("error")).get.text
    println(s"Number of errors on the page is: $errors")
    println(s"Number of alerts on the page is: ${find(id("alert")).get.text}")
    assert(errors == "0 Errors")
  }

}
