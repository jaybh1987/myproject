package Books.FPScalaManning.Chapter1

import scala.annotation.tailrec

object MyModule {

  def abs(n: Int): Int = if(n < 0) -n else n

  def factorial(x: Int): Int = if(x <= 0) 1 else x * factorial(x - 1)

  private def formatAbs(x: Int): String = {
    val msg = "The absolute value of %d is %d"
    msg.format(x, abs(x))
  }

  private def formatFactorial(n: Int): String = {
    val msg = "The factorial of %d is %d"
    msg.format(n, factorial(n))
  }

  private def formatResult(name: String,n: Int,f: Int => Int): String = {
    val msg = "The %s of %d is %d"
    msg.format(name, n, f(n))
  }

  def main(args: Array[String]): Unit = {
    println(formatAbs(-42))
    println(factorial(5))
    println(formatResult("absolute value", -42, abs))
  }
}
