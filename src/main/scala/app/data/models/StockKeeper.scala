package app.data.models

class StockKeeper(var name: String, var yearsAgo: Int) extends Runnable{    
    override def toString = { 
        "O mantenedor do estoque é %s.".format(name)
    } 
    def run(): Unit = {
        
    }
}