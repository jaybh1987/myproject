package controllers

import java.io.File

import Books.FPScalaManning.Cons
import DesingPattern.DesignPatternDuck._
import DesingPattern.DesignWeatherStation.{WeatherStation, WeatherStationPull}
import akka.http.impl.util.JavaAccessors
import akka.stream.scaladsl.{FileIO, Source}
import akka.util.ByteString
import javax.inject._
import play.api.mvc._
import play.http.HttpEntity
import work.CodeB
import scala.concurrent.ExecutionContext.Implicits.global

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

    Ok("")
  }


  def barcode = Action{ implicit  request: Request[AnyContent] =>

      val c = new CodeB
      c.fun("12345678")
      Ok("")
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

  def testingApi = Action{ implicit request =>

    val a = List(1,2,3)
    val b = List(11,12)

    val k = a.map(x => x + 1).map(b => b + 2)

//    val k = for{
//      i <- a.map(xx => xx + 1)
//      j <- b
//      m = i + j
//    } yield(m)

    Ok(k.toString)
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

    Ok("")
  }

  def observerPatten = Action{ implicit request =>

    val v = new WeatherStation
    println(v.main(Array("a")))
    Ok("")
  }

  def observerPatternPull = Action {implicit request =>
    val v = new WeatherStationPull
    println(v.main(Array("j")))
    Ok("")
  }

  def showData = Action { implicit request =>
    val b = List(1,2,3,4,5)
    Ok(ExampleViews.html.result(b))
  }

  def reverseList = Action { implicit  request =>
    import Books.FPScalaManning.Nil
    val ans = Books.FPScalaManning.List.revTest(Cons(1, Cons(2, Nil)))
    println("output = "+ans)
    Ok("")
  }

  def reverseFR = Action{ implicit request =>
    import Books.FPScalaManning.Nil

    val ans = Books.FPScalaManning.List.reverseFoldRight(Cons(1, Cons(2, Cons(3, Nil))))
    print(ans)
    Ok("")
  }

  def appendList = Action { implicit request =>
    import Books.FPScalaManning.Nil
    val ans = Books.FPScalaManning.List.append(4, Cons(1, Cons(2, Nil)))
    println("output = "+ans)

    Ok("")
  }

  def appendRight = Action { implicit request =>
    import Books.FPScalaManning._

    val ans = List.appendRight(3, Cons(1, Cons(2, Nil)))
    println("output  = "+ans)
    Ok("")
  }

  def transform = Action{ implicit request =>
    import Books.FPScalaManning._

    val ans = List.transform(Cons(1, Cons(2, Cons(3, Nil))))

    println(ans)
    Ok("")
  }

  def transformDouble = Action{ implicit request =>
    import Books.FPScalaManning._
    val ans = List.transformDouble(Cons(1d, Cons(2d, Cons(3d, Nil))))
    println(ans)
    Ok("")
  }

  def contentType = Action { implicit request =>
    Ok.sendFile(
      content = new File("/home/laitmatus/Desktop/car.pdf"),
      fileName = _ => "s.pdf"
    )
  }

  def map = Action { implicit request =>
    import Books.FPScalaManning._
    val ans = List.map(List(1,2,3,4))(x => x + 1)
    println("output is = "+ans)
    Ok("")
  }


  def filter = Action{ implicit request =>

    import Books.FPScalaManning._
    val ans = List.filter(Cons(1, Cons(2, Cons(3, Nil))))( x => x % 2 == 0)

    println("output is = "+ans)
    Ok("")
  }

  def multipleData = Action { implicit request =>
    Ok(ExampleViews.html.genericData(List(1,2,3)))
  }


  def failingFn(i: Int): Int = {

    val y: Int = throw new Exception("fail!")

    try{
      val x = 42 + 5
      x + y
    }catch {
      case e: Exception => 42
    }
  }

  def failingFn2(i: Int): Int = {
    try {
      val x = 42 + 5
      x + ((throw new Exception("fail!")): Int)

    } catch {
      case e: Exception => 42
    }
  }


}
