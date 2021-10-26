package app.data.models

class TimeFrame(var frame:String, var begin:String,var end:String, var qtt:Int){
    def this(frame:String, begin:String,end:String)=this(frame,begin,end,0)
}