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


object OrderDao {
    
    var conn = ConnectionPool.getConnection()
    
    def loadOrders(orders:Array[Order]):Array[Order]={
        var stmt = conn.createStatement()
        stmt.execute(s"DELETE FROM dott.item")
        stmt.execute(s"DELETE FROM dott.order")
        for (i<-0 to orders.length-1){
            var pstmt = conn.prepareStatement("INSERT INTO dott.order (id_customer, created_at)" +
                " VALUES(?, ?) returning id_order".stripMargin)
            pstmt.setInt(1,orders(i).customer.id)
            var creation = orders(i).creation_date
            pstmt.setDate(2, java.sql.Date.valueOf(creation))
            var rs = pstmt.executeQuery()
            var id_order = 0;
            if(rs.next()){
                var id_order = rs.getInt(1)
                orders(i).id = id_order
                ItemDao.loadItems(orders(i))	
            }
        }
        return orders
    }
    def filterByInterval(timeBox:TimeBox,timeFrames:TimeFrames):TimeFrames={
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val startAt = LocalDate.parse(timeBox.start, formatter);
        val endAt = LocalDate.parse(timeBox.end, formatter);
        val stmt = conn.createStatement()
        new TimeFrames()
    }
}