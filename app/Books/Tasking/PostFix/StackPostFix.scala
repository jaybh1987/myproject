package Books.Tasking.PostFix

import scala.collection.mutable.Stack

object StackPostFix {

  import scala.collection.mutable.Stack

  //var instr = "A+(B*C-(D/E^F)*g)*H".toList
  var instr = "A+".toList

  val operatorStack : Stack[Char] = Stack[Char]()
  val postfixStack: Stack[Char] = Stack[Char]()

  for (i <- instr.indices){

    val k = instr(i)

    k match {
      case 'A' => postfixStack.push('A')

      case '+' => /*
        if scanned operator has higher precedence then the operator in the stack
        in that case push the operator
        if scanned operator has lower precedence then the operator in the stack
        then pop the stack operator and push the lower precedence operator in the stack
      */
    }
  }

}

object Utils {

  def weightOfOperator(op: Char): Int = op match {
    case '^' => 10
    case '*' => 9
    case '/' => 8
    case '+' => 7
    case '-' => 6
  }
}


