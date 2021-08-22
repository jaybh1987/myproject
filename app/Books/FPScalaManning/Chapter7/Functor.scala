package Books.FPScalaManning.Chapter7

//trait Functor[F[_]] {
//  def map[A, B](as:F[A])(f: A => B): F[B]
//}

/*
*   we have parameterized map on the type constructor , F[_]
*   Functor trait is paramatric in the choce of F
*   Here is the instance of list.
* */

object Functor {
  val listFunctor = new Functor[List] {
    def map[A, B](as: List[A])(f: A => B): List[B] = as.map(f)
  }
}


/*
*   We can say type constructor like List or Option or F is a functor.
*   and the Functor[F] instance constitutes proof  that F is in fact functor.
*
*   what we achieve using this abstraction ??
*   we can discover useful functions just by playing with operations of the interface , in a purly algebraic way.
*
*   Any useful operations you can define only in terms of map ???
*
* */

trait Functor[F[_]] {

  def map[A, B](as:F[A])(f: A => B): F[B]

  def distribute[A, B](fab: F[(A, B)]): (F[A], F[B]) = (  map(fab)( r => r._1), map(fab)( r => r._2) )

  def codistribute[A, B](e: Either[F[A], F[B]]): F[Either[A, B]] = e match {
    case Left(errorValue)       =>    map(errorValue)(  Left(_)  )
    case Right(sucessValue)     =>    map(sucessValue)( Right(_)  )
  }

}

/*
*   Functor Laws
*   2 important reason for Laws.
*
*   First is :
*   Law helps an interface form a new semantic level
*   whose algebra may be reasoned about independently of the intances.
*
*   e.g
*   when we talk product of a Monoid[A] and Monoid[B] to form a Monoid[(A, B)],
*   the monoid laws let us conclude that "fused" monoid operation is also associative.
*   we don't need to know anything about A and B to conclude this.
*
*   Second is :
*   we often rely on laws when writing various combinators derived from the functions
*   of some abstract interface like Functor.
*
* */





