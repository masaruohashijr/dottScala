package app.data.resources

import app.data.models.TimeBox
import app.data.models.TimeFrames
import app.data.models.StockKeeper
import java.util.ArrayList
import app.data.services.ThreadService
import app.data.services.ReportService

object StockKeeperManager{

    var sks:ArrayList[StockKeeper] = new ArrayList[StockKeeper](0)

    def newKeeper(name:String, timeBox:TimeBox, timeFrames:TimeFrames){
        var sk = new StockKeeper(name, timeBox, timeFrames)
        sks.add(sk)
    }

    def startKeepers(){
        ThreadService.start(sks)
    }

    def printReports(){
        for(sk<-sks.toArray(Array.ofDim[StockKeeper](sks.size))){
            println(sk.rows.length)
            sk.rows.synchronized {
                for(row<-sk.rows){
                    println(row)
                }
            }
        }
    }
}