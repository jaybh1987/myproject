package DuckTrait

class RubberDuck extends Duck with Quackable {

  override def display: Unit = println("RubberDuck Display")

  override def swim: Unit = println("RubberDuck Swimming")

  def quack = println("RubberDuck Quack")
}
