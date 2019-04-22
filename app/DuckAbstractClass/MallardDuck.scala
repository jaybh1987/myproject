package DuckAbstractClass

class MallardDuck extends Duck {

  override def swim: Unit = super.swim

  override def quack: Unit = super.quack

  def display = println("Mallard Duck Display.")

  def fly = println("MallardDuck flying.")
}
