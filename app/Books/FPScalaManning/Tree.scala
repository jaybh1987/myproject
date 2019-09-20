
sealed trait Tree[+A]
case class Leaf[A](value: A) extends Tree[A]
case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]


class MyTree

object MyTree {

  val a = Branch(
    Branch(Branch(Leaf(4), Leaf(6)), Leaf(2)),
    Branch(Leaf(3), Leaf(3))
  )

  val b = Branch(
    Branch(
      Branch(
        Branch(Leaf(5), Leaf(5)),
        Leaf(4)),
      Leaf(3)
    ),
    Branch(Leaf(4), Leaf(10))
  )

  val k = Branch(Leaf(3), Leaf(4))

  def size[A](xs: Tree[A]): Int = xs match {
    case Leaf(value) => 1
    case Branch(l, r) => 1 + size(l) + size(r)
  }

  def maximum(xs: Tree[Int]): Int = xs match {
    case Leaf(value) => value
    case Branch(left, right) => maximum(left).max(maximum(right))
  }

  def depth[A](xs: Tree[A]): Int = xs match {
    case Leaf(value) => 0
    case Branch(l, r) => 1 + depth(l).max(depth(r))
  }

  def map[A, B](xs: Tree[A])(f: A => B): Tree[B] = xs match {
    case Leaf(value) => Leaf(f(value))
    case Branch(l, r) => Branch( map(l)(f), map(r)(f))
  }


  /*
  * generalizing function rules
  * 1st - take the subexpression out and make it function arguments
  * 2nd - if sub expression refers any local variable then
  * turn that subexpression into a function which accept this variables as an arguments.
  * */


}







