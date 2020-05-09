package Books.FPScalaManning.Chapter5

sealed trait Stream[+A] {

  def toList: List[A] = {
    def go(s: Stream[A], acc: List[A]): List[A] = s match {
      case Cons(h, t) => go(t(), h() :: acc)
      case _ => acc
    }

    go(this, List()).reverse
  }

  def takeWhile(p: A => Boolean): Stream[A] = {

    def loop(s: Stream[A], f: A => Boolean): Stream[A] = s match {
      case Cons(h, t) if f(h()) => Cons(h, () => loop(t(), f))
      case _ => Empty
    }
    loop(this, p)
  }

}

case object Empty extends Stream[Nothing]
case class Cons[+A]( h: () => A, t:() => Stream[A]) extends Stream[A]

object Stream {

  def cons[A](hd: => A, t1: => Stream[A]): Stream[A] = {
    lazy val head = hd
    lazy val tail = t1
    Cons(() => head, () => tail)
  }

  def empty[A]: Stream[A] = Empty

  def apply[A](as: A*): Stream[A] = if(as.isEmpty) empty else cons(as.head, apply(as.tail:_*))

  def take[A](n: Int, s: Stream[A]): Stream[A] = {
    def go(pos: Int, k: Stream[A]): Stream[A] = k match {
      case Cons(h, t) if pos == n => empty
      case Cons(h, t) if pos < n => Cons(h, () => go(pos + 1, t()))
      case Empty => empty
    }
    go(0, s)
  }

  def drop[A](n: Int, s: Stream[A]): Stream[A] = {

    def go(pos: Int, k: Stream[A]): Stream[A] = k match {
      case Cons(h, t) if pos <= n => go(pos + 1, t())
      case Cons(h, t) if pos > n => Cons(h, () => go(pos + 1, t()))
      case Empty => Empty
    }

    go(1, s)
  }

  val k = Cons( () => 1, () => Cons( () => 2, () => Cons( () => 3, () => Empty)))
}
