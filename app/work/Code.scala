package work

import java.awt.image.BufferedImage
import java.io.{File, FileOutputStream}

import org.krysalis.barcode4j.impl.code128.Code128Bean
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider
import org.krysalis.barcode4j.tools.UnitConv

import java.io.IOException

class CodeB {

  val barcodePath = "/home/laitmatus/Desktop/"

  def fun(fileName:String) = {
    try {
      val bean = new Code128Bean()
      val dpi = 160
      bean.setModuleWidth(UnitConv.in2mm(2.8f / dpi))
      bean.doQuietZone(false)
      val outPutFile = new File(barcodePath + fileName + ".png")
      val out = new FileOutputStream(outPutFile)
      val canvas = new BitmapCanvasProvider(
        out,
        "image/x-png",
        dpi,
        BufferedImage.TYPE_BYTE_BINARY,
        false,
        0
      )
      bean.generateBarcode(canvas, fileName)
      canvas.finish()
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }
}



