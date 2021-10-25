package app.data.mock

import _root_.app.util.Utils
import _root_.app.data.models.Customer

object CustomerMock {

    def create():Customer = {
        var name = mockName()
        var phone_number = mockPhoneNumber()
        var shipping_address = mockShippingAddres()
        var credit_rating = mockCreditRating()
        new Customer(name,phone_number,shipping_address, 1)
    }

    def mockName():String = {
        Utils.randomString(9)
    }
    def mockPhoneNumber():String = {
        Utils.randomPhoneNumber(13)
    }
    def mockShippingAddres():String = {
        Utils.randomString(100)
    }
    def mockCreditRating():Int = {
        Utils.randomInt(4)
    }

}