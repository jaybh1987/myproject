package controllers

import DesingPattern.DecoratorPattern.abstractClass.Beverage
import DesingPattern.DecoratorPattern.concreteBeverages.{DarkRoast, Espresso, HouseBlend}
import DesingPattern.DecoratorPattern.decorators.{Mocha, Soy, Whip}
import javax.inject.Inject
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents, Request}
import javax.inject._
import play.api.mvc._

@Singleton
class DecoratorController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with
  play.api.i18n .I18nSupport {


  def orderCoffee = Action {
    implicit reqeust : Request[AnyContent] =>

      val beverage: Beverage = new Espresso()

      println(s"beverage = ${beverage.getDescription}")
      println(s"cost = ${beverage.cost}")

      var beverage2 : Beverage = new DarkRoast()
      beverage2 = new Mocha(beverage2)
      beverage2 = new Mocha(beverage2)
      beverage2 = new Whip(beverage2)

      println(
        s"""
           |beverage2 description = ${beverage2.getDescription}
           |beverate2 cost = ${beverage2.cost}
         """.stripMargin)

      var beverage3: Beverage = new HouseBlend()
      beverage3 = new Soy(beverage3)
      beverage3 = new Mocha(beverage3)
      beverage3 = new Whip(beverage3)

      println(
        s"""
           |beverage3 description = ${beverage3.getDescription}
           |beverage3 cost = ${beverage3.cost}
         """.stripMargin)

      Ok("")
  }


}
