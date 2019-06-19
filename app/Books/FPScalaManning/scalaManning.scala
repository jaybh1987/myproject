package Books.FPScalaManning

object ScalaManning {

  //monomorphic function to find a string in array.
  def findFirst(ss: Array[String], key: String): Int = {
    def loop(n: Int): Int = {

      if(n >= ss.length)  -1
      else if( ss(n) == key) n
      else loop(n + 1)
    }
    loop(0)
  }

  //polymorphic function to find a string in array.
  def findFirst[A](ss: Array[A], p: (A) => Boolean): Int = {

    def loop(n: Int): Int = {
      if(n >= ss.length) -1
      else if( p(ss(n)) ) n
      else loop(n + 1)
    }
    loop(0)
  }


  def fun(x: Int): Int = {

    def loop(x: Int,fst: Int, snd: Int ): Int = {
      if(x <= 0) {
        fst     
      } else {
        loop(x - 1, snd, fst + snd)
      }
    }
    loop(x, 0, 1)
  }

  val ar = Array(1,2,3)

  def fun(a: Int, b: Int): Boolean = a > b

  def isSorted[A](ar: Array[A], f:(A, A)=> Boolean) = {
    def loop(n: Int): Boolean = {
      if(n >= ar.length - 1) true
      else if ( f( ar(n), ar(n + 1)) ) loop(n + 1)
      else false
    }
    loop(0)
  }

}

trait Ex {
  def partial11[A, B, C](a: A, f:(A, B) => C): B => C
}

