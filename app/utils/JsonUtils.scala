package utils

import org.json4s.NoTypeHints
import org.json4s.ext.JavaTypesSerializers
import org.json4s.jackson.Serialization

object JsonUtils {
  def toJsonString(value: AnyRef) = {
    implicit val formats = Serialization.formats(NoTypeHints) ++ JavaTypesSerializers.all
    Serialization.write(value)
  }
}
