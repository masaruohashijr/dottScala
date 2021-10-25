package app.data.dao

import app.data.pool.ConnectionPool
import app.data.models.Product
import java.util.ArrayList
import app.data.resources.ResourceManager
import java.time.LocalDate

object ProductDao{    
    
    def loadProducts(products:Array[Product]):Array[Product]={
        var conn = ConnectionPool.getConnection()
        var stmt = conn.createStatement()
        stmt.execute(s"DELETE FROM dott.product")
        var pstmt = conn.prepareStatement("INSERT INTO dott.product (name, category, price, created_at)" +
          " VALUES(?, ?, ?, ?)")
        var counter:Int = 0
        for (i<-0 to products.length-1){
            pstmt.setString(1,products(i).name)
            pstmt.setString(2,products(i).category)
            pstmt.setDouble(3,products(i).price.toDouble)
            var creation = products(i).creation_date
            pstmt.setDate(4, java.sql.Date.valueOf(creation))
            pstmt.addBatch()
            counter+=1
            if( counter%ResourceManager.BATCH_SIZE == 0 ){
                pstmt.executeBatch()
            }
        }
        pstmt.executeBatch()
        stmt = conn.createStatement()
        var rs = stmt.executeQuery("SELECT id_product, name, category, price, created_at FROM dott.product")
        var array_list = new ArrayList[Product]()
        while (rs.next()) {
            var id: Int = rs.getInt(1)
            var name: String = rs.getString(2)
            var category: String = rs.getString(3)
            var price: Double = rs.getDouble(4)
            var created_at: LocalDate = rs.getDate(5).toLocalDate()
            var product = new Product(id, name, category, price, created_at)
            array_list.add(product)
        }
        return array_list.toArray(Array.ofDim[Product](array_list.size))
    }
}