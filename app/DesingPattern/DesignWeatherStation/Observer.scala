package DesingPattern.DesignWeatherStation

trait Observer {

  def update(temp: Double, humidity: Double, pressure: Double): Unit

}
