package DesingPattern.DesignWeatherStation
import java.util.Observable
import java.util.Observer

class CurrentConditionDisplayPull {

  var temp: Double = Double.NaN
  var humid: Double = Double.NaN
  var pressure: Double = Double.NaN

  def update(obs: Observable, arg: Object) = {

    if(obs.isInstanceOf[WeatherDataPull]) {

      var weatherDataPull: WeatherDataPull = obs.asInstanceOf[WeatherDataPull]
      this.temp = weatherDataPull.getTemp
      this.humid = weatherDataPull.getHumid
      this.pressure = pressure

      display
    }
  }

  def display = println(s"tempreture = "+temp+"\n "+"humid = "+humid)

}

object CurrentConditionDisplayPull {

  var observable: Observable = null

  def apply(observable: Observable) = {
    this.observable = observable
  }
}