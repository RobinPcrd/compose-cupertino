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
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
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
import com.robinpcrd.cupertino.CupertinoNavigateBackButton
import com.robinpcrd.cupertino.CupertinoText
import com.robinpcrd.cupertino.CupertinoTopAppBarDefaults
import com.robinpcrd.cupertino.ExperimentalCupertinoApi
import com.robinpcrd.cupertino.adaptive.AdaptiveScaffold
import com.robinpcrd.cupertino.adaptive.AdaptiveSwitch
import com.robinpcrd.cupertino.adaptive.AdaptiveTopAppBar
import com.robinpcrd.cupertino.adaptive.AdaptiveWidget
import com.robinpcrd.cupertino.adaptive.ExperimentalAdaptiveApi
import com.robinpcrd.cupertino.adaptive.icons.AccountCircle
import com.robinpcrd.cupertino.adaptive.icons.AdaptiveIcons
import com.robinpcrd.cupertino.adaptive.icons.Add
import com.robinpcrd.cupertino.adaptive.icons.Create
import com.robinpcrd.cupertino.adaptive.icons.Delete
import com.robinpcrd.cupertino.adaptive.icons.Person
import com.robinpcrd.cupertino.adaptive.icons.Search
import com.robinpcrd.cupertino.adaptive.icons.Settings
import com.robinpcrd.cupertino.adaptive.icons.Share
import com.robinpcrd.cupertino.adaptive.icons.ThumbUp

val useAdaptiveAppBar = true

@OptIn(
    ExperimentalAdaptiveApi::class,
    ExperimentalLayoutApi::class,
    ExperimentalCupertinoApi::class,
)
@Composable
fun AdaptiveNativeWidgetsScreen(component: AdaptiveNativeWidgetsComponent) {
    AdaptiveScaffold(
        topBar = {
            if (useAdaptiveAppBar) {
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
                        Text("Adaptive")
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
            } else {
                Row {
                    AdaptiveWidget(
                        cupertino = {
                            CupertinoNavigateBackButton(
                                onClick = component::onNavigateBack,
                                modifier = Modifier.windowInsetsPadding(CupertinoTopAppBarDefaults.windowInsets)
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
                }
            }
        },
        //bottomBar = {
        //    AdaptiveNavigationBar {
        //        var selected by rememberSaveable {
        //            mutableStateOf(0)
        //        }
        //        val content =
        //            listOf(
        //                "Profile" to AdaptiveIcons.Outlined.Person,
        //                "Menu" to AdaptiveIcons.Outlined.Menu,
        //                "Settings" to AdaptiveIcons.Outlined.Settings,
        //            )
        //        content.forEachIndexed { index, pair ->
        //            AdaptiveNavigationBarItem(
        //                selected = selected == index,
        //                onClick = {
        //                    selected = index
        //                },
        //                icon = {
        //                    Icon(
        //                        imageVector = pair.second,
        //                        contentDescription = pair.first,
        //                    )
        //                },
        //                label = {
        //                    Text(pair.first)
        //                },
        //            )
        //        }
        //    }
        //},
    ) { innerPadding ->
        MapView(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
        )
    }
}

@Composable
private fun adaptiveIcons() =
    listOf(
        AdaptiveIcons.Outlined.Add,
        AdaptiveIcons.Outlined.Create,
        AdaptiveIcons.Outlined.Share,
        AdaptiveIcons.Outlined.Settings,
        AdaptiveIcons.Outlined.Person,
        AdaptiveIcons.Outlined.AccountCircle,
        AdaptiveIcons.Outlined.Delete,
        AdaptiveIcons.Outlined.ThumbUp,
        AdaptiveIcons.Outlined.Search,
    )
