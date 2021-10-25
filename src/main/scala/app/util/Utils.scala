package app.util

import java.time.LocalDate
import java.time.temporal.ChronoUnit.DAYS
import scala.util.Random

object Utils {
  val self = new scala.util.Random
  def randomString(length: Int) = {
    val sb = new StringBuilder
    for (i <- 1 to length) {
      sb.append(nextPrintableChar())
    }
    sb.toString
  }
  def randomPhoneNumber(length: Int) = {
    val low  = 48
    val high = 57
    val sb = new StringBuilder
    for (i <- 1 to length) {
      var sorted = self.nextInt(high - low) + low
      sb.append(sorted.toChar)
    }
    sb.toString
  }
  def nextPrintableChar(): Char = {
    val low  = 32
    val high = 90
    var sorted = self.nextInt(high - low) + low
    if (sorted > 32 && sorted <65) {
      return nextPrintableChar()
    } 
    sorted.toChar
  }
  def randomDate(qttYears:Int): LocalDate = {
    var to = LocalDate.now();
    var from = LocalDate.now().minusYears(qttYears);
    val diff = DAYS.between(from, to)
    val random = new Random(System.nanoTime)
    from.plusDays(random.nextInt(diff.toInt))
  }
  def randomInt(interval:Int): Int = {
     self.nextInt(interval)
  }
}