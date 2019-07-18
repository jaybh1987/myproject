package DesingPattern.DesignPatternDuck

class MallardDuck(quackBehavior: QuackBehavior, flyBehavior: FlyBehavior) extends Duck {

  opQuackBehavior = Some(quackBehavior)
  opFlyBehavior = Some(flyBehavior)

  override def display: Unit = println("MallardDuck display method.")

}
