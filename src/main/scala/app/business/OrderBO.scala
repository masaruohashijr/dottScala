package app.business

import app.data.models.TimeFrames
import app.data.models.TimeBox
import app.data.models.Order
import app.data.dao.OrderDao

object OrderBO{
    def filterByInterval(timeBox:TimeBox, timeFrames:TimeFrames): TimeFrames={
        var tfs = OrderDao.filterByInterval(timeBox,timeFrames)   
        tfs
    }
}