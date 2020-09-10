package DesingPattern.DecoratorPattern.concreteBeverages

import DesingPattern.DecoratorPattern.abstractClass.Beverage

class HouseBlend extends Beverage{

  override val description = "House Blend"

  override def cost: Double = 0.89
}
