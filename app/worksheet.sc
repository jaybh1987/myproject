
case class ModelFields(
                        barcode: Boolean = true,
                        vendorId: Boolean = true,
                        modelId: Boolean = true,
                        styleId: Boolean = true,
                        colorfin: Boolean = true,
                        price1: Boolean = true,
                        price2:Boolean = true,
                        price3: Boolean = true
                      ) {

  def toSeq = Seq(
      (Some("m.barcodenumber as barcode") -> barcode),
      (Some("m.vendorid as vendorId")-> vendorId),
    (Some("m.modelid as modelId")-> modelId),
    (Some("m.styleid as styleId")-> styleId),
    (Some("m.colorfin  as colorfin")-> colorfin),
    (Some("m.price1 as price1")-> price1),
    (Some("m.price2 as price2")-> price2),
    (Some("m.price3 as price3")-> price3))

  def getSelectedFields(data: Map[Some[String], Boolean]) = {
    data.collect{
      case (k, v) => if(v) k else None
      case _ => None
    }.flatten.mkString(",")
  }

  def toList = List(
    barcode,
    vendorId,
    modelId,
    styleId,
    colorfin,
    price1,
    price2,
    price3
  )
}


object ModelFields {
  def getSelectedFields(data: Map[Some[String], Boolean]) = {
    data.collect{
      case (k, v) => if(v) k else None
      case _ => None
    }.flatten.mkString(",")
  }
}


ModelFields.getSelectedFields(ModelFields(true, true,true, true, true, true,true, true).toMap)



