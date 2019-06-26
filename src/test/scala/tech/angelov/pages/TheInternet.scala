package tech.angelov.pages

import java.io.File
import java.nio.file.{Files, Paths}

import scala.collection.JavaConverters._
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import tech.angelov.util.TestInfo

import scala.annotation.tailrec

// Practice site for test automation: https://the-internet.herokuapp.com/
object TheInternet extends WebPage {

  def goToSection(s: String): Unit =
    clickByLinkText(s)

  /** File Upload */
  lazy val fileName: String = "test.txt"
  lazy val pwd: String      = System.getProperty("user.dir") // Gets current folder path

  def uploadFile(): Unit = {
    inputId("file-upload", pwd + "/src/test/resources/" + fileName) // Uploads the file from the resources folder in the project
    clickById("file-submit")
  }

  def checkFileUpload(): Unit =
    checkIfShown("File Uploaded!", fileName)

  /** File Download */
  lazy val downloadFolder   = "/home/yordan-angelov/Downloads"
  lazy val downloadFileName = "downloadFileName"

  def downloadFile(): Unit = {
    val cssSelector = "div > div > a"
    TestInfo.addToStore(downloadFileName, getTextByCss(cssSelector))
    println(s"Found file: ${TestInfo.store(downloadFileName)}")
    clickByCss(cssSelector) // TODO: Seems to behave differently when ran in headless mode - how to make it work there?
  }

  def checkFileDownload(): Unit = {
    lazy val file = new File(downloadFolder + "/" + TestInfo.store(downloadFileName))

    waitForFile() // There's some delay between downloading it and being able to access it

    //    assert(Files.exists(Paths.get(downloadFolder + "/" + downloadFileName)), "File is not present in expected location") // Either method works
    assert(file.exists, "File is not present in expected location.")
    file.delete() // Clean up after the function

    // TODO Move to Utils and make it applicable to other functions
    @tailrec
    def waitForFile(numOfPolls: Int = 0, pollMs: Int = 50, pollLimit: Int = 200): Option[File] =
      if (file.exists) {
        Some(file)
      } else {
        Thread.sleep(pollMs)
        if (numOfPolls <= pollLimit) waitForFile() else None
      }
  }

  /** Dropdown List */
  def selectDropdown(): Unit = {
    singleSel(id("dropdown")).value = "1"
  }

  def checkDropdown(): Unit =
    assert(singleSel(id("dropdown")).value == "1", "The dropdown did not have the expected value expected.")

  /** Dealing with Multiple Windows */
  def openNewWindow(): Unit =
    clickByLinkText("Click Here")

  def switchToNewWindow(): Unit = {
    val tabs = driver.getWindowHandles.asScala.toList // Gets the current tabs - needs import scala.collection.JavaConverters._ to work

    driver.switchTo().window(tabs(1)) // Switches to the other tab

    checkIfShown("New Window") // Checks the text on the new tab
    driver.close() // Closes that tab

    driver.switchTo().window(tabs.head) // Switches back to first tab - if you don't to it, it'll fail the subsequent tests
  }

}
