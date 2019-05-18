package DesingPattern.DesignPatternDuck

class FlyNoWay extends FlyBehavior {

  override def fly: Unit = println("Not Able To Fly.")
}
