package Books.Cookbook

import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonDSL._
import org.json4s.JsonDSL.WithDouble._
import org.json4s.JsonDSL.WithBigDecimal._

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


  def forYieldMethod(i: Int): Seq[Int] = {
    var cumulate: Int = 0
    val c = 0.to(i).map { i =>
      val s = i
      cumulate = cumulate + s
      (i, s, cumulate)
    }.map {
      case ((i@_), (s@_), (cumulate@_)) => cumulate
    }
    c
  }



}

class ScalaReference {

  val e = List(1,2,3,4,5)
  val e1 = List(5,6,7,8,9)

  val forAndYield = for(p <- e) yield e
  //translated.
  val forAndYieldTrans = e.map(p => e)

  val onlyFor = for(p <- e) e
  //translated.
  val onlyForTrans = e.foreach(p => e)

  val forWithMultiGenrator = for{
          p <- e;
          p1 <- e1
  } yield e

  val forWithMultiGenratorTrans = e.flatMap( p => for(p1 <- e1) yield e)



}
