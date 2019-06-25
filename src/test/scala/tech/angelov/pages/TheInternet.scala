package tech.angelov.pages

import java.nio.file.{Files, Paths}

import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions

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

  def checkFileDownload(): Unit =
//    assert(Files.exists(Paths.get(downloadFolder + "/" + downloadFileName)), "File is not present in expected location") // Either method works
    assert(new java.io.File(downloadFolder + "/" + downloadFileName).exists, "File is not present in expected location")

}
