package br.com.fiap.vinheriaagnello.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.vinheriaagnello.VinheriaAgnelloApplication
import br.com.fiap.vinheriaagnello.model.Produto
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProdutoViewModel() : ViewModel() {
    private val _produtoDAO = VinheriaAgnelloApplication.db.produtoDAO()
    private val _eventoSnackbar = MutableSharedFlow<String>()
    val eventoSnackbar = _eventoSnackbar.asSharedFlow()

    private val _produtos = MutableStateFlow<List<Produto>>(emptyList())
    private val _nome = MutableStateFlow("")
    private val _quantidade = MutableStateFlow("")
    private val _novoNome = MutableStateFlow("")
    private val _novaQuantidade = MutableStateFlow("")

    val produtos = _produtos.asStateFlow()
    val nome = _nome.asStateFlow()
    val quantidade = _quantidade.asStateFlow()
    val novoNome = _novoNome.asStateFlow()
    val novaQuantidade = _novaQuantidade.asStateFlow()

    private val _mostrarAtualizarProdutoForm = MutableStateFlow(false)
    val mostrarAtualizarProdutoForm = _mostrarAtualizarProdutoForm.asStateFlow()

    fun abreAtualizarProdutoForm(produto: Produto) {
        _novoNome.value = produto.nome
        _novaQuantidade.value = produto.quantidade.toString()
        _mostrarAtualizarProdutoForm.value = true
    }

    fun fechaAtualizarProdutoForm() {
        _mostrarAtualizarProdutoForm.value = false
    }

    fun onNomeChange(novoNome: String) {
        _nome.value = novoNome
    }

    fun onQuantidadeChange(novaQuantidade: String) {
        _quantidade.value = novaQuantidade
    }

    fun onNovoNomeChange(novoNome: String) {
        _novoNome.value = novoNome
    }

    fun onNovaQuantidadeChange(novaQuantidade: String) {
        _novaQuantidade.value = novaQuantidade
    }

    fun listarProdutos() {
        viewModelScope.launch {
            _produtos.value = _produtoDAO.listarProdutos()
        }
    }

    fun salvar(produto: Produto) {
        if (produto.nome.isBlank()) return

        viewModelScope.launch {
            val existente = _produtoDAO.buscarProdutoPorNome(produto.nome)

            if (existente != null) {
                val produtoAtualizado = existente.copy(
                    quantidade = existente.quantidade + produto.quantidade
                )
                _produtoDAO.atualizar(produtoAtualizado)
                _eventoSnackbar.emit("Produto atualizado com nova quantidade!")
            } else {
                _produtoDAO.salvar(produto)
                _eventoSnackbar.emit("Produto cadastrado com sucesso!")
            }
            listarProdutos()
            _nome.value = ""
            _quantidade.value = ""

        }
    }

    fun atualizar(produto: Produto) {
        viewModelScope.launch {
            val produtoAtualizado = produto.copy(
                nome = _novoNome.value,
                quantidade = _novaQuantidade.value.toIntOrNull() ?: 0
            )
            _produtoDAO.atualizar(produtoAtualizado)
            _eventoSnackbar.emit("Produto atualizado com sucesso!")
            fechaAtualizarProdutoForm()
            listarProdutos()
        }
    }

    fun excluir(produto: Produto) {
        viewModelScope.launch {
            _produtoDAO.excluir(produto)
            _eventoSnackbar.emit("Produto removido com sucesso!")
            listarProdutos()
        }
    }

    fun criarProduto(): Produto {
        val produto = Produto(
            0,
            nome = _nome.value,
            quantidade = _quantidade.value.toIntOrNull() ?: 0
        )
        return produto;
    }
}