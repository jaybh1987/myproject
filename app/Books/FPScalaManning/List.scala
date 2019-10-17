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

  def length[A](as  : List[A]): Int = as match {
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

  def reverse[A](xs: List[A]): List[A] = foldLeft(xs, List[A]())( (x, y) => Cons(y, x))

  def reverseFoldRight[A](xs: List[A]): List[A] = foldRight(xs, List[A]())( (x, y) => Cons(x, y))

  def revTest[A](l: List[A]): List[A] = reverse(l)

  def append[A](x: A, xs: List[A]): List[A] = foldLeft(reverse(xs), Cons(x, Nil) )( (b, a) => Cons(a, b))

  def append1[A](x1: List[A], x2: List[A]): List[A] = foldRight(x1, x2)( (a, b) => Cons(a, b))

  def appendRight[A](x: A, xs: List[A]): List[A] = foldRight(xs, Cons(x, Nil))( (a, b) => Cons(a, b))

  def appendList[A](a1: List[A], a2: List[A]): List[A] = a1 match {
    case Nil => a2
    case Cons(h, t) => Cons(h, appendList(t, a2))
  }

  def transform(xs: List[Int]): List[Int] = xs match {
    case Nil => Nil
    case Cons(h, tail) => Cons( h + 1, transform(tail))
  }

  def map[A, B](xs: List[A])(f: A => B): List[B] = xs match {
    case Nil => Nil
    case Cons(h, tail) => Cons( f(h) , map(tail)(f))
  }

  def filter[A](as: List[A])(f: A => Boolean): List[A] = as match {
    case Nil => Nil
    case Cons(h, tail) if(f(h)) => Cons(h, filter(tail)(f))
    case Cons(h, tail) => filter(tail)(f)
  }

  def transformDouble(xs: List[Double]): List[String] = xs match {
    case Nil => Nil
    case Cons(h, tail) => Cons(h.toString, transformDouble(tail))
  }

  def foldLeftByFoldRight[A,B](xs: List[A], z: B)(f:(B, A) => B): B = foldRight(xs, z)( (a, b) => f(b, a) )

  def foldRightByFoldLeft[A, B](xs: List[A], z: B)(f: (A, B) => B): B = foldLeft(xs, z)( (b, a) => f(a, b))

  def concatenates[A](xs: List[List[A]]): List[A] = foldRight(xs, Nil: List[A])( (a, b) => appendList(a, b) )

  def flatMap[A, B](xs: List[A])(f: A => List[B]): List[B] = xs match {
    case Nil => Nil
    case Cons(h, tail) =>  concatenates(map(xs)(f))
  }

  // List(1, 2, 3) , List( 2, 3, 4) then output is List(3, 5, 7)
  def correspondingSum(x1: List[Int], x2: List[Int]): List[Int] = x1 match {
    case Nil => x2 match {
      case Nil => Nil
      case Cons(h, tail) => Cons(h , tail)
    }
    case Cons(h, tail) => x2 match {
      case Nil => Nil
      case Cons(hh, ttail) => Cons(h + hh, correspondingSum(tail, ttail))
    }
  }

  def correspondingSum1(x1: List[Int], x2: List[Int]): List[Int] = (x1, x2) match {
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, correspondingSum1(t1, t2))
    case (Nil, _) => Nil
    case (_, Nil) => Nil
  }

  def zipWith[A, B](xs: List[A], xxs: List[A])(f: (List[A], List[A]) => List[B]): List[B] = (xs, xxs) match {
    case(a1, a2) => f(a1, a2)
    case (_, Nil) => Nil
    case (Nil,_) => Nil
  }
  def hasSubSequence[A](sup: List[A], sub: List[A]): Boolean = ???
}

























