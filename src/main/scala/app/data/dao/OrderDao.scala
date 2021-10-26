package app.data.dao

import app.data.pool.ConnectionPool
import app.data.resources.ResourceManager
import app.data.models.Order
import app.data.dao.ItemDao
import java.util.ArrayList
import app.data.models.Product
import java.sql.Statement
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import app.data.models.TimeFrames
import app.data.models.TimeBox
import java.time.format.DateTimeFormatter
import app.data.models.TimeFrame

object OrderDao {

  var conn = ConnectionPool.getConnection()

  def loadOrders(orders: Array[Order]): Array[Order] = {
    var stmt = conn.createStatement()
    stmt.execute(s"DELETE FROM dott.item")
    stmt.execute(s"DELETE FROM dott.order")
    for (i <- 0 to orders.length - 1) {
      var pstmt = conn.prepareStatement(
        "INSERT INTO dott.order (id_customer, created_at)" +
          " VALUES(?, ?) returning id_order".stripMargin
      )
      pstmt.setInt(1, orders(i).customer.id)
      var creation = orders(i).creation_date
      pstmt.setDate(2, java.sql.Date.valueOf(creation))
      var rs = pstmt.executeQuery()
      var id_order = 0;
      if (rs.next()) {
        var id_order = rs.getInt(1)
        orders(i).id = id_order
        ItemDao.loadItems(orders(i))
      }
    }
    return orders
  }
  def filterByInterval(timeBox: TimeBox, timeFrames: TimeFrames): Array[TimeFrame] = {
    val frames = timeFrames.frames
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val startAt = LocalDate.parse(timeBox.start, formatter);
    val endAt = LocalDate.parse(timeBox.end, formatter);
    val stmt = conn.createStatement()
    var sb = new StringBuffer()
    var bands = timeFrames.toArray()
    
    sb.append("select r2.time_band,sum(r2.qtd) from (select case ")
    var else_part = ""
    for (band <- bands) {
      if(band.frame.indexOf(">")>=0){
        else_part = band.frame 
      } else {
        sb.append(" when r1.age between "+band.begin+" and "+band.end+" then '"+band.frame+"' ")
      }
    }
    sb.append(" else '"+else_part+"' ")
    sb.append(" end as time_band, ")
    sb.append(" count(1) as qtd from ( ")
    sb.append(" select distinct c.id_order,  ")
    sb.append(" (date_part('month','"+timeBox.end+"'::date)-date_part('month',a.created_at::date) ")
    sb.append(" +(date_part('year','"+timeBox.end+"'::date)-date_part('year',a.created_at::date))*12 ")
    sb.append(" ) as age   ")
    sb.append(" from dott.product a  ")
    sb.append(" inner join dott.item b on a.id_product = b.id_product ")
    sb.append(" inner join dott.order c on b.id_order = c.id_order  ")
    sb.append(" where c.created_at between '"+timeBox.start+"' and '"+timeBox.end+"') r1 ")
    sb.append(" group by r1.age ")
    sb.append(" order by 1) r2 ")
    sb.append(" group by r2.time_band  ")
    //println(sb.toString())
    var rs = stmt.executeQuery(sb.toString())
    var array_list = new ArrayList[Product]()
    var counter = 0
    while (rs.next()) {
      var band = timeFrames.bands(counter)
      band.frame = rs.getString(1)
      band.qtt = rs.getInt(2)
      counter+=1
    }
    bands
  }
}
