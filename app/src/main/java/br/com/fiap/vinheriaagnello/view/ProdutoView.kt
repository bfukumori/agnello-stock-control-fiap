package br.com.fiap.vinheriaagnello.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.vinheriaagnello.components.ProdutoForm
import br.com.fiap.vinheriaagnello.components.ProdutoList

@Composable
fun ProdutoView(modifier: Modifier, produtoViewModel: ProdutoViewModel) {

    val snackbarHostState = remember { SnackbarHostState() }

    val nomeState = produtoViewModel.nome.collectAsState()
    val quantidadeState = produtoViewModel.quantidade.collectAsState()
    val listaProdutos = produtoViewModel.produtos.collectAsState()

    LaunchedEffect(Unit) {
        produtoViewModel.listarProdutos()
        produtoViewModel.eventoSnackbar.collect { mensagem ->
            snackbarHostState.showSnackbar(mensagem)
        }
    }

    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }, content = {
        Column(modifier) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = "Vinheria Agnello", fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,

                )
            ProdutoForm(
                nome = nomeState.value,
                quantidade = quantidadeState.value,
                onNomeChange = {
                    produtoViewModel.onNomeChange(it)
                },
                onQuantidadeChange = {
                    produtoViewModel.onQuantidadeChange(it)
                },
                onCadastrar = {
                    val produto = produtoViewModel.criarProduto()
                    produtoViewModel.salvar(produto)
                }
            )
            ProdutoList(listaProdutos.value, produtoViewModel)
        }
    })


}

