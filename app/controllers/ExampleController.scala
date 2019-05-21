package controllers

import Books.problemsOfScala.Example
import javax.inject.Inject
import play.api.mvc._

class ExampleController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with
  play.api.i18n .I18nSupport{

  //remove every N'th element in the list.
  def dropRecursive = Action { implicit request: Request[AnyContent] =>

    val ansList = Example.dropRecursive(2, List(1,2,3,4))

    Ok(ExampleViews.html.result(ansList))
  }


  }
