/*
 * Copyright (c) 2023-2024. Compose Cupertino project and open source contributors.
 * Copyright (c) 2025. Scott Lanoue.
 * Copyright (c) 2025. Robin Picard.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package adaptivevative

import MapView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.robinpcrd.cupertino.CupertinoNavigateBackButton
import io.github.robinpcrd.cupertino.CupertinoText
import io.github.robinpcrd.cupertino.ExperimentalCupertinoApi
import io.github.robinpcrd.cupertino.adaptive.AdaptiveScaffold
import io.github.robinpcrd.cupertino.adaptive.AdaptiveSwitch
import io.github.robinpcrd.cupertino.adaptive.AdaptiveTopAppBar
import io.github.robinpcrd.cupertino.adaptive.AdaptiveWidget
import io.github.robinpcrd.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.robinpcrd.cupertino.theme.CupertinoColors
import io.github.robinpcrd.cupertino.theme.systemGreen

@OptIn(
    ExperimentalAdaptiveApi::class,
    ExperimentalLayoutApi::class,
    ExperimentalCupertinoApi::class,
)
@Composable
fun AdaptiveNativeWidgetsScreen(component: AdaptiveNativeWidgetsComponent) {
    AdaptiveScaffold(
        topBar = {
                AdaptiveTopAppBar(
                    navigationIcon = {
                        AdaptiveWidget(
                            cupertino = {
                                CupertinoNavigateBackButton(
                                    onClick = component::onNavigateBack,
                                ) {
                                    CupertinoText("Back")
                                }
                            },
                            material = {
                                IconButton(
                                    onClick = component::onNavigateBack,
                                ) {
                                    Icon(
                                        imageVector =
                                            if (LocalLayoutDirection.current == LayoutDirection.Ltr) {
                                                Icons.AutoMirrored.Default.ArrowBack
                                            } else {
                                                Icons.AutoMirrored.Default.ArrowForward
                                            },
                                        contentDescription = "Back",
                                    )
                                }
                            },
                        )
                    },
                    title = {
                        Text("Adaptive Native")
                    },
                    actions = {
                        Text("Theme")
                        AdaptiveSwitch(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            checked = component.isMaterial.value,
                            onCheckedChange = {
                                component.onThemeChanged()
                            },
                        )
                    },
                )
        },
        adaptation = {
            cupertino {
                hasUIKitContent = true
            }
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxWidth(0.5f).fillMaxHeight().background(CupertinoColors.systemGreen))
        MapView(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
        )
    }
}