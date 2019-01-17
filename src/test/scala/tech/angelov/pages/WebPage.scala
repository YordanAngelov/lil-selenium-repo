package tech.angelov.pages

import org.openqa.selenium.{By, WebDriver}
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.selenium.WebBrowser
import tech.angelov.util.Driver

trait WebPage extends Driver

sealed trait Driver extends WebBrowser {

  /** Instantiates the driver and the implicit wait **/
  implicit val driver: WebDriver = Driver.getInstance()
  implicit val w: WebDriverWait = new WebDriverWait(driver, 2)

  /** Input methods **/
  private def input(by: By, value: String): Unit = {
    driver.findElement(by).clear()
    driver.findElement(by).sendKeys(value)
  }

  /** Can be expanded to include more if necessary **/
  protected def inputId(id: String, value: String): Unit = input(By.id(id), value)

  protected def inputXpath(xpath: String, value: String): Unit = input(By.xpath(xpath), value)

  protected def inputCss(css: String, value: String): Unit = input(By.cssSelector(css), value)

  /** Generic click methods **/
  private def clickBy(by: By): Unit = {
    w.until(ExpectedConditions.presenceOfElementLocated(by))
    driver.findElement(by).click()
  }

  /** Can be expanded to include more if necessary **/
  protected def clickById(id: String): Unit = clickBy(By.id(id))

  protected def clickByCss(css: String): Unit = clickBy(By.cssSelector(css))

  protected def clickByXpath(xpath: String): Unit = clickBy(By.xpath(xpath))

  protected def clickByLinkText(link: String): Unit = clickBy(By.linkText(link))

}
