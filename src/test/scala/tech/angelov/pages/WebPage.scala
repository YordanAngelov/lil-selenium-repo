package tech.angelov.pages

import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, WebDriver}
import org.scalatest.Matchers
import org.scalatest.selenium.WebBrowser
import tech.angelov.util.Driver

trait WebPage extends Page

sealed trait Driver extends WebBrowser with Matchers {

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

  /** Checking if something is present on a page **/
  protected def checkIfShown(args: String*): Unit = {
    val page = pageSource
    args.foreach(x ⇒ page should include(x))
  }

  protected def checkIfRegexShown(args: String*): Unit = {
    val page = pageSource
    args.foreach(x ⇒ page should include regex x)
  }

  protected def checkIfNotShown(args: String*): Unit = {
    val page = pageSource
    args.foreach(x ⇒ page shouldNot include(x))
  }

  protected  def checkIfRegexNotShown(args: String*): Unit = {
    val page = pageSource
    args.foreach(x ⇒ page shouldNot include regex x)
  }

  /** General retrieve text methods **/
  private def getText(by: By): String = driver.findElement(by).getText

  protected  def getTextById(id: String): String = getText(By.id(id))

  protected  def getTextByCss(css: String): String = getText(By.cssSelector(css))

  protected  def getTextByXpath(xpath: String): String = getText(By.xpath(xpath))
}

sealed trait Page extends Driver {

  val relativeUrl: Option[String] = None
  val baseUrl:     Option[String] = None

  /* The header is assumed to be the H1 of the page - change headerBy if that isn't the case */
  val header:      Option[String] = None
  val headerBy:    By             = By.tagName("h1")
  val title:       Option[String] = None

  /** Checking which page the tests are on **/
  /* The quickest way to check which page you are on */
  def on(page: WebPage): Unit = w.until(ExpectedConditions.urlContains(page.relativeUrl.get))

  /* The more robust, but also slower, way of checking the page */
  def definitelyOn(page: WebPage): Unit = {
    on(page)
    assert(driver.getTitle == page.title.get)
    w.until(ExpectedConditions.presenceOfElementLocated(headerBy))
    assert(driver.findElement(headerBy).getText == page.header.get)
  }

}
