package app.controller

import app.data.models.TimeBox
import app.data.models.TimeFrames
import app.data.resources.ResourceManager
import app.data.resources.StockKeeperManager

object Main extends App {  
  val YEARS_AGO: Int = 3
  val CREATION_DATE_PRODUCT_YEARS_AGO: Int = 8
  ResourceManager.ReloadDatabase(YEARS_AGO)

  StockKeeperManager.newKeeper("João",new TimeBox("2018-01-01","2018-12-31"),new TimeFrames(Array("1-3","4-6","7-12",">12")))
  StockKeeperManager.newKeeper("Armando",new TimeBox("2019-01-01","2019-12-31"),new TimeFrames(Array("1-2","3-4","5-6","7-8","9-10","11-12",">12")))
  StockKeeperManager.newKeeper("Luís",new TimeBox("2018-01-01","2018-12-31"),new TimeFrames(Array("1-4","5-8","9-12",">12")))
  StockKeeperManager.newKeeper("Filipe",new TimeBox("2020-01-01","2020-12-31"),new TimeFrames(Array("1-6","7-12",">12")))
  StockKeeperManager.newKeeper("Vasco",new TimeBox("2019-01-01","2019-12-31"),new TimeFrames(Array("1-5","6-10","11-12",">12")))
  
  StockKeeperManager.startKeepers()
  StockKeeperManager.printReports()
}

