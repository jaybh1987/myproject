package DesingPattern.DecoratorPattern.decorators

import DesingPattern.DecoratorPattern.abstractClass.{Beverage, CondimentDecorator}

case class Mocha(beverage: Beverage) extends CondimentDecorator{

  override def getDescription: String = beverage.getDescription + " Mocha"

  override def cost: Double = 0.20 + beverage.cost
}
