package tech.angelov.stepdefs

import cucumber.api.scala.{EN, ScalaDsl}
import tech.angelov.pages.WebPage

trait Steps
  extends ScalaDsl
    with WebPage
    with EN {

  //  Before { _ ⇒
  //
  //  }

  After { scenario ⇒
//    if (scenario.isFailed) {} //Can include something for troubleshooting.
    driver.manage().deleteAllCookies()
  }

}
