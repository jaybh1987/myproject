package DesingPattern.DesignWeatherStation

class CurrentConditionDisplay extends DisplayElement with Observer {

  private var temp: Double = Double.NaN
  private var humidity: Double = Double.NaN
  private var pressure: Double = Double.NaN


  override def display: Unit = {
    println(
      "Current Condition : "+temp+
      "\n F degrees and "+ humidity +" % humidity " +
      "\n pressure = "+pressure
    )
  }

  override def update(temp: Double, humidity: Double, pressure: Double): Unit = {
    this.temp = temp
    this.humidity = humidity
    this.pressure = pressure
    display
  }

}


object CurrentConditionDisplay {

  private var weatherData: Subject = null

  def apply(weatherData: Subject) = {
      this.weatherData = weatherData
  }
}