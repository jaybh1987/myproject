package Books.FPScalaManning.Chapter7


trait WordCount
case class Stub(chars: String) extends WordCount
case class Part(leftStub: String, words: Int, rightStub: String) extends WordCount


object WordCount {

  /*
  * starts with space and end with space = complete word
  * starts with space but not ending with space = there may complete words but must have some word portion need to set on right
  * starts without space and end with space = there is half word after words the there few words. (half word should be set on left)
  * starts with space and ends with space = means entire word we have found there is not partial word in string.
  * Stub means we do not have encountered a whole world yet
  * Part holds portion of word for left half , count of whole word, portion of word for right half
  *
  * */

  def testFunction(s: String): List[String] = s.split("\\s+").toList

  def fromString(s: String): WordCount = {
    val words = s.split("\\s+").toList
    lazy val startsWithSpace = s.startsWith(" ")
    lazy val endsWithSpace = s.endsWith(" ")

    if(s.forall(_.isSpaceChar)) {
      Stub("")
    } else if (startsWithSpace && endsWithSpace) {
      Part("", words.length - 1, "")
    } else if (startsWithSpace) {
      Part("", words.length - 2, words.last)
    } else if (endsWithSpace) {
      Part(words.head, words.length - 1, "")
    } else if( words.length == 1) {
      Stub(words.head)
    } else {
      Part(words.head, words.length - 2, words.last)
    }
  }


  object NonEmptySpaceString {
    def unapply(s: String): Option[String] = if( s.forall(_.isSpaceChar) && s.length > 0) Some(s) else None
  }

  var wordCountMonoid = new Monoid[WordCount] {
    def op(a1: WordCount, a2: WordCount): WordCount = (a1, a2) match {
      case (Stub(leftStub), Stub(rightStub))                                =>    Stub(leftStub + rightStub)
      case (Stub(leftStub), Part("", wordCount, rightStub) )                =>    Part(leftStub, wordCount, rightStub)
      case (Part(leftStub, wordCount, ""), Stub(rightStub))                 =>    Part(leftStub, wordCount, rightStub)
      case (Stub(NonEmptySpaceString(_)), Part(_, wordCount, rightStub))    =>    Part("", wordCount + 1, rightStub)
      case (Part(leftStub, wordCount, _), Stub(NonEmptySpaceString(_)))     =>    Part(leftStub, wordCount + 1, "")
      case (Stub(leftLeftStub), Part(leftRightStub, wordCount, rightStub))  =>    Part(leftLeftStub + leftRightStub, wordCount, rightStub)
      case (Part(leftStub, wordCount, rightLeftStub), Stub(rightRightStub)) =>    Part(leftStub, wordCount, rightRightStub + rightRightStub)
      case (Part(leftStub, leftWordCount, centreLeftStub), Part(centreRightStub, rightWordCount, rightStub)) =>
                                                                                  Part(
                                                                                    leftStub,
                                                                                    leftWordCount + rightWordCount + (if ((centreLeftStub + centreRightStub == "")) 0 else 1),
                                                                                    rightStub
                                                                                  )
    }
    def zero: WordCount= Stub("")
  }
  wordCountMonoid.zero
}




















