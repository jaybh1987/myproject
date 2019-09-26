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
































