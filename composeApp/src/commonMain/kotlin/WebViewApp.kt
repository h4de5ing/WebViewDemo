import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.multiplatform.webview.web.LoadingState
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState

@Composable
internal fun WebViewApp() {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = "main",
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
    ) {
        composable("main") {
            MainScreen(controller)
        }
        composable("basic") {
            BasicWebViewSample(controller)
        }
        composable("intercept") {
            InterceptRequestSample(controller)
        }
    }
}

@Composable
fun MainScreen(controller: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            controller.navigate("basic")
        }) {
            Text("Basic Sample", fontSize = 18.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            controller.navigate("intercept")
        }) {
            Text("Intercept Request Sample", fontSize = 18.sp)
        }
    }
}

@Composable
internal fun WebViewSample() {
    MaterialTheme {
        val webViewState =
            rememberWebViewState("https://github.com/KevinnZou/compose-webview-multiplatform")
        webViewState.webSettings.apply {
            isJavaScriptEnabled = true
            customUserAgentString =
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_1) AppleWebKit/625.20 (KHTML, like Gecko) Version/14.3.43 Safari/625.20"
            androidWebSettings.apply {
                isAlgorithmicDarkeningAllowed = true
                safeBrowsingEnabled = true
            }
        }
        Column(Modifier.fillMaxSize()) {
            val loadingState = webViewState.loadingState
            if (loadingState is LoadingState.Loading) {
                LinearProgressIndicator(
                    progress = loadingState.progress,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            WebView(
                state = webViewState,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
