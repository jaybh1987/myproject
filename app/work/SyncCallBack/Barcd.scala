package work.SyncCallBack

trait OnGreekEventListner{

  def onGreekEvent(): Unit
}


class B {

  private var mListner: OnGreekEventListner = null

  //setting listioner
  def registerOnGreekEventListner(mListner: OnGreekEventListner) = {
    this.mListner = mListner
  }

  //my synchronous task
  def doGreekStuff() = {
    println("perform any operation.")
    println("performing callback before synchonorous task.")

    if(this.mListner != null) {
      mListner.onGreekEvent()
    }
  }
}


class A extends OnGreekEventListner {

  override def onGreekEvent(): Unit = {
    println("performing callback after synchonorous task.")
    println("performing routine operation.")
  }
}