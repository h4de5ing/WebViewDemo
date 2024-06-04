import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val html = """
    <html>
        <body>
            <h1>Hello World</h1>
        </body>
    </html>
""".trimIndent()
        val webViewState = rememberWebViewStateWithHTMLData(data = html)
//        val webViewState = rememberWebViewState("https://sj.qq.com")
        Column(Modifier.fillMaxSize()) {
            val text = webViewState.let { "${it.pageTitle ?: ""} ${it.loadingState} ${it.lastLoadedUrl ?: ""}" }
            Text(text)
            WebView(
                state = webViewState,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}