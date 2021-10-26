package app.business

import app.data.models.TimeFrames
import app.data.models.TimeBox
import app.data.models.Order
import app.data.dao.OrderDao
import app.data.models.TimeFrame

object OrderBO{
    def filterByInterval(timeBox:TimeBox, timeFrames:TimeFrames): Array[TimeFrame]={
        var tfs = OrderDao.filterByInterval(timeBox,timeFrames)   
        tfs
    }
}