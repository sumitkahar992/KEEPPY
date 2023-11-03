package com.example.keeppy.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.keeppy.R
import com.example.keeppy.biometric.AppBioMetricManager
import com.example.keeppy.biometric.MainViewModel
import com.example.keeppy.biometric.SettingsViewModel
import com.example.keeppy.presentation.common.navigation.NavigationComponent
import com.example.keeppy.presentation.common.theme.KEEPPYTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val settingsViewModel: SettingsViewModel by viewModels()


    @Inject
    lateinit var appBioMetricManager: AppBioMetricManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val biometricAuthState by settingsViewModel.biometricAuthState.collectAsState()

            KEEPPYTheme {
                NavigationComponent()

               /* SettingsSwitchCard(
                    text = "Biometric",
                    icon = painterResource(id = R.drawable.ic_launcher_background),
                    isChecked = biometricAuthState,
                    onCheckedChange = {
                        settingsViewModel.showBiometricPrompt(this@MainActivity)
                    }
                )*/
            }
        }
        setObservers()
    }










    private fun setObservers() {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.initAuth.collect { value ->
                    if (value && mainViewModel.loading.value) {
                        mainViewModel.showBiometricPrompt(this@MainActivity)
                    }
                }
            }
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.finishActivity.collect { value ->
                    if (value) {
                        finish()
                    }
                }
            }
        }
    }

}


@Composable
fun SettingsSwitchCard(
    text: String,
    icon: Painter,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.surface),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(21.dp)
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = text,
                fontSize = 16.sp,
                fontFamily = FontFamily.Monospace
            )
            Switch(
                modifier = Modifier
                    .semantics { contentDescription = "Theme Switch" }
                    .padding(start = 130.dp),
                checked = isChecked,
                onCheckedChange = onCheckedChange
            )
        }
    }

}
