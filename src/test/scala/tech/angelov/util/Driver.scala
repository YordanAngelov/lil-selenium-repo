package tech.angelov.util

import java.util.logging.Level

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.openqa.selenium.logging.{LogType, LoggingPreferences}
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

    /** You can add logging using the below */
//    val logPrefs = new LoggingPreferences()
//    logPrefs.enable(LogType.BROWSER, Level.INFO)
//    logPrefs.enable(LogType.CLIENT, Level.INFO)
//    capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs)

    /** To add logging of the network tab:
      * https://stackoverflow.com/questions/53049026/selenium-chrome-performance-logs-not-working
      * For anyone coming to this recently - in recent Selenium and ChromeDriver versions (eg 3.14.159, chrome driver 76.x) setting up loggingPrefs with ChromeDriver doesn't seem to work, using the local ChromeDriver - no matter what you do you seem to get the log type 'performance' not found error
      * The reason is increasingly strict w3c spec compliance, both in Selenium and ChromeDriver code. */
//    val perfLogPrefs = new LoggingPreferences()
//    perfLogPrefs.enable(LogType.PERFORMANCE, Level.SEVERE)
//    capabilities.setCapability("goog:loggingPrefs", perfLogPrefs)

    new ChromeDriver(capabilities)
  }
}
