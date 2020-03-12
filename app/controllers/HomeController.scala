package controllers

import java.io.File

import Books.FPScalaManning.Cons
import DesingPattern.DesignPatternDuck._
import DesingPattern.DesignWeatherStation.{WeatherStation, WeatherStationPull}

import javax.inject._
import org.mongodb.scala._
import play.api.mvc._
import utils.ExcelUtil
import work.SyncCallBack.{A, B}

import scala.concurrent.ExecutionContext.Implicits.global
import play.api.mvc._
import akka.actor._
import javax.inject._
import akka.pattern._
import akka.util.Timeout
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension


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

  def fileTest = Action {
      implicit request: Request[AnyContent] =>
        ExcelUtil.exportExcep
        val f = new java.io.File("/tmp/scalabook.xlsx")
      Ok.sendFile(f)
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

  def synDemo = Action{
    implicit request =>

      val obj = new B()
      val mListner = new A()

      obj.registerOnGreekEventListner(mListner)
      obj.doGreekStuff()
      Ok("synDemo called.")
  }

  def aysDemo = Action {
    implicit request =>

      val obj = new work.AsyncCallBack.B()

      val mlistener = new work.AsyncCallBack.A()

      obj.registerOnGreekEventListener(mlistener)

      obj.doGreekStuff

      Ok("aysDemo called.")
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

  def excelWork = Action {
    implicit request =>

    import java.io.FileOutputStream
    import java.io.IOException
    import org.apache.poi.ss.usermodel.Cell
    import org.apache.poi.ss.usermodel.Row
    import org.apache.poi.xssf.usermodel.XSSFSheet
    import org.apache.poi.xssf.usermodel.XSSFWorkbook

    val workbook = new XSSFWorkbook()
    val sheet = workbook.createSheet("scala books.")

    val bookData =  List(
      List("headfirst java", "kathy serria", 79),
      List("java", "kathy bloch", 73),
      List("clean code", "kathy serria", 73),
      List("thinking in java", "kathy serria", 73)
    )

    var rowcount = 0
    var columnCount = 0

    val k = for {

      abook <- bookData

      row = sheet.createRow( {rowcount += 1; rowcount})

      counter <- 0 until abook.length

      cell = row.createCell(counter)

      _ = println("column count --"+columnCount)

      out = if (abook(counter).isInstanceOf[String]){
        println("yes String."+abook(counter).asInstanceOf[String])
        cell.setCellValue(abook(counter).asInstanceOf[String])
      } else {
        println("yes int.")
        cell.setCellValue(abook(counter).asInstanceOf[Int])
      }

    } yield()


      try{
        val outputStream: FileOutputStream = new FileOutputStream("/home/laitmatus/Desktop/scalabook.xlsx")

        workbook.write(outputStream)
      } catch {
        case e: Exception => e.printStackTrace()
      }
    Ok(100.toString)
  }

  def mongoTest = Action {

    implicit request =>



      val mongoClient : MongoClient =  MongoClient()
      val database : MongoDatabase = mongoClient.getDatabase("mydb")

      val collection : MongoCollection[Document] = database.getCollection("test")

      val doc: Document = Document(
        "_id" -> 0,
        "name" -> "MongoDB",
        "type" -> "database",
        "count" -> 1,
        "info" -> Document(
          "x" -> 203,
          "y" -> 102
        )
      )

      collection.insertOne(doc)

      Ok("mongo test.")
  }

}
