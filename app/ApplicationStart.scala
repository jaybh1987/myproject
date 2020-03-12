
import akka.actor.{ActorSystem, Props}
import com.google.inject.Inject
import com.typesafe.akka.extension.quartz.QuartzSchedulerExtension
import play.api.inject.ApplicationLifecycle
import play.inject.Injector

import scala.concurrent.Future

class ApplicationStart @Inject()(
                                  lifecycle: ApplicationLifecycle,
                                  system: ActorSystem,
                                  injector: Injector
                                ) {
  val name = "ApplicationStart"

  // Shut-down hook
  lifecycle.addStopHook { () =>
    Future.successful()
  }

  // Start scheduling
  val scheduler = QuartzSchedulerExtension(system)
  val receiver = system.actorOf(Props.create(classOf[GuiceActorProducer], injector, classOf[HelloActor]))
  scheduler.schedule("every15seconds", receiver, HelloActor.SayHello("Jay bhavsar"), None)

}


object My {

  val word = List('a','a','b','a','a')

  word.foldLeft(List('a')) {
    (default, element) =>
      default match {
        case h :: tail => if(h == element) default :+ element else List(element)
      }
  }
}