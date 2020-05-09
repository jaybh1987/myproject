package Books.FPScalaManning.Chapter1


/*
*
* class Cafe {
*
*   def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
*
*     val cup = new Coffee
*     (cup, Charge(cc,cup.price))
*   }
*
* }
*
* case class Charge(cc: CreditCard, amount: Double) {
*   def combine(other: Chareg): Charge = if( cc == other.cc) Charge(cc,amount + other.amount)
*   else throw new Exception( "can't combine charges to different cards")
* }
*
* class Cafe {
*
*   def buyCoffee(cc: CreditCard): (Coffee, Charge) = {
*     val cup = new Coffee
*
*     (cup, Charge(cc, cup.price) )
*   }
*
*   def buyCoffees(cc: CreditCard, n: Int): (List[Coffee], Charge) = {
*     val purchases: List[(Coffee, Charge)] = List.fill(n)(buyCoffee(cc))
*     val (coffees, charges) = purchases.unzip
*
*     (coffees, charges.reduce( (c1, c2) => c1.combine(c2)))
*   }
*
* }
*
* */
