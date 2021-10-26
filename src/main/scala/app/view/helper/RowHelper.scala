package app.view.helper

import app.data.models.TimeFrames
import app.data.models.TimeFrame

object RowHelper{
    def render(slots:Array[TimeFrame]):Array[String]={
        var rows = new Array[String](slots.length)
        var counter = 0
        for(s<-slots){
            rows(counter)=s.frame+" months: "+s.qtt+" orders\n"
            counter+=1
        }
        rows
    }
}