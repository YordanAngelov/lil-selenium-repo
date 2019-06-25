package tech.angelov.stepdefs

import tech.angelov.pages.TheInternet

class TheInternetSteps extends Steps {

 Given("""^I go on the Internet$""") {
    driver.navigate().to("https://the-internet.herokuapp.com/")
  }

  When("""^I go to the (.*) section on the Internet$""") { (s: String) ⇒
    TheInternet.goToSection(s)
  }

  When("""^I upload a file$""") {
    TheInternet.uploadFile()
  }

  Then("""^I will see that the file has been uploaded$""") {
    TheInternet.checkFileUpload()
  }

  When("""^I download a file$""") {
    TheInternet.downloadFile()
  }

  Then("""^I will see that the file has been downloaded$""") {
    TheInternet.checkFileDownload()
  }

}
