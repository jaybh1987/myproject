package Books.Cookbook

import scala.collection.mutable.ArrayBuffer

class StockPoor (
                  var symbol: String,
                  var company: String,
                  var price: BigDecimal,
                  var volume: Long
                ) {

  var html: String = _
  var high: Int = _
  var low : Int = _

  def buildUrl(stockSymbold: String) :String= { stockSymbold }
  def getUrlContent(url: String): String = { url }

  def setPriceFromHtml(html: String) = {
    this.price = html.toDouble
    price
  }

  def setVolumeFromHtml(html: String) = {
    this.volume = html.toLong
    volume
  }

  def setHighFromHtml(html: String) = {
    this.high =  html.toInt
    high
  }

  def setLowFromHtml(html: String) = {
    this.low = html.toInt
    low
  }

  private val _history: ArrayBuffer[StockPoor] = {
    ArrayBuffer(
      new StockPoor(
      "a",
      "adani",
      1000.11,
      30000),
      new StockPoor(
        "b",
        "badani",
        2000.11,
        32000)
    )
  }

  def getHistory = _history


}
