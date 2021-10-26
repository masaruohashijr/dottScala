package app.data.models

class TimeFrames(var frames:Array[String]){
    var bands:Array[TimeFrame] = new Array[TimeFrame](frames.length)
    def this()=this(new Array[String](0))
    var counter = 0
    def toArray():Array[TimeFrame]={
        for(f<-frames){
            var a = f.split("-")
            var begin = a(0)
            var end = a(0)
            if(a.length > 1){
                end = a(1)
            }
            bands(counter) = new TimeFrame(f,begin,end)
            counter+=1
        }
        bands
    }
}
