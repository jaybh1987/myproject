package DesingPattern.DecoratorPattern.abstractClass

abstract class Beverage {

  val description : String = "Unknown Beverage"

  def getDescription = description

  /*we need to implement cost method in subclass.*/
  def cost : Double

}


