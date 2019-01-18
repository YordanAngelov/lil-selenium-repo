package tech.angelov.util

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.remote.DesiredCapabilities

object Driver {

  def getInstance(): WebDriver = createChromeDriver()

  /** If needed, can expand to include other browsers **/
  def createChromeDriver(): WebDriver = {
    val capabilities = DesiredCapabilities.chrome()
    val options = new ChromeOptions()

    options.addArguments("test-type")
    options.addArguments("--disable-gpu")
    options.addArguments("start-maximized")
    capabilities.setJavascriptEnabled(true)
    capabilities.setCapability(ChromeOptions.CAPABILITY, options)

    new ChromeDriver(capabilities)
  }
}
