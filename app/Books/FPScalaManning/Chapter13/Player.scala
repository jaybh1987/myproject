package Books.FPScalaManning.Chapter13

trait IO { self =>
  def run: Unit
  def ++(io: IO): IO = new IO {
    def run {  self.run; io.run  }
  }
}

object IO {
  def empty: IO = new IO { def run = () }
}

case class Player(name: String, score: Int) {

  /*
  * I/O and pure logic is mixed here.
  * */
  def contest(p1: Player, p2: Player) : Unit = {
    if(p1.score > p2.score) {
      println(s"${p1.name} is the winner.")
    } else if(p2.score > p1.score) {
      println(s"${p2.name} is the winner.")
    } else {
      println(s"It's a draw.")
    }
  }

  /*
  * Pure Logic*/
  def winner(p1: Player, p2: Player): Option[Player] = if(p1.score > p2.score) Some(p1) else if (p2.score > p1.score) Some(p2) else None

  /*
  * Here match block has the respoinsibility to display on console.
  * */
  def contest2(p1: Player, p2: Player): Unit = winner(p1, p2) match {
    case Some(Player(name, _ )) => println(s"${name} is the winner")
    case None => println("It's a draw")
  }

  /*
  * Further refactoring also possible as contest2 function has two responsibilities.
  * its computing which message to display and then display it.
  * lets factor out pure function here as well.
  * */

  def winnerMsg(p: Option[Player]): String = p.map{
    case Player(name, _ ) => s"$name is the winner. "
  }.getOrElse("It's a draw")


  /*
  * Note the side effect println is now only the outermost layer of the program
  * what is inside of println is pure expression.
  *
  *
  * inside every function with side effects is a pure function waiting to get out.
  * */
  def contest3(p1: Player, p2: Player): Unit = println(winnerMsg(winner(p1, p2)))


  /*
  * given an impure function f of type A => B,
  * we can splite f into two functions
  *     a pure function of type A => D, where D is some descrition of the result of f
  *     an impure function of type D => B, which can be tought of as an interpreter of these descriptions.
  *
  * */
  def PrintLine(msg: String):IO = new IO {
    def run: Unit = println(msg)
  }

  def contest4(p1: Player, p2: Player): IO = PrintLine(winnerMsg(winner(p1, p2)))

}
























