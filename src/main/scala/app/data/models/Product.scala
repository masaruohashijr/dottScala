package app.data.models

import java.util.Date
import java.time.LocalDate

class Product(var id:Int, var name: String,  var category: String, var price: BigDecimal, var creation_date: LocalDate){
    override def toString = { 
        "O produto %s, da categoria %s,  custa € %s.\n".format(name, category, price.toString)
    }
    def this(name: String,  category: String, price: BigDecimal, creation_date: LocalDate)=this(0,name,category,price, creation_date)
}