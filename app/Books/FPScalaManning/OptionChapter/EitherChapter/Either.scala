package Books.FPScalaManning.OptionChapter.EitherChapter

import java.nio.channels.CompletionHandler

trait Either[+E, +A] {


  def map[B](f: A => B): Either[E, B] = this match {
    case Left(value) => Left(value)
    case Right(value) => Right(f(value))
  }

//  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
//    case Left(value) => Left(value)
//    case Right(value) => f(value)
//  }
//  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
//    case Left(value) => Left(value)
//    case Right(value) =>  b
//  }
//
//  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] = this match {
//    case Left(value) => Left(value)
//    case Right(value) =>  Right(f(value,value))
//  }
}
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]


object EitherTasks {

  def mean(xs: IndexedSeq[Double]): Either[String, Double] = {
    if(xs.isEmpty){
      Left("mean of empty list")
    } else {
      Right(xs.sum / xs.length)
    }
  }

  def safeDiv(x: Int, y: Int): Either[Exception, Double] ={
    try Right(x / y)
    catch {
      case e: Exception => Left(e)
    }
  }

}