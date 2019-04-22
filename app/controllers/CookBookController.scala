package controllers

import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import Cookbook._
class CookBookController @Inject()(cc: ControllerComponents) extends AbstractController(cc)
with play.api.i18n .I18nSupport {


//  def callAttemptMethod = Action { implicit request =>
//    val ans = Attempt(4 / 2)
//    val ans2 = Attempt( 4 / 0 )
//    val ans3 = Attempt( 5 / 6)
//
//    val listOfTry = List(ans, ans2, ans3)
//    Ok(viewsCookBook.html.resultpage(""))
//  }

  def testDog = Action { implicit request =>
    val d = new Dog("sunny")
    val s = new Cbook
    s.funForDog(d)
    Ok("")
  }

}
