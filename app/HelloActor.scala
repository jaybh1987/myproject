import akka.actor.{Actor, Props}
import com.google.inject.{Inject, Singleton}
import com.typesafe.config.ConfigFactory


object HelloActor {
  def props = Props[HelloActor]

  case class SayHello(name: String)
}

@Singleton
class HelloActor @Inject()() extends Actor {
  import HelloActor._

  private val mailConfig = ConfigFactory.load

  override def receive: Receive = {
    case SayHello(name: String) => {
      println("hello, " + name)
    }
  }
}