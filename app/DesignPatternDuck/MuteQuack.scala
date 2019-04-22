package DesignPatternDuck

class MuteQuack extends QuackBehavior {

  def quack: Unit = println("Mute Quack.")

}
