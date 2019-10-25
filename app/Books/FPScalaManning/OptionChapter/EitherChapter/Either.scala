package Books.FPScalaManning.OptionChapter.EitherChapter
import java.nio.channels.CompletionHandler

import scala.collection.mutable.ArrayBuffer


sealed trait Either[+E, +A]{

  def map[B](f: A => B): Either[E, B] = this match {
    case Left(value) => Left(value)
    case Right(value) => Right(f(value))
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Left(value) => Left(value)
    case Right(value) => f(value)
  }

  def orElse[EE >: E,B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
    case Left(_) => b
    case Right(value) => Right(value)
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = {
    for{
      i <- this
      b1 <- b
    } yield(f(i, b1))
  }

  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = es match{
    case h :: tail => h match {
      case Right(value) =>  sequence(tail).map( x => x :+ value)
      case Left(value) => Left(value)
    }
    case Nil => Right(Nil)
  }

  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = ???

}
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]



