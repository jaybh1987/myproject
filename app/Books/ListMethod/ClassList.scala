package Books.ListMethod

class ClassList{
    //(1)(List(1,2,3,4))
    def fLeft(l: List[Int])(x: Int)(f: (Int, Int) => Int): Int = {

      var start = x
      var list = l

      while(!list.isEmpty) {
        start = f(start, l.head)
        list = list.tail
      }
      start
    }

  def genericFold[A, B](l: List[A])(start: B)(f:(B, A) => B):B = {

    var initial = start
    var list = l

    while(!list.isEmpty) {
      initial = f(initial, l.head)
      list = list.tail
    }
    initial
  }


  def myMap[A, B](list: List[A])(f: (A) => B): List[B] = {

    list match {
      case Nil => Nil
      case h :: tail => f(h) :: myMap(tail)(f)
    }

  }


}


object ClassList {

  def labelEncode (replcost: String, encode: String): String = {
    var result = ""
     if(replcost.split('.').size == 2 ) {
      result = labelEncodeWithDecimal(replcost, encode)
     }else {
       result = encoderFunction(replcost, encode)
     }
      result
  }

  //WHVQPBGXZF
  def labelEncodeWithDecimal(replcost: String, encodeString: String): String = {

    val parts = replcost.split('.')
    val partNumber = parts(0)
    var partDecimal= parts(1)

    encoderFunction(partNumber, encodeString) + "." + encoderFunction(partDecimal, encodeString)
  }

  def encoderFunction(str: String, encode: String): String = {
    val result = for {
      i <- str
      myString = i match {
        case '0' => encode.takeRight(1)
        case _ => encode.charAt(i.asDigit - 1)
      }
    }yield (myString)
    result.mkString
  }

  def dropNthEle(n: Int, ls: List[Int]): List[Int] = {
    def dropR(c: Int, cursiveList: List[Int]) : List[Int] = {
      (c, cursiveList) match {
        case (_, Nil) => Nil
        case (1, _ :: tail) => dropR(n, tail)
        case (_, h :: tail) => h :: dropR(c - 1 ,tail)
      }
    }
    dropR(n, ls)
  }


  def flattenNestList(ls: List[Any]) : List[Any] = ls.flatMap {
    case ms: List[_] => flattenNestList(ms)
    case e => List(e)
  }

  def duplicate[A](ls: List[A]): List[A] = ls.flatMap( e => List(e, e))

  def splitList(n: Int, ls: List[Int]) : (List[Int], List[Int]) = (n, ls) match {

    case (_, Nil) => (Nil, Nil)
    case (0, list) => (Nil, list)
    case (n, h :: tail) => {
        val (pre, post) = splitList(n - 1, tail)
      (h :: pre, post)
    }
  }


//  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match {
//    case List(Some(value)) => value
//    case _ => value
//  }

  def sequence[A](a: List[Option[A]]): List[A] = a match {
    case Some(h) :: tail => h :: sequence(tail)
    case Nil => Nil
  }
}

















