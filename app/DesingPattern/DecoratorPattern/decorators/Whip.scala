package DesingPattern.DecoratorPattern.decorators

import DesingPattern.DecoratorPattern.abstractClass.{Beverage, CondimentDecorator}

case class Whip(beverage: Beverage) extends CondimentDecorator{

  override def getDescription: String = beverage.getDescription + " Whip"

  override def cost: Double = 0.12 + beverage.cost
}
