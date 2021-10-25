package app.data.models

import java.util.Date
import javax.lang.model.`type`.NullType

class Item(var id:Int, var order:Order, var product: Product, var quantity: Int){
    def this(order:Order, product: Product, quantity: Int)=this(0,order, product, quantity)
    def this(product: Product, quantity: Int)=this(0,null, product, quantity)
}