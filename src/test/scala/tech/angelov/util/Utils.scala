package tech.angelov.util

import java.io.FileInputStream
import java.time.{LocalDateTime, ZoneId}
import java.util.Properties

import scala.collection.JavaConverters._

trait Utils {

  def gm(msg: String): String = {
    val x = new Properties()
    x.load(new FileInputStream("src/test/resources/messages.properties"))
    x.asScala(msg)
      .replace("â", "’") // Have no idea why this happened - used to work okay, but it stopped interpreting ’ properly
  }

  def getDateTime: LocalDateTime = LocalDateTime.now(ZoneId.of("Europe/London"))

}

object Utils {}
