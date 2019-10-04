package Books.nintynineproblemofscala

object BasicTask {



}


object Task {

  val a = List(1,5,20,30,10,40)

  def fun(a: scala.collection.mutable.ArrayBuffer[Int]) = {

    var i = 0
    var atTop = (0,0)
    var atSecondTop = (0,0)

    // val a = scala.collection.mutable.ArrayBuffer[Int](1,120,1, 120)

    a.zipWithIndex.map {
      case (value, position) => if( value > atTop._1) atTop = (value, position)
    }

    a.zipWithIndex.filter( x => x._2 != atTop._2).map {
      case (value, position) => if( value > atSecondTop._1) atSecondTop = (value, position)
    }

    val data = Seq(atTop, atSecondTop)


    val min_value = data.map(tup => tup._1).min
    val max_position = data.map( tup => tup._2).max
    val min_position = data.map( tup => tup._2).min

    min_value * (max_position - min_position)

  }


}