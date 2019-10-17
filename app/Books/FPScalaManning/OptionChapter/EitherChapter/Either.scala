package Books.FPScalaManning.OptionChapter.EitherChapter
import java.nio.channels.CompletionHandler


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
    case Left(value) => Left(value)
    case Right(value) => b
  }

}
case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]