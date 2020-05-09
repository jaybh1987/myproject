package utils

import java.io.FileOutputStream

import org.apache.poi.xssf.usermodel.XSSFWorkbook

object ExcelUtil {


  def exportExcep = {

    val workbook = new XSSFWorkbook()
    val sheet = workbook.createSheet("scala books.")

    val bookData =  List(
      List("headfirst java", "kathy serria", 79),
      List("java", "kathy bloch", 73),
      List("clean code", "kathy serria", 73),
      List("thinking in java", "kathy serria", 73)
    )

    var rowcount = 0
    var columnCount = 0


    val k = for {

      abook <- bookData

      row = sheet.createRow( {rowcount += 1; rowcount})

      counter <- 0 until abook.length

      cell = row.createCell(counter)


      out = if (abook(counter).isInstanceOf[String]){
        println("yes String."+abook(counter).asInstanceOf[String])
        cell.setCellValue(abook(counter).asInstanceOf[String])
      } else {
        println("yes int.")
        cell.setCellValue(abook(counter).asInstanceOf[Int])
      }
    } yield()


    try{
      val outputStream: FileOutputStream = new FileOutputStream("/tmp/scalabook.xlsx")

      workbook.write(outputStream)
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }




}
