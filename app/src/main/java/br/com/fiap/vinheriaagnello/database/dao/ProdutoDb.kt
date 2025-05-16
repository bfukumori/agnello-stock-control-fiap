package br.com.fiap.vinheriaagnello.database.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.fiap.vinheriaagnello.model.Produto

@Database(entities = [Produto::class], version = 1)
abstract class ProdutoDb : RoomDatabase() {
    abstract fun produtoDAO(): ProdutoDAO
}
