package app.data.models

class Customer(var id: Int, var name: String, var phone_number: String, var shipping_address: String, var credit_rating: Double){    
    override def toString = { 
        "O cliente %s, com telefone %s, e endereço de entrega em %s.".format(name, phone_number, shipping_address)
    } 
    def this(name: String, phone_number: String, shipping_address: String, credit_rating: Double)=this(0,name,phone_number,shipping_address,credit_rating)

}