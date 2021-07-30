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

  def last[A](seq: Seq[A]): A = seq match {
    case h :: Nil => h
    case h :: tail => last(tail)
    case _ => throw new NoSuchElementException("Not Found")
  }

  def updateList[A](r: Seq[A], elementToAdd: A): Seq[A] = r.dropRight(1) :+ elementToAdd

  def runLengthEncoding(x: String): String = {
    x.foldLeft(Seq[(Int, Char)]()) {
      (myDefault, element) =>
        if(myDefault.isEmpty) myDefault :+ (1, element)
        else {
          val lastElm = last(myDefault)
          if(lastElm._2 == element) updateList(myDefault, (lastElm._1 + 1, element) )
          else myDefault :+ (1, element)
        }
    }.map{ case (count, alpha) => s"$count$alpha"}.mkString
  }




}























