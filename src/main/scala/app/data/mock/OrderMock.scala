package app.data.mock
import _root_.app.data.models.Customer
import _root_.app.data.models.Order
import _root_.app.data.models.Item
import java.time.LocalDate

object OrderMock{
    def create(customer:Customer, items:Array[Item], createdAt:LocalDate):Order = {
        new Order(customer, items, createdAt)
    }
}