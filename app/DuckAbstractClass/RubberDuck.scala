package DuckAbstractClass

class RubberDuck extends Duck {

  override def swim: Unit = super.swim

  override def quack: Unit = super.quack

  def display = println("Rubber Duck Display.")

  def fly = println("override to do nothin")
}
