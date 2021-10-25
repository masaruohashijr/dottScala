package app.data.dao

import app.data.pool.ConnectionPool
import app.data.resources.ResourceManager
import app.data.models.Order
import java.sql.Date
import java.util.ArrayList
import app.data.models.Product
import java.sql.Statement


object ItemDao {
    var conn = ConnectionPool.getConnection()
    def loadItems(order:Order):Unit={
        var pstmt = conn.prepareStatement("INSERT INTO dott.item (id_order, id_product, quantity, applied_price) " +
          "VALUES(?, ?, ?, ?) RETURNING id_item")          
        var items = order.items
        for (i<-0 to items.length-1){
            pstmt.setInt(1,order.id)
            pstmt.setInt(2,items(i).product.id)
            pstmt.setInt(3,items(i).quantity)
            var applied_price = items(i).product.price.toDouble
            pstmt.setDouble(4,applied_price)
            var rs = pstmt.executeQuery()
            var id_item = 0;
            if(rs.next()){
                id_item = rs.getInt(1);
            }
            items(i).id = id_item
            items(i).order = order
        }
    }
}