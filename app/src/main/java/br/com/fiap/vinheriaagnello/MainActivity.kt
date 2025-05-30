package br.com.fiap.vinheriaagnello

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.fiap.vinheriaagnello.ui.theme.VinheriaAgnelloTheme
import br.com.fiap.vinheriaagnello.view.ProdutoView
import br.com.fiap.vinheriaagnello.view.ProdutoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VinheriaAgnelloTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProdutoView(
                        modifier = Modifier.padding(innerPadding),
                        produtoViewModel = ProdutoViewModel()
                    )
                }
            }
        }
    }
}