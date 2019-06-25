package tech.angelov.pages

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

  def checkFileUpload(): Unit = checkIfShown("File Uploaded!", fileName)

}
