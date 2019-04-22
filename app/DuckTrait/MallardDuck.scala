package DuckTrait

class MallardDuck extends Duck with Flayable with Quackable {

  override def display = println("MallardDuck Display")
  def fly = println("MallardDuck flying")
  def quack = println("MallardDuck quack")

}
