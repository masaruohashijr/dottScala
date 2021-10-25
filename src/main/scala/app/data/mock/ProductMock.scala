package app.data.mock

import _root_.app.util.Utils
import _root_.app.data.models.Product
import java.time.LocalDate
import _root_.app.data.resources.ResourceManager
import _root_.app.controller.Main

object ProductMock{
    def create():Product = {
        var name = mockName()
        var category = mockCategory()
        var price = mockPrice()
        var creation_date = mockCreationDate(Main.CREATION_DATE_PRODUCT_YEARS_AGO)
        new Product(name,category,price, creation_date)
    }

    def mockName():String = {
        Utils.randomString(9)
    }
    def mockCategory():String = {
        CategoryMock.mock()
    }
    def mockPrice():BigDecimal = {
        val r = new scala.util.Random
        var price = r.between(5.toFloat, 1000.toFloat)
        BigDecimal(price).setScale(2, BigDecimal.RoundingMode.HALF_UP)
    }

    def mockCreationDate(yearsAgo:Int):LocalDate={
        Utils.randomDate(yearsAgo)
    }

}