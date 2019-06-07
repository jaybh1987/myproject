package DesingPattern.DesignWeatherStation

class WeatherStation extends App{

  override def main(args: Array[String]) = {

    val weatherData = new WeatherData

    val currentConditionDisplay = CurrentConditionDisplay(weatherData)

    weatherData.setMeasurement(10, 20 , 30)
    weatherData.measurementChange()
    println("temp = :"+weatherData.temp)
    println("humidity = :"+weatherData.humidity)
    println("pressure = :"+weatherData.pressure)
    println("heatIndex = :"+HeatIndexDisplay(weatherData.temp, weatherData.humidity))


    weatherData.setMeasurement(20, 30 , 80)

    weatherData.measurementChange()
    println("temp = :"+weatherData.temp)
    println("humidity = :"+weatherData.humidity)
    println("pressure = :"+weatherData.pressure)
    println("heatIndex = :"+HeatIndexDisplay(weatherData.temp, weatherData.humidity))


    weatherData.setMeasurement(40, 20 , 11)
    weatherData.measurementChange()
    println("temp = :"+weatherData.temp)
    println("humidity = :"+weatherData.humidity)
    println("pressure = :"+weatherData.pressure)
    println("heatIndex = :"+HeatIndexDisplay(weatherData.temp, weatherData.humidity))
  }
}
