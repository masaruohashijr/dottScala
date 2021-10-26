package app.data.models

import app.business.OrderBO
import app.view.helper.RowHelper

class StockKeeper(
    var name: String,
    var timeBox: TimeBox,
    var timeFrames: TimeFrames
) extends Runnable {

  var rows: Array[String] = new Array[String](0)

  override def toString = {
    "O mantenedor do estoque é %s.".format(name)
  }
  def run(): Unit = {
    var slots: Array[TimeFrame] = OrderBO.filterByInterval(timeBox, timeFrames)
    rows = RowHelper.render(slots)
    synchronized {
      println("-----------------------")
      println(name)
      println(".......................")
      var last: String = ""
      for (row <- rows) {
        if (row.contains(">")) {
          last = row
        } else {
          println(row)
        }
      }
      println(last)
      println()
      println()
    }
  }
}
