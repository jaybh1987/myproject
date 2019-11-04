package Books.Tasking.PostFix

import scala.collection.mutable.Stack

  /*
   * its not operand means its operator.
   * so, few conditions should be taken care of.
   *
   * 1st is stack is empty then push
   *
   * 2nd if incoming operator has greater precedence then the
   * operator on the top of the stack in this case push
   *
   * 3rd if incoming operator has lower precedence then the
   * oprator on the top of the stack in this case pop the stack while
   * the operator in the stack have the same precedence as the incoming operator.
   *
   * 4th is incoming character is ) then op until (
   *
   * */

object StackPostFix {

  import scala.collection.mutable.Stack

  var ch = "A+(B*C-(D/E^F)*G)*H".toList


  def f( ch: List[Char]): Unit = {

    val opratorstack: Stack[Char] = Stack[Char]()
    val outputStack: Stack[Char] = Stack[Char]()

    for (i <- ch.indices) {

      isOprand( ch(i)) match {
        case true =>  outputStack.push(ch(i))
        case false =>

            opratorstack.isEmpty match {
              case true =>  opratorstack.push(ch(i))
              case false =>

                isBracket(ch(i)) match {
                  case true => opratorstack.push(ch(i))
                  case false => outputStack.push(opratorstack.pop)

                }
            }
      }
    }

    println(s"operator stack $opratorstack .")
    println(s"output stack $outputStack.")
  }


  def isOprand(o: Char): Boolean = o match {
    case 'A' => true
    case 'B' => true
    case 'C' => true
    case 'D' => true
    case 'E' => true
    case 'F' => true
    case 'G' => true
    case 'H' => true
    case 'I' => true
    case 'J' => true
    case 'K' => true
    case 'L' => true
    case 'M' => true
    case 'N' => true
    case 'O' => true
    case 'P' => true
    case 'Q' => true
    case 'R' => true
    case 'S' => true
    case 'T' => true
    case 'U' => true
    case 'V' => true
    case 'W' => true
    case 'X' => true
    case 'Y' => true
    case 'Z' => true
    case '^' => false
    case '*' => false
    case '/' => false
    case '+' => false
    case '-' => false
    case '(' => false
    case ')' => false
  }

  def isBracket(br: Char): Boolean = br match {
    case '(' => true
    case ')' => true
    case _ => false
  }

  def weightOfOperator(op: Char): Int = op match {
    case '^' => 10
    case '*' => 9
    case '/' => 8
    case '+' => 7
    case '-' => 6
  }

  def charactor(product: Char): Char = product match {

    case 'A' => 'A'
    case 'B' => 'B'
    case 'C' => 'C'
    case 'D' => 'D'
    case 'E' => 'E'
    case 'F' => 'F'
    case 'G' => 'G'
    case 'H' => 'H'
    case 'I' => 'I'
    case 'J' => 'J'
    case 'K' => 'K'
    case 'L' => 'L'
    case 'M' => 'M'
    case 'N' => 'N'
    case 'O' => 'O'
    case 'P' => 'P'
    case 'Q' => 'Q'
    case 'R' => 'R'
    case 'S' => 'S'
    case 'T' => 'T'
    case 'U' => 'U'
    case 'V' => 'V'
    case 'W' => 'W'
    case 'X' => 'X'
    case 'Y' => 'Y'
    case 'Z' => 'Z'
    case '^' => '^'
    case '*' => '*'
    case '/' => '/'
    case '+' => '+'
    case '-' => '-'
    case '(' => '('
    case ')' => ')'
  }
}


