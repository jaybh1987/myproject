package Books.FPScalaManning.Chapter6

class TestRandomNumber {

  def rollDie: Int = {
    val rng = new scala.util.Random
    rng.nextInt(6)
  }

  /* one suggestion might be to pass in the random number generator. That way, when we
  *  want to reproduce a fail test, we can pass the same generator that cause the test
  *  to fail*/

  def rollDie(rng: scala.util.Random): Int = rng.nextInt(6)

}


trait RNG {
  def nextInt: (Int, RNG)
}

/* A purely functional random number generator */

case class SimpleRNG(seed: Long) extends RNG {
  override def nextInt: (Int, RNG) = {
    val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0XFFFFFFFFFFFFFFFL
    val nextRNG = SimpleRNG(newSeed)
    val n = (newSeed >>> 16).toInt
    (n, nextRNG)
  }

  /* here i1 and i2 will be the same! if we want to generate two distinct numbers,
  * we need to use the RNG returned by the first call to nextInt to generate the second Int*/
//  def randomPair(rng: RNG): (Int, Int) = {
//    val (i1, _) = rng.nextInt
//    val (i2, _) = rng.nextInt
//    (i1, i2)
//  }

  /*correct one  : ((Int, Int), RNG)*/
  def randomPair(rng: RNG) = {

    val (i1, rng2) = rng.nextInt
    val (i2, rng3) = rng2.nextInt

    ((i1, i2), rng3)
  }


  /*
  * write a function that uses RNG.nextInt to generate a random integer between 0 and Int.maxValue(inclusive).
  * Make sure to handle the corner case when nextInt returns Int.MinValue, which doesn't have a non-negative counterpart.*/
  def nonNegativeInt(rng: RNG): (Int, RNG) = {
    val (n, r) = rng.nextInt
    if(n < 0) {
      (Math.abs(n), r)
    } else (n, r)
  }

  /*
  * Write a function to generate a Double between 0 and 1, not including 1.
  * Note: You can use Int.MaxValue to obtain the maximum positive integer value, and
  * you can use x.toDouble to convert an x: Int to a Double
  * */
  def double(rng: RNG): (Double, RNG) = {
    val (n, rn) = nonNegativeInt(rng)
    (n.toDouble/(Int.MaxValue.toDouble + 1), rn)
  }

  /*
  * Write functions to geneate an(int, Double) pair, a (Double, Int) pair, and a (Double, Double, Double) 3-tuple. You should be able to resue the functions you've already written
  *
  * */

  def intDouble(rng: RNG): ((Int,Double), RNG) = {
    val (n, rng1) = nonNegativeInt(rng)
    val (n2, rng2) = double(rng1)

    ((n, n2), rng2)
  }

  def doubleInt(rng: RNG):((Double, Int), RNG) = {
    val (n, rng1) = double(rng)
    val (n1, rng2) = nonNegativeInt(rng1)

    ((n, n1), rng2)
  }
  def double3(rng: RNG): ((Double, Double, Double), RNG) = {

    val (n, rng1) = double(rng)
    val (n1, rng2) = double(rng1)
    val (n2, rng3) = double(rng2)

    ((n, n1, n2), rng3)
  }

  /*Write a function to generate a list of random integers*/

  def ints(count: Int)(rng: RNG): (List[Int], RNG) = {

    def loop(c: Int, list: List[Int], rn: RNG): (List[Int], RNG) = {
      if(c == 0) (list, rn)
      else {
        val (number, r) = rn.nextInt
        loop(c - 1, list :+ number, r)
      }
    }
    loop(count, Nil, rng)
  }

  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] = rng => (a, rng)


  /*
  * takes a function RNG => (A, RNG) and apply f on A  and
  * retuns a function RNG => (B, RNG)
  * */
  def map[A, B](s: Rand[A])(f: A => B): Rand[B] = rng => {
    val (a, rng2) = s(rng)
    (f(a), rng2)
  }

  def nonNegativeEven: Rand[Int] = map(nonNegativeEven)(i => i - i % 2)

  /*
  * reimplement this using map
  *   def double(rng: RNG): (Double, RNG) = {
    val (n, rn) = nonNegativeInt(rng)
    (n.toDouble/(Int.MaxValue.toDouble + 1), rn)
  }
  * */

  def doubleMap: Rand[Double] = map(nonNegativeInt)( i => i.toDouble/ Int.MaxValue.toDouble + 1)

  /*
  * Write the implementation of map2 based on the following signature. This function
  * takes two actions, ra and rb, and a function f for combining their results, and returns
  * a new action that combines them*/

  def map2[A, B, C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = rng => {
    val (n1, r1) = ra(rng)
    val (n2, r2) = rb(r1)

    (f(n1, n2), r2)
  }

  def both[A, B](ra: Rand[A], rb: Rand[B]): Rand[(A, B)] = map2(ra, rb)( (x, y) => (x, y))

  /*
  * Hard: If you can combine two RNG transitions, you should be able to combine a whole list of them.
  * Implement sequence for combining a List of transitions into a single transition.
  * Use it to reimplement the ints functions you wrote before.
  * For the latter, you can use the standard library function List.fill(n)(x) to make a list with x repeated n times.
  * */
  /*
  * def ints(count: Int)(rng: RNG): (List[Int], RNG) = {

    def loop(c: Int, list: List[Int], rn: RNG): (List[Int], RNG) = {
      if(c == 0) (list, rn)
      else {
        val (number, r) = rn.nextInt
        loop(c - 1, list :+ number, r)
      }
    }
    loop(count, Nil, rng)
  }
  * */

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = rng =>  {

    def loop(mkingList: List[A], rng: RNG, inputList: List[Rand[A]]): (List[A], RNG) = {
        inputList match {
          case h :: tail =>
            val (n1, r) = h(rng)
            loop(mkingList :+ n1, r, tail)
          case Nil => (mkingList, rng)
        }
    }
    loop(List.empty, rng, fs)
  }

//  val k = sequence(List(nonNegativeInt _, nonNegativeInt _, nonNegativeInt _, nonNegativeInt _))
}






























