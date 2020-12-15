import play.api.libs.concurrent.ExecutionContextProvider

//package Books.FPScalaManning.OptionChapter.EitherChapter
//import java.nio.channels.CompletionHandler
//
//import scala.collection.mutable.ArrayBuffer
//
//
//sealed trait Either[+E, +A]{
//
//  def map[B](f: A => B): Either[E, B] = this match {
//    case Left(value) => Left(value)
//    case Right(value) => Right(f(value))
//  }
//
//  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
//    case Left(value) => Left(value)
//    case Right(value) => f(value)
//  }
//
//  def orElse[EE >: E,B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
//    case Left(_) => b
//    case Right(value) => Right(value)
//  }
//
//  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
//    for{
//      i <- this
//      b1 <- b
//    } yield(f(i, b1))
//  }
//
//  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = es match{
//    case h :: tail => h match {
//      case Right(value) =>  sequence(tail).map( x => x :+ value)
//      case Left(value) => Left(value)
//    }
//    case Nil => Right(Nil)
//  }
//
//  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = ???
//
//}
//case class Left[+E](value: E) extends Either[E, Nothing]
//case class Right[+A](value: A) extends Either[Nothing, A]
//
//
//
sealed trait Either [+E, +A] {
  def map[B](f: A => B): Either[E, B] = this match {
    case Left(value) => Left(value)
    case Right(value) => Right(f(value))
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Left(value) => Left(value)
    case Right(value) => f(value)
  }

  def orElse[EE >: E, B >: A](b: Either[EE, B]): Either[EE, B] = this match {
    case Left(value) => Left(value)
    case Right(value) => Right(value)
  }


  def map2_OtherWay[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {

    this match {
      case Left(value) => Left(value)
      case Right(value) => b.map(r => f(value, r))
    }
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = for {
    didfora <- this
    didforb <- b
  } yield f(didfora, didforb)

  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = es match {
    case h :: tail => h match {
      case Left(value) => Left(value)
      case Right(value) => sequence(tail)
    }
  }

  def sequenceww[E, A](es: List[Either[E, A]]) = es match {
    case h :: tail => h match {
      case Left(value) => Left(value)
      case Right(value) => sequence(tail)
    }
  }

  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = {
    as match {
      case h :: tail => f(h) match {
        case Left(value) => Left(value)
        case Right(value) => traverse(tail)(f).map( r => value +: r)
      }
      case Nil => Right(Nil)
    }
  }

//  def traversTwo[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = {
//
//  }



  /*
  * traverse(List(2, 5))( x => dev10(x))
  *          traverse(List(5))(x => dev10(x))
  *                   traverse(Nil)( x => dev10(x))
  *
  * */


  //  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = {
//
//    def fun(oldList: List[A], newList: List[B]): Either[E, List[B]] = oldList match {
//      case h :: tail => f(h) match {
//        case Left(value) => Left(value)
//        case Right(value) =>  traverse(tail)(f).map( r => r ++ newList)
//      }
//      case Nil => Right(newList)
//    }
//    fun(as, List.empty)
//  }

}

case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]


object Examples {

  def mean(xs: IndexedSeq[Double]) = if (xs.isEmpty) {
    Left("Mean of empty list")
  } else {
    Right(xs.sum / xs.length)
  }

  def devideTen(x: Int): Either[Exception, Double] = try (Right(10 / x)) catch {
    case e: Exception => Left(e)
  }


  def safeDiv(x: Int, y: Int) = try {
    Right(x / y)
  } catch {
    case e: Exception => Left(e)
  }

  def Try[A](a: => A): Either[Exception, A] = {
    try (Right(a))
    catch {
      case e: Exception => Left(e)
    }
  }

  def dev10(by: Int) = try ( Right(10/by)) catch { case e: Exception => Left(e)}

}

case class Person(name: Name, age: Age)
sealed class Name(val value: String)
sealed class Age(val age: Int)

object Person {

  def mkName(name: String) : Either[String, Name] = if(name =="" || name == null) Left("Name is empty")
  else Right(new Name(name))

  def mkAge(age: Int): Either[String, Age] = if(age < 0) Left("Age is out of range.")
  else Right(new Age(age))

  def mkPerson(name: String, age: Int): Either[String, Person] = {
    mkName(name).map2(mkAge(age))(Person(_, _))
  }
}


























