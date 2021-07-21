package Books.FPScalaManning.Chapter7
//https://github.com/robertberry/Functional-Programming-in-Scala-Exercises/blob/master/src/main/scala/com/github/robertberry/fpis/Chapter10.scala
trait Monoid[A] {

  def op(a1: A, a2: A): A

  def zero: A
}

object Test {

  val stringMonoid = new Monoid[String] {

    def op(a1: String, a2: String) = a1 + a2
    def zero = ""
  }

//  val listMonoid = new Monoid[List[A]] {
//    def op(a1: List[A], a2: List[A]) = a1 ++ a2
//
//    def zero = Nil
//  }

  val intAddition = new Monoid[Int] {
    def op(a1: Int, a2: Int) = a1 + a2
    def zero = 0
  }

  val intMultiplication = new Monoid[Int] {
    def op(a1: Int, a2: Int) = a1 * a2
    def zero = 0
  }

  val booleanOr = new Monoid[Boolean] {
    def op(a1: Boolean, a2: Boolean) = a1 || a2

    def zero = false
  }

  val booleanAnd = new Monoid[Boolean] {

    def op(a1: Boolean , a2: Boolean) = a1 && a2

    def zero = true
  }


  val optionMonoid = new Monoid[Option[Int]] {
    def op(a1: Option[Int], a2: Option[Int]) = a1.orElse(a2)

    def zero = None
  }

  def endoMonad = new Monoid[A => A] {
    def op(a1: A => A, a2: A => A): A => A  = (A) => a1.compose(a2)
    def zere = identity(A)
  }
}