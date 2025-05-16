package br.com.fiap.vinheriaagnello.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.fiap.vinheriaagnello.model.Produto

@Dao
interface ProdutoDAO {

    @Insert
    suspend fun salvar(produto: Produto): Long

    @Update
    suspend fun atualizar(produto: Produto): Int

    @Delete
    suspend fun excluir(produto: Produto): Int

    @Query("SELECT * FROM tbl_produtos ORDER BY nome ASC")
    suspend fun listarProdutos(): List<Produto>

    @Query("SELECT * FROM tbl_produtos WHERE nome=:nome")
    suspend fun buscarProdutoPorNome(nome: String): Produto?
}