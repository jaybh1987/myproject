package Cookbook

//final case class Failed[A](val exception: Exception) extends Attempt[A] {
//  isSuccess = false
//  override def get = throw exception
//}
//
//final case class Succeeded[A](value: A) extends Attempt[A] {
//  isSuccess = true
//  override def get = value
//}
//
//sealed abstract class Attempt[A] {
//  var isSuccess = true
//  def get : A
//  def getOrElse[B >: A](default: => B): B = if(isSuccess) get else default
//}
//
//object Attempt {
//  def apply[A](f: => A): Attempt[A] = {
//    try {
//      val result = f
//      Succeeded(result)
//    } catch {
//      case e: Exception => Failed(e)
//    }
//  }
//}

  trait Animal
  final case class Dog(name: String) extends Animal
  final case class Cat(name: String) extends Animal

  object Humanish {

    trait HumanLike[A] {
      def speak(speaker: A): Unit
    }

    object HumanLike {

      implicit object DogIsHumanLike extends HumanLike[Dog] {
        override def speak(speaker: Dog): Unit = {
          println(s"this is dog ${speaker.name}")
        }
      }
    }

  }

class Cbook {

  def funForDog[A](animal: A)(implicit s: Humanish.HumanLike[A]) = {
    s.speak(animal)
  }
}




