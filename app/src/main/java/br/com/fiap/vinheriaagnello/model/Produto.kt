package br.com.fiap.vinheriaagnello.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_produtos")
data class Produto(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val nome: String = "",
    @ColumnInfo(name = "qtd") val quantidade: Int,
)