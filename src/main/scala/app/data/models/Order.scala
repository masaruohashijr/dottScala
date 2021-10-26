package app.data.models

import java.util.Date
import java.time.LocalDate

class Order(var id:Int, var customer: Customer, var items: Array[Item], var creation_date: LocalDate){
    override def toString = { 
        var total:BigDecimal = 0
        for(i <- 0 to items.length-1){
            var price = items(i).product.price
            total += price
        }
        "O pedido de %s contendo %d itens foi realizado em %s. Total: € %s.\n".format(customer.name, items.length, creation_date, total.toString())
    }       
    def this(customer: Customer, items: Array[Item], creation_date: LocalDate)=this(0,customer,items,creation_date)
    def this()=this(0,null,null,null)
}