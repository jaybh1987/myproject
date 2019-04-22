package DesignPatternDuck

class FlyWithWings extends FlyBehavior {
  override def fly: Unit = println("flying with wings.")
}
