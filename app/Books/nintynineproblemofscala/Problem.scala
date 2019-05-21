package Books.nintynineproblemofscala

class Problem {


  def flatMapSublists[A, B](ls: List[A])(f: (List[A] => List[B])): List[B] = {
    ls match {
      case Nil => Nil
      case subList@(_ :: tail) => f(subList) ::: flatMapSublists(tail)(f)
    }
  }
  //explain execution
  /** assume input is List(1,2,3)(helperFunction)
    *
    * case subList@(_ :: tail) => helperFunction(List(1,2,3)) ::: flatMapSublists(2,3)(helperFunction)    List(2, 3, 4) ::: (List(3, 4) ::: (List(4) ::: Nil))
    * case subList@(_ :: tail) => helperFunction(List(2,3)) ::: flatMapSublists(3)(helperFunction)    List(3, 4) ::: (List(4) ::: Nil)
    * case subList@(_ :: tail) => helperFunction(List(3)) ::: flatMapSublists(Nil)(helperFunction)   returns (List(4) ::: Nil)
    *
    *
    */

  def combinations[A](n: Int, ls: List[A]): List[List[A]] = {
      if(n == 0) List(Nil)
      else flatMapSublists(ls){ ls =>
          combinations(n - 1, ls.tail).map{ls.head :: _}
      }
  }

  /**
    * assume input is (3,List(1,2,3))
    * flatMapSublists(List(1,2,3){ ls => combinations(3 - 1, List(2,3))
    * */

}


object Problem {

  def helperFunction(list : List[Int]): List[Int] = {
    list.map(x => x + 1)
  }
}