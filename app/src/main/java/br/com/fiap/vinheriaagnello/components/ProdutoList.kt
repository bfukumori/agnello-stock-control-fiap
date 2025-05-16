package br.com.fiap.vinheriaagnello.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.fiap.vinheriaagnello.model.Produto
import br.com.fiap.vinheriaagnello.view.ProdutoViewModel

@Composable
fun ProdutoList(produtos: List<Produto>, produtoViewModel: ProdutoViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        for (produto in produtos) {
            ProdutoCard(
                produto,
                produtoViewModel = produtoViewModel
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}