package controllers

import javax.inject.{Inject, Singleton}
import nintynineproblemofscala.Problem
import play.api.mvc.{AbstractController, ControllerComponents}


@Singleton
class ProblemController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with
play.api.i18n .I18nSupport {

  def flatMapsublist = Action { implicit  request =>
    val f = Problem.helperFunction _
    val ans =new Problem().flatMapSublists(List(1,2,3))(f)
    Ok(problemview.html.outputpage(ans))
  }

}
