package controllers

import DesignPatternDuck._
import javax.inject._
import play.api._
import play.api.mvc._
import form.UserData
import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with
play.api.i18n .I18nSupport {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>

    val anyData = Map("name" -> "bob", "age" -> "33")
    val userData = UserData.userForm.bind(anyData).get

    Ok(views.html.index(UserData.userForm))
  }

  def post() = Action {
    println("post")
    Ok("You have submitted ths form.")
  }

  def userData() = Action{ implicit request: Request[AnyContent] =>

    val anyData = Map("name" -> "bob", "age" -> "33")
    val userData = UserData.userForm.bind(anyData).get
    println("userData call.")
    Ok(views.html.showUserData(userData))
  }

  def displayProduct() = Action { implicit request =>
    val p = DAOs.Product("niky shoes", 5000)
    Ok(views.html.productPage(p))
  }

  def displayString(name: String) = Action{ implicit reqeust =>

      Ok(name)
  }

  def duckFly = Action { implicit  request =>

    var q = new Quack
    val f = new FlyWithWings

    val mallard: Duck = new MallardDuck(q, f)
    println("********************")
    println("mallard duck calls")
    mallard.performQuack
    mallard.performFly
    mallard.display

    Ok("")
  }


  def modelDuck = Action{ implicit request =>

    val model: Duck = new ModelDuck
    println("********************")
    println("model duck calls")
    model.performFly
    model.performQuack
    model.display

    println("******************")
    println("changing behavior")
    model.setFlyBehavior(Some(new FlyRocketPowered))
    model.setQuackBehavior(Some(new MuteQuack))
    model.performQuack
    model.performFly

    Ok(views.html.duckView(model))
  }

}
