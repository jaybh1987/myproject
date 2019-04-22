package DuckAbstractClass

class DecoyDuck extends Duck {

  def display = println("DecoyDuck display")

  def fly = println("override to do nothing")

  override def quack = println("override to do nothing")
}
