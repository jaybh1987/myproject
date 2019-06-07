package DesingPattern.DesignWeatherStation

import scala.collection.mutable.ListBuffer

class WeatherData extends Subject {

  var observers: ListBuffer[Observer] = ListBuffer.empty
  var temp: Double = Double.NaN
  var humidity: Double = Double.NaN
  var pressure: Double = Double.NaN

  override def registerObserver(o: Observer) = observers :+ o

  override def notifyObserver : Unit = {
    println("notification send.")
    for (i <- 0 to observers.length - 1) {
      val obs = observers(i)
      obs.update(temp, humidity, pressure)
    }
  }

  override def removeObserver(o: Observer): Unit = {
    val index = observers.indexOf(o)

    if( index > 0) {
      observers.drop(index)
    }
  }

  def measurementChange(): Unit = {
    notifyObserver
  }

  def setMeasurement(tempreture: Double, humidity: Double, pressure: Double) = {
    this.temp = tempreture
    this.humidity = humidity
    this.pressure = pressure
  }

}

