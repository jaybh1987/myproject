package DuckTrait

class RedHeadDuck extends Duck with Flayable with Quackable {

  override def display = println("RedHeadDuck display")
  def fly = println("RedHeadDuck fly")
  def quack = println("ReadHeadDuck Swimming")
}
