package DuckAbstractClass

class RedHeadDuck extends Duck {

  override def swim: Unit = super.swim

  override def quack: Unit = super.quack

  def display = println("RedHeadDuck display.")

  def fly = print("RedHeadDuck flying")
}
