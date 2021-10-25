package app.data.dao

import app.data.pool.ConnectionPool
import app.data.models.Customer
import java.{util => ju}
import app.data.resources.ResourceManager


object CustomerDao {
    def loadCustomers(customers:Array[Customer]):Array[Customer]={
        var conn = ConnectionPool.getConnection()
        var stmt = conn.createStatement()
        stmt.execute(s"DELETE FROM dott.item")
        stmt.execute(s"DELETE FROM dott.order")
        stmt.execute(s"DELETE FROM dott.customer")
        var pstmt = conn.prepareStatement("INSERT INTO dott.customer (name, shipping_address, phone_number, credit_rating)" +
          " VALUES(?, ?, ?, ?)")
          var counter:Int = 0
          for (i<-0 to customers.length-1){
            pstmt.setString(1,customers(i).name)
            pstmt.setString(2,customers(i).shipping_address)
            pstmt.setString(3,customers(i).phone_number)
            pstmt.setDouble(4,customers(i).credit_rating)
            pstmt.addBatch()
            counter+=1
            if( counter% ResourceManager.BATCH_SIZE == 0 ){
                pstmt.executeBatch()
            }
        }
        pstmt.executeBatch()
        stmt = conn.createStatement()
        var rs = stmt.executeQuery("SELECT id_customer, name, shipping_address, phone_number, credit_rating FROM dott.customer")
        var array_list = new ju.ArrayList[Customer]()
        while (rs.next()) {
            var id: Int = rs.getInt(1);
            var name: String = rs.getString(2);
            var shipping_address: String = rs.getString(3);
            var phone_number: String = rs.getString(4);
            var credit_rating: Double = rs.getDouble(5);
            var customer = new Customer(id, name, shipping_address, phone_number, credit_rating)
            array_list.add(customer)
        }
        return array_list.toArray(Array.ofDim[Customer](array_list.size))
    }

}