package DesingPattern.DesignWeatherStation

import java.util.Observable
import java.util.Observer

class WeatherDataPull extends Observable {

  var temp: Double = Double.NaN
  var humid: Double = Double.NaN
  var pressure: Double = Double.NaN

  /*
  now we will call set change in measurementChange method.
  setChanged defines that there is change in the object,
  and after that we will cal notifyObeservers() methods.
  Notice that there is no parameter with notifyOberservs so,
  we are using pull model. Otherwise nofityObserver need
  subject as an argument as well as data object as sencod argment.
  * */
  def measurementChange() = {
    setChanged()
    notifyObservers()
  }

  def setMeasurement (temp: Double, humid: Double, pressure: Double)= {
    this.temp = temp
    this.humid = humid
    this.pressure = pressure
  }

  /*as it is pull model observers will use this mothods to get states.*/
  def getTemp = temp
  def getHumid = humid
  def getPressure = pressure

}
