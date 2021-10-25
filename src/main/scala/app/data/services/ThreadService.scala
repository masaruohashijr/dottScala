package app.data.services

import java.util.ArrayList
import app.data.models.StockKeeper

object ThreadService{
    def start(sks:ArrayList[StockKeeper]){
        for(sk <- sks.toArray(Array.ofDim[StockKeeper](sks.size))) {
            new Thread(sk).start()
        }
    }
}