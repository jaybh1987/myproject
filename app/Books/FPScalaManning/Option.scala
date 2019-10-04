package Books.FPScalaManning
sealed trait Option[+A]{

  def map[B](f: A => B): Option[B] = this match {
    case Some(value) => Some(f(value))
    case None => None
  }

  def flatMap[B](f: A => Option[B]): Option[B] = this match {
    case Some(value) => f(value)
    case None => None
  }

  def getOrElse[B >: A](default: => B): B = this match {
    case Some(value) => value
    case None => default
  }

  def orElse[B >: A](ob: => Option[B]): Option[B] = this match {
    case Some(value) => ob
    case None => None
  }

  def filter(f: A => Boolean): Option[A] = this match {
    case Some(value) => if(f(value)) Some(value) else None
    case None => None
  }

  def lift[A, B](f: A => B): Option[A] => Option[B] = _.map(f)

  def f(x: Int): Double = x.toDouble

  def liftingInt(f: Int => Double) : Option[Int] => Option[Double] = x => x.map(f)

  def map2[A, B, C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] = (a, b) match {
    case (Some(value_a), Some(value_b)) => Some(f(value_a, value_b))
    case (_ , _) => None
  }

  //start reading from page 56.
}



case class Some[A](get: A) extends Option[A]
case object None extends Option[Nothing]

object Handling {

  def mean(xs: Seq[Double]): Double = {

    if(xs.isEmpty)
      throw new ArithmeticException("mean of empty list!")
    else
      xs.sum / xs.length
  }

  def mean_1(xs: IndexedSeq[Double], onEmpty: Double): Double = {
    if(xs.isEmpty) onEmpty
    else xs.sum / xs.length
  }

  def mean_2(xs: Seq[Double]): Books.FPScalaManning.Option[Double] = {
    if(xs.isEmpty) None
    else Some( xs.sum / xs.length)
  }

}

trait Facility[M[A]]{

  def map[A, B](x: M[A])(f: A => B): M[B]

  def lift[A, B](f: A => B): M[A] => M[B] = map(_)(f)

}


trait Functor[F[A]] {
  def map[A, B](a: F[A])(f: A => B): F[B]
}

object OptionFunctor extends Functor[Option] {
  override def map[A, B](a: Option[A])(f: A => B): Option[B] = a.map(f)


  // def sequence[A](x: List[Option[A]]): Option[List[A]] = x match {
  // case h :: tail => h :: sequence(x)
  // case Nil => Some(Nil)
  // }

}




































object A {
  def lift[A, B, M[_]](f: A => B)(ma: Functor[M]): M[A] => M[B] = { a =>
    ma.map(a)(f)
  }
  val f: Int => Int = x => x + x
  val run = lift(f)(OptionFunctor)
}

//def f[A](x: List[Option[A]]): Option[List[A]] = x match {
//
//  case h :: tail =>
//    if(h.isDefined) {
//
//      h.map(x => x :: Nil)
//
//      h.map( x => x :: tail.map(b => b))
//
//      } else
//      {
//        None
//      }
//  case Nil => None
//
//}


