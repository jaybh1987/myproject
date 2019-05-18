
object ScalaManning {

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