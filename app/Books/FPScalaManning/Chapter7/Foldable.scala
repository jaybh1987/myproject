package Books.FPScalaManning.Chapter7


trait Foldable[F[_]] {
  def foldRight[A, B](as: F[A])(z: B)(f: (A, B) => B) : B
  def foldLeft[A, B](as: F[A])(z: B)(f: (B, A) => B): B
  def foldMap[A, B](as: F[A])(f: A => B)(mb: Monoid[B]): B
  def concatenate[A](as: F[A])(m: Monoid[A]): A = foldLeft(as)(m.zero)(m.op)
}


object TryFoldable {
  val listFoldable = new Foldable[List] {
    def foldRight[A, B](ls: List[A])(z: B)(f: (A, B) => B): B = ls.foldRight(z)(f)
    def foldLeft[A, B](ls: List[A])(z: B)(f: (B, A) => B): B = ls.foldLeft(z)(f)
    def foldMap[A, B](ls: List[A])(f: A => B)(m: Monoid[B]) : B = foldRight(ls)(m.zero) {
      (someAtype, someBtype) =>
          m.op(someBtype, f(someAtype))
    }
  }

  val indexedSeqFoldable = new Foldable[IndexedSeq] {

    override def foldRight[A, B](as: IndexedSeq[A])(z: B)(f: (A, B) => B): B = as.foldRight(z)(f)

    override def foldLeft[A, B](as: IndexedSeq[A])(z: B)(f: (B, A) => B): B = as.foldLeft(z)(f)

    override def foldMap[A, B](as: IndexedSeq[A])(f: A => B)(mb: Monoid[B]): B = as.foldRight(mb.zero) {
      (someAtype, someBtype) =>
        mb.op(someBtype, f(someAtype))
    }
  }


  def flip[A, B, C](f: (A, B) => C): (B, A) => C = ( b: B, a: A) => {
    f(a, b)
  }

  val optionFoldable = new Foldable[Option] {
    override def foldRight[A, B](as: Option[A])(z: B)(f: (A, B) => B): B = as.map{
      a => f(a, z)
    }.getOrElse(z)
    override def foldLeft[A, B](as: Option[A])(z: B)(f: (B, A) => B): B = foldRight(as)(z)(flip(f))
    override def foldMap[A, B](as: Option[A])(f: A => B)(mb: Monoid[B]): B = as.map(f).getOrElse(mb.zero)
  }

  implicit class FoldableToListExtension[F[_]](foldable: Foldable[F]) {
    def toList[A](fa: F[A]): List[A] = foldable.foldRight(fa)(List.empty[A])( (x,y) => x :: y)
  }
}