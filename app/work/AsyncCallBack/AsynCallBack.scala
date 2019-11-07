package work.AsyncCallBack


trait OnGreekEventListener {

  def onGreekEvent: Unit
}


class B {

  private var mListener: OnGreekEventListener = null

  def registerOnGreekEventListener(mListener: OnGreekEventListener) = {
    this.mListener = mListener
  }

  def doGreekStuff = {

    new Thread( new Runnable {
      override def run(): Unit = {
        println("performing operation in Asynchronous Task.")

        if(mListener != null) {
          mListener.onGreekEvent
        }
      }
    }).start()
  }
}

class A  extends OnGreekEventListener {
  override def onGreekEvent: Unit = println("performing callback after Asynchronous task.")
}