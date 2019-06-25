package tech.angelov.pages

import java.io.File
import java.nio.file.{Files, Paths}

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

import scala.annotation.tailrec

// Practice site for test automation: https://the-internet.herokuapp.com/
object TheInternet extends WebPage {

  def goToSection(s: String): Unit =
    clickByLinkText(s)

  /** File Upload */
  lazy val fileName: String = "test.txt"
  lazy val pwd: String      = System.getProperty("user.dir") // Gets current folder path

  def uploadFile(): Unit = {
    inputId("file-upload", pwd + "/src/test/resources/" + fileName)
    clickById("file-submit")
  }

  def checkFileUpload(): Unit =
    checkIfShown("File Uploaded!", fileName)

  /** File Download */
  lazy val downloadFolder   = "/home/yordan-angelov/Downloads"
  lazy val downloadFileName = "Cheque Dummy.jpg"

  def downloadFile(): Unit =
    w.until(ExpectedConditions.elementToBeClickable(By.linkText(downloadFileName))).click()

  def checkFileDownload(): Unit = {
    lazy val file = new File(downloadFolder + "/" + downloadFileName)

    waitForFile() // There's some delay between downloading it and being able to access it

    //    assert(Files.exists(Paths.get(downloadFolder + "/" + downloadFileName)), "File is not present in expected location") // Either method works
    assert(file.exists, "File is not present in expected location.")
    file.delete() // Clean up after the function

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

}
