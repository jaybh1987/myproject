package Books.FPScalaManning.Chapter7

object ProductMonoid {
  def productMonoid[A, B](aMonoid: Monoid[A], bMonoid: Monoid[B]): Monoid[(A, B)] = new Monoid[(A, B)] {
    def op(a1: (A, B), a2: (A, B)): (A, B) = {
      val (someA1, someB1) = a1
      val (someA2, someB2) = a2
      ( aMonoid.op(someA1, someA2), bMonoid.op( someB1, someB2) )
    }
    def zero:(A, B) = (aMonoid.zero, bMonoid.zero)
  }
}

object FunctionMonoid {
  def functionMonoid[A, B](monoid: Monoid[B]) : Monoid[A => B] = new Monoid[A => B] {
    def op(a1: A => B, a2: A => B): A => B = (x: A) => monoid.op( a1(x), a2(x) )
    def zero : A => B = (x: A) => monoid.zero
  }
}

