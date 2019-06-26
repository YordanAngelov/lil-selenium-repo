package tech.angelov.util

object TestInfo {

  var store: Map[String, String] = Map.empty

  def addToStore(key: String, value: String): Unit = {
    store += (key → value)
  }

  def resetStore(): Unit = {
    store = Map.empty
  }

}
