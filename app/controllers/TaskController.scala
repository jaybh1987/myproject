package controllers

import ListMethod.ClassList
import javax.inject.Inject
import play.api.mvc.{AbstractController, ControllerComponents}
import play.api.libs.json._

import scala.collection.mutable.ArrayBuffer

class TaskController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with
  play.api.i18n .I18nSupport  {



  def jsonClassConstruction = Action {

    val json: JsValue = JsObject(Seq(
      "name" -> JsString("Watership Down"),
      "location" -> JsObject(Seq("lat" -> JsNumber(51.235685), "long" -> JsNumber(-1.309197))),
      "residents" -> JsArray(IndexedSeq(
        JsObject(Seq(
          "name" -> JsString("Fiver"),
          "age" -> JsNumber(4),
          "role" -> JsNull
        )),
        JsObject(Seq(
          "name" -> JsString("Bigwig"),
          "age" -> JsNumber(6),
          "role" -> JsString("Owsla")
        ))
      ))
    ))
    Ok("")
  }


  def addToArray(str: String) = Action { implicit request =>

    val arr: ArrayBuffer[String] = ArrayBuffer()

    arr.append(str)

    Ok(views.html.arrayDisplay(arr))
  }


  def listMethod = Action { implicit request =>
    val obj = new ClassList
    val v =  obj.fLeft(List(1,2,3,4))(1)((x, y) => x + y)
    println("answer  = "+v)
    Ok("")
  }


  def jsonStringParsing = Action { implicit request =>


    val json: JsValue = Json.parse("""
  {
    "name" : "Watership Down",
    "location" : {
      "lat" : 51.235685,
      "long" : -1.309197
    },
    "residents" : [ {
      "name" : "Fiver",
      "age" : 4,
      "role" : null
    }, {
      "name" : "Bigwig",
      "age" : 6,
      "role" : "Owsla"
    } ]
  }
  """)

    Ok(views.html.jsonView(json))
  }

  def label = Action{ implicit request =>


    Ok("")
  }

}
