sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[A](h: A, tail: List[A]) extends List[A]

def foldLeft[A, B](xs: List[A], z:B)(f:(B, A) => B):B = xs match {
  case Nil => z
  case Cons(h, tail) => foldLeft(tail, f(z, h))(f)
}

def foldRight[A, B](xs: List[A], z: B)(f: (A, B) => B): B = xs match {
  case Nil => z
  case Cons(h, tail) => f(h, foldRight(tail, z)(f))
}


def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
  case Nil => a2
  case Cons(h, tail) => Cons(h, append(tail, a2))
}


def concate[A](xs: List[List[A]]): List[A] = xs match {
  case Nil => Nil
  case Cons(h, tail) => append(h, concate(tail))
}

def map[A, B](xs: List[A])(f: A => B): List[B] = xs match {
  case Nil => Nil
  case Cons(h, tail) => Cons( f(h), map(tail)(f))
}

def flatMap[A, B](xs: List[A])(f: A => List[B]): List[B] = xs match {
  case Nil => Nil
  case Cons(h, tail) =>  concate(map(tail)(f))
}


