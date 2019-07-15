package Books.FPScalaManning

sealed trait Option[+A]
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