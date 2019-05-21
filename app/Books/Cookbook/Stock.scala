package Books.Cookbook

case class Stock (symbol: String, company: String)

case class StockInstance(
                        symbol: String,
                        datetime: String,
                        price: BigDecimal,
                        volume: Long
                        )

object NetworkUtils {

  def getUrl(url: String): String = { url }

}

object StockUtils {

  def buildUrl(stockSymbol: String): String = { stockSymbol }
  def getPrice(html: String) = {html.toInt}
  def getVolume(html: String) = { html.toLong}
  def getHigh(html: String) = {html.toLong}

}
