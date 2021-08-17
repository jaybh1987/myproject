package Books.FPScalaManning.Chapter7

/*
*     generalizing the flatMap and unit functions
*     Functor is 1 of the many abstraction.
*     Functor isn't too compelling
*     as there aren't many useful operations that can be defined purely in terms of map.
*
*     so let take intereting interface Monad
*     we can implement no of useful operations
*     once and for all ,
*     factoring out what would otherwise be duplicated code.
*     And it comes with the laws with which we can reason that our libraries work the way we expect.
*
* */
trait Monad[F[_]] {
  def unit[A](a: => A): F[A]

  def flatMap[A, B](fa: F[A])(f: A => F[B]): F[B]

  def map[A, B](fa: F[A])(f: A => B): F[B] = flatMap(fa)(unit(_))

  def map2[A, B, C](fa: F[A], fb: F[B])(f: (A, B) => C): F[C] = flatMap(fa){a => map(fb){ b => f(a, b)}}
}
