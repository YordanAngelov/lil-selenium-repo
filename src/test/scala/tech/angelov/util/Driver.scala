package tech.angelov.util

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.remote.DesiredCapabilities

object Driver {

  var instance: WebDriver = _
  lazy val driverType: String = Option(System.getProperty("browser")).getOrElse("headless")

  def getInstance(): WebDriver = {
    if(instance == null) instance = driverType match {
      case "chrome"   ⇒ createChromeDriver(headless = false)
      case "headless" ⇒ createChromeDriver(headless = true)
    }
    instance
  }

  /** If needed, can expand to include other browsers **/
  def createChromeDriver(headless: Boolean): WebDriver = {
    val capabilities = DesiredCapabilities.chrome()
    val options = new ChromeOptions()

    options.addArguments("test-type")
    options.addArguments("--disable-gpu")
    options.addArguments("start-maximized")
    if (headless) options.addArguments("--headless")
    capabilities.setJavascriptEnabled(true)
    capabilities.setCapability(ChromeOptions.CAPABILITY, options)

    new ChromeDriver(capabilities)
  }
}
