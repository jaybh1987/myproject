package DesingPattern.DesignPatternDuck

class Duck {

  var opFlyBehavior: Option[FlyBehavior] = None
  var opQuackBehavior: Option[QuackBehavior] = None

  def performQuack = {
    opQuackBehavior.map { quackBehavior =>
      quackBehavior.quack
    }
  }

  def performFly = {
    opFlyBehavior.map { flyBehavior =>
      flyBehavior.fly
    }
  }

  def display = {
    println("Duck Display Method.")
  }

  def setQuackBehavior(quackBehavior: Option[QuackBehavior]): Unit = {
    opQuackBehavior = quackBehavior
  }

  def setFlyBehavior(flyBehavior: Option[FlyBehavior]) = {
    opFlyBehavior = flyBehavior
  }

}
