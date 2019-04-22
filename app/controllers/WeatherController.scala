package controllers

import play.api.mvc.{AbstractController, ControllerComponents}
import javax.inject.Inject

class WeatherController @Inject()(cc: ControllerComponents) extends AbstractController(cc) with
play.api.i18n .I18nSupport  {


}
