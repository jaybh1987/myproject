package Books.FPScalaManning

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]


object List {

  def apply[A](as: A*): List[A] = {
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(h, tail) => h + sum(tail)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(h, tail) => h * product(tail)
  }

  def setHead[A](h: A, ls: List[A]): List[A] = ls match {
    case Nil => Nil
    case Cons(head, tail) => Cons(h, tail)
  }

  def drop[A](l: List[A], n: Int) : List[A] = l match {
    case Nil => Nil
    case Cons(h, tail) => if(n > 0) drop(tail, n - 1) else Cons(h, tail)
  }

  def drop1[A](l: List[A], n: Int) : List[A] = l match {
    case Nil => Nil
    case Cons(h, tail) if n > 0 => drop(tail, n - 1)
    case _ => l
  }

  //return list but the last element.
  def init[A](l: List[A]):List[A] = l match {
    case Nil => Nil
    case Cons(h, Nil) => Nil
    case Cons(h, tail) => Cons(h, init(tail))
  }

  def dropWhile[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case Cons(h, tail)  if( f(h) ) => dropWhile(tail)(f)
    case _ => as
  }

  def foldRight[A, B](xs: List[A], z: B) (f:(A, B) => B): B = xs match {
    case Nil => z
    case Cons(h, tail) => f(h, foldRight(tail, z)(f))
  }

  def length[A](as: List[A]): Int = as match {
    case Nil => 0
    case Cons(h, tail) => 1 + length(tail)
  }

  //function must be tail recursive.
  def foldLeft[A, B](ls: List[A], z: B)(f: (B, A) => B): B = ls match {
     case Nil => z
     case Cons(h, tail) => foldLeft(tail, f(z, h))(f)
  }

  def sumUsingFoldLeft(xs: List[Int]): Int = foldLeft(xs, 0)( (x, y) => x + y)

  def productUsingFoldLeft(xs: List[Double]): Double = foldLeft(xs, 1.0)((x, y) => x * y)

  def lengthUsingFoldLeft(xs: List[Int]): Int = foldLeft(xs, 0)((x, y) => x + 1)
}