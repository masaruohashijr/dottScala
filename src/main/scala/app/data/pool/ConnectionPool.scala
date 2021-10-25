package app.data.pool

import org.apache.commons.dbcp2._
import java.net.URI
import java.util.Properties
import java.sql.Connection

object ConnectionPool {
    
  val dbUrl = s"jdbc:postgresql://localhost:5432/dott?user=postgres"
  private val connectionPool = new BasicDataSource()
  connectionPool.setUsername("postgres")
  connectionPool.setPassword("admin")
  connectionPool.setDriverClassName("org.postgresql.Driver")
  connectionPool.setUrl(dbUrl)
  connectionPool.setInitialSize(10)

  def getConnection():Connection={
    ConnectionPool.connectionPool.getConnection
  }

}

