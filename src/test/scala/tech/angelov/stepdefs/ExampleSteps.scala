package tech.angelov.stepdefs

class ExampleSteps extends Steps {

  Given("""^I go on Google$""") {
    driver.navigate().to("https://www.google.com/")
  }

  When("""^I search for cats$""") {
    inputCss("div > input[aria-label='Search']", "cats")
    clickByCss("input[value='Google Search']")
  }

  Then("""^I will see results showing websites of cats$""") {
    checkIfShown("cats")
  }

}
