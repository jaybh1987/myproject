package Books.Cookbook.DataStructure


sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]


object List {
  def apply[A](as: A*): List[A] = {
    if (as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
  }

  def setHead[A](h: A, ls: List[A]): List[A] = ls match {
    case Nil => Cons(h, Nil)
    case Cons(head, tail) => Cons(h, tail)
  }

  def drop[A](l: List[A], n: Int) : List[A] = l match {
    case Nil => Nil
    case Cons(h, tail) => if(n > 0) drop(tail, n - 1) else Cons(h, tail)
  }
}