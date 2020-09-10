package DesingPattern.DecoratorPattern.decorators

import DesingPattern.DecoratorPattern.abstractClass.{Beverage, CondimentDecorator}

case class Soy(val beverage: Beverage) extends CondimentDecorator{

  override def getDescription: String = beverage.getDescription + " Soy"

  override def cost: Double = {
      0.15 + beverage.cost
  }
}
