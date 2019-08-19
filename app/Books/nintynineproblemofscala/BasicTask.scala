package Books.nintynineproblemofscala

class BasicTask {

}


object Algorithm{


  def fun(arr: scala.collection.mutable.ArrayBuffer[Int]) = {

    val leftSide = arr(0)

    val ans = for{
      i <- 1 until arr.length
      j <- 2 until arr.length
      maxRightSide = if(arr(i) < arr(j)) arr(i) else arr(j)
    }yield(maxRightSide)
    val output = Array(leftSide, ans.max).min * ( arr.length - 1)
    output
  }
}