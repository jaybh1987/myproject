package Books.problemsOfScala

class Example

object Example {

  //simple recursive
  //(3, List(1,2,3,4,5,6,7,8))
  def dropRecursive(n: Int, ls: List[Int]): List[Int] = {

    def dropR(c: Int, cursivList: List[Int]): List[Int] = {

      (c, cursivList) match {
        case (_, Nil) => Nil
        case (1, _ :: tail) => dropR(n, tail)
        case (_, h :: tail) => h :: dropR(c -1, tail)
          /*
          execution order sample
          1 :: dropR(3 - 1 = 2, List(2,3,4,5,6,7,8) 1 :: 2 :: 4 :: 5 :: 7 :: 8 :: Nil
          2 :: dropR(2 - 1 = 1, List(3,4,5,6,7,8) 2 :: 4 :: 5 :: 7 :: 8 :: Nil
               dropR(3, List(4,5,6,7,8)  4 :: 5 :: 7 :: 8 :: Nil
          4 :: dropR(3-2=2, List(5,6,7,8) 4 :: 5 :: 7 :: 8 :: Nil
          5 :: dropR(2-1=1, List(6,7,8)   5 :: 7 :: 8 :: Nil
               dropR(3, List(7,8)      7 :: 8 :: Nil
          7 :: dropR(3-1=2, List(8)    7 :: 8 :: Nil
          8 :: dropR(2-1=1, Nil) returns Nil  // 8 :: Nil
          */
      }
    }
    dropR(n, ls)
  }

}


case class TemplateData(id: Long, price: Double, name: String)

case class SubModel(id: Long, price: Double, name: String)










