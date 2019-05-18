package DesingPattern.DesignPatternDuck

class ModelDuck extends Duck{

  opFlyBehavior = Some(new FlyNoWay)
  opQuackBehavior = Some(new Quack)

  override def display: Unit = println("I am ModelDuck.")


}
