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

  When("""^I select an option from the dropdown$""") {
    TheInternet.selectDropdown()
  }

  Then("""^that option will be selected$""") {
    TheInternet.checkDropdown()
  }

  When("""^I open a new window$""") {
    TheInternet.openNewWindow()
  }

  Then("""^I will be on the new window$""") {
    TheInternet.switchToNewWindow()
  }

  When("""^I tick checkbox (\d)$""") { (i: Int) ⇒
    TheInternet.tickCheckbox(i)
  }

  When("""^I tick checkbox (\d) if unticked$""") { (i: Int) ⇒
    TheInternet.tickCheckboxIfNotTicked(i)
  }

  When("""^I untick checkbox (\d)$""") { (i: Int) ⇒
    TheInternet.untickCheckboxIfTicked(i)
  }

  When("""^I untick checkbox (\d) if ticked$""") { (i: Int) ⇒
    TheInternet.untickCheckboxIfTicked(i)
  }

  /** I can either:
    * 1) Use this syntax and write my own Cucumber transformer for Either types (good way to work my Scala)
    * OR
    * 2) Change the syntax (waaay more practical)
    * */
//  Then("""^checkbox (\d) will be ticked$|^checkbox (\d) is ticked$""") { (i1: Either[Null, Int], i2: Either[Null, Int]) ⇒
//    if (i1.isLeft) TheInternet.checkCheckboxIsTicked(i2.right.asInstanceOf[Int]) // Need to write my own transformer for this to work
//    else TheInternet.checkCheckboxIsTicked(i1.right.asInstanceOf[Int])
//    (i1, i2) match {                                                // This won't work as you can't use type matching on Null
//      case (i: Int, x: Null) ⇒ TheInternet.checkCheckboxIsTicked(i) //
//      case (_, i) ⇒ TheInternet.checkCheckboxIsTicked(i)            //
//    }
//  }
//
//  Then("""^checkbox (\d) will be unticked$|^checkbox (\d) is unticked$""") { (i1: Either[Null, Int], i2: Either[Null, Int]) ⇒
//    if (i1.isLeft) TheInternet.checkCheckboxIsTicked(i2.right.asInstanceOf[Int])
//    else TheInternet.checkCheckboxIsTicked(i1.right.asInstanceOf[Int])
//    (i1, i2) match {
//      case (i, _) ⇒ TheInternet.checkCheckboxIsUnticked(i)
//      case (_, i) ⇒ TheInternet.checkCheckboxIsUnticked(i)
//    }
//  }

    private def checkIfTicked(i: Int): Unit = {
      TheInternet.checkCheckboxIsTicked(i)
    }

    Then("""^checkbox (\d) will be ticked$""") { (i: Int) ⇒
      checkIfTicked(i)
    }

    Then("""^checkbox (\d) is ticked$""") { (i: Int) ⇒
      checkIfTicked(i)
    }

    private def checkIfNotTicked(i: Int): Unit = {
      TheInternet.checkCheckboxIsUnticked(i)
    }

    Then("""^checkbox (\d) will be unticked$""") { (i: Int) ⇒
      checkIfNotTicked(i)
    }

    Then("""^checkbox (\d) is unticked$""") { (i: Int) ⇒
      checkIfNotTicked(i)
    }

}
