package Books.FPScalaManning.Chapter7

import java.util.NoSuchElementException

//https://github.com/robertberry/Functional-Programming-in-Scala-Exercises/blob/master/src/main/scala/com/github/robertberry/fpis/Chapter10.scala

trait Monoid[A] {
  def op(a1: A, a2: A): A
  def zero: A
}


object TestHello {

  val stringMonoid = new Monoid[String] {
    def op(a1: String, a2: String) = a1 + a2
    def zero = ""
  }

  "foo".length + "bar".length == ("foo" + "bar").length
  /*
  * length is function from String to Int that preserved the monoid structure.
  * such function called a monoid homomorphism.
  *
  * a monoid homomorphism f beteween monoid M and N obeys the law for all values x and y
  *
  * M.op( f(x), f(y) ) == f( N.op(x, y)  )
  *
  *
  * */


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

//  def endoMonad: Monoid[A => A] = new Monoid[A => A] {
//    def op(a1: A => A, a2: A => A): A => A  = (A) => a1.compose(a2)
//    def zere = identity(A)
//  }

//    def foldLeft[A, B](ls: List[A])(z: B)(f: (B, A) => B): B = ls match {
//      case h :: tail => foldLeft(tail)(f(z, h))(f)
//      case Nil => z
//    }
    //(z: B) ( (B, A) => B) : B
  def foldMap[A, B](xs: List[A])(m: Monoid[B])(f: A => B): B = xs.foldLeft(m.zero){
    (myDefaultb, someA) =>
        m.op(myDefaultb, f(someA))
  }



}
























