package form

import play.api.data
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class UserData(name: String, age: Int)

object UserData {

  val userForm = Form(
    mapping(
      "name" -> text,
      "age" -> number
    ) (UserData.apply)(UserData.unapply)
  )
}
