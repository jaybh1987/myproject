package DesingPattern.DecoratorPattern.concreteBeverages

import DesingPattern.DecoratorPattern.abstractClass.Beverage

class DarkRoast extends Beverage{

  override val description : String = "Dark Roast"

  def cost: Double = 2.33

}
