package DesingPattern.DesignWeatherStation

class WeatherStationPull extends App{


  val weatherDataPull = new WeatherDataPull

  val currentConditionDisplayPull = CurrentConditionDisplayPull(weatherDataPull)

  weatherDataPull.setMeasurement(20,10,30)

  println("temp = : "+weatherDataPull.getTemp)
  println("humid = : "+weatherDataPull.getHumid)
  println("pressure = : "+weatherDataPull.getPressure)

  weatherDataPull.setMeasurement(90,20,44)

  println("temp = : "+weatherDataPull.getTemp)
  println("humid = : "+weatherDataPull.getHumid)
  println("pressure = : "+weatherDataPull.getPressure)

  weatherDataPull.setMeasurement(92,27,43)
  println("temp = : "+weatherDataPull.getTemp)
  println("humid = : "+weatherDataPull.getHumid)
  println("pressure = : "+weatherDataPull.getPressure)

}
