package br.com.fiap.vinheriaagnello.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.vinheriaagnello.model.Produto
import br.com.fiap.vinheriaagnello.view.ProdutoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProdutoCard(
    produto: Produto,
    produtoViewModel: ProdutoViewModel,
) {
    val mostrarAtualizarProdutoForm = produtoViewModel.mostrarAtualizarProdutoForm.collectAsState()
    val novoNome = produtoViewModel.novoNome.collectAsState()
    val novaQuantidade = produtoViewModel.novaQuantidade.collectAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(true, null, null, { produtoViewModel.abreAtualizarProdutoForm(produto) }),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(2f)
            ) {
                Text(
                    text = produto.nome,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Quantidade: ${produto.quantidade}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(onClick = { produtoViewModel.excluir(produto) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "",
                    tint = Color.Red
                )
            }
        }
    }
    if (mostrarAtualizarProdutoForm.value) {
        BasicAlertDialog(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(color = Color.White)
                .padding(32.dp),
            onDismissRequest = { produtoViewModel.fechaAtualizarProdutoForm() },
            content = {
                EditarProdutoForm(
                    nome = novoNome.value,
                    quantidade = novaQuantidade.value,
                    onNomeChange = {
                        produtoViewModel.onNovoNomeChange(it)
                    },
                    onQuantidadeChange = {
                        produtoViewModel.onNovaQuantidadeChange(it)
                    },
                    onAtualizar = {
                        produtoViewModel.atualizar(produto)
                    }
                )
            })

    }
}