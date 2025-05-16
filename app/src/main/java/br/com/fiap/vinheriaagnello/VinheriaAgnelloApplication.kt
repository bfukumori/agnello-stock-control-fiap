package br.com.fiap.vinheriaagnello

import android.app.Application
import androidx.room.Room
import br.com.fiap.vinheriaagnello.database.dao.ProdutoDb

class VinheriaAgnelloApplication : Application() {
    companion object {
        lateinit var db: ProdutoDb
            private set
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext, ProdutoDb::class.java, name = "produto_db")
            .fallbackToDestructiveMigration(true)
            .build()
    }
}