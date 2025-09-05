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

package adaptive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.robinpcrd.cupertino.CupertinoIcon
import io.github.robinpcrd.cupertino.CupertinoIconButtonDefaults
import io.github.robinpcrd.cupertino.CupertinoIconButtonSize
import io.github.robinpcrd.cupertino.CupertinoNavigateBackButton
import io.github.robinpcrd.cupertino.CupertinoText
import io.github.robinpcrd.cupertino.ExperimentalCupertinoApi
import io.github.robinpcrd.cupertino.IconSource
import io.github.robinpcrd.cupertino.adaptive.AdaptiveAlertDialog
import io.github.robinpcrd.cupertino.adaptive.AdaptiveButton
import io.github.robinpcrd.cupertino.adaptive.AdaptiveCheckbox
import io.github.robinpcrd.cupertino.adaptive.AdaptiveDatePicker
import io.github.robinpcrd.cupertino.adaptive.AdaptiveFilledIconButton
import io.github.robinpcrd.cupertino.adaptive.AdaptiveFilledIconToggleButton
import io.github.robinpcrd.cupertino.adaptive.AdaptiveIconButton
import io.github.robinpcrd.cupertino.adaptive.AdaptiveIconToggleButton
import io.github.robinpcrd.cupertino.adaptive.AdaptiveNavigationBar
import io.github.robinpcrd.cupertino.adaptive.AdaptiveNavigationBarItem
import io.github.robinpcrd.cupertino.adaptive.AdaptiveScaffold
import io.github.robinpcrd.cupertino.adaptive.AdaptiveSlider
import io.github.robinpcrd.cupertino.adaptive.AdaptiveSwitch
import io.github.robinpcrd.cupertino.adaptive.AdaptiveTextButton
import io.github.robinpcrd.cupertino.adaptive.AdaptiveTopAppBar
import io.github.robinpcrd.cupertino.adaptive.AdaptiveTriStateCheckbox
import io.github.robinpcrd.cupertino.adaptive.AdaptiveWidget
import io.github.robinpcrd.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.robinpcrd.cupertino.adaptive.icons.AccountCircle
import io.github.robinpcrd.cupertino.adaptive.icons.AdaptiveIcons
import io.github.robinpcrd.cupertino.adaptive.icons.Add
import io.github.robinpcrd.cupertino.adaptive.icons.Check
import io.github.robinpcrd.cupertino.adaptive.icons.CheckCircle
import io.github.robinpcrd.cupertino.adaptive.icons.Clear
import io.github.robinpcrd.cupertino.adaptive.icons.Create
import io.github.robinpcrd.cupertino.adaptive.icons.Delete
import io.github.robinpcrd.cupertino.adaptive.icons.Menu
import io.github.robinpcrd.cupertino.adaptive.icons.Person
import io.github.robinpcrd.cupertino.adaptive.icons.Search
import io.github.robinpcrd.cupertino.adaptive.icons.Settings
import io.github.robinpcrd.cupertino.adaptive.icons.Share
import io.github.robinpcrd.cupertino.adaptive.icons.ThumbUp
import io.github.robinpcrd.cupertino.cancel
import io.github.robinpcrd.cupertino.default
import io.github.robinpcrd.cupertino.icons.CupertinoIcons
import io.github.robinpcrd.cupertino.icons.outlined.Paintpalette
import io.github.robinpcrd.cupertino.rememberCupertinoDatePickerState
import io.github.robinpcrd.cupertino.theme.CupertinoColors
import io.github.robinpcrd.cupertino.theme.CupertinoTheme
import io.github.robinpcrd.cupertino.theme.systemBlue
import io.github.robinpcrd.cupertino.theme.systemGreen
import io.github.robinpcrd.cupertino.theme.systemOrange
import io.github.robinpcrd.cupertino.theme.systemPurple
import io.github.robinpcrd.cupertino.theme.systemRed
import kotlinx.coroutines.delay
import kotlin.random.Random

@OptIn(
    ExperimentalAdaptiveApi::class,
    ExperimentalCupertinoApi::class,
)
@Composable
fun AdaptiveWidgetsScreen(component: AdaptiveWidgetsComponent) {
    val isDark = CupertinoTheme.colorScheme.isDark
    val colorList = remember(isDark) {
        listOf(
            CupertinoColors.systemRed(isDark),
            CupertinoColors.systemGreen(isDark),
            CupertinoColors.systemBlue(isDark),
            CupertinoColors.systemOrange(isDark),
            CupertinoColors.systemPurple(isDark)
        )
    }

    var testIconButtonColor: Color by remember { mutableStateOf(Color.Unspecified) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(1_500L)
            Random.nextInt(0, 4).let {
                testIconButtonColor = colorList[it]
            }
        }
    }

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
        },
        bottomBar = {
            AdaptiveNavigationBar {
                var selected by rememberSaveable {
                    mutableStateOf(0)
                }

                val content =
                    listOf(
                        "Profile" to AdaptiveIcons.Outlined.Person,
                        "Menu" to AdaptiveIcons.Outlined.Menu,
                        "Settings" to AdaptiveIcons.Outlined.Settings,
                    )

                content.forEachIndexed { index, pair ->
                    AdaptiveNavigationBarItem(
                        selected = selected == index,
                        onClick = {
                            selected = index
                        },
                        icon = {
                            Icon(
                                imageVector = pair.second,
                                contentDescription = pair.first,
                            )
                        },
                        label = {
                            Text(pair.first)
                        },
                    )
                }
            }
        },
    ) { pv ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding =
                PaddingValues(
                    start = 12.dp,
                    end = 12.dp,
                    top = pv.calculateTopPadding() + 12.dp,
                    bottom = pv.calculateBottomPadding(),
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                ColorButtons(component::onAccentColorChanged, testColor = testIconButtonColor)
            }
            item {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    adaptiveIcons().forEach {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = it,
                            contentDescription = it.name,
                        )
                    }
                }
            }

            item {
                var checked by remember { mutableStateOf(false) }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    AdaptiveSwitch(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        },
                    )
                    AdaptiveSwitch(
                        checked = !checked,
                        onCheckedChange = {
                            checked = !it
                        },
                    )

                    //AdaptiveCircularProgressIndicator()
                }
            }

            item {
                var v by remember {
                    mutableStateOf(.5f)
                }

                AdaptiveSlider(v, { v = it })
            }

            item {
                var v by remember {
                    mutableStateOf(.5f)
                }

                AdaptiveSlider(v, { v = it }, steps = 5)
            }

            item {
                var alertVisible by remember { mutableStateOf(false) }

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    itemVerticalAlignment = Alignment.CenterVertically,
                ) {
                    AdaptiveButton(
                        onClick = {
                            alertVisible = true
                        },
                    ) {
                        Text("Alert")
                    }
                    AdaptiveTextButton(onClick = {}) {
                        Text("Text Button")
                    }

                    AdaptiveIconButton(onClick = {}) {
                        Icon(
                            imageVector = AdaptiveIcons.Outlined.Delete,
                            contentDescription = null,
                        )
                    }
                    AdaptiveFilledIconButton(onClick = {}) {
                        Icon(
                            imageVector = AdaptiveIcons.Outlined.Delete,
                            contentDescription = null,
                        )
                    }
                }

                if (alertVisible) {
                    AdaptiveAlertDialog(
                        onDismissRequest = {
                            alertVisible = false
                        },
                        title = {
                            Text("Alert")
                        },
                        message = {
                            Text("Adaptive Alert Dialog")
                        },
                    ) {
                        cancel(onClick = {
                            alertVisible = false
                        }) {
                            Text("Cancel")
                        }
                        default(onClick = {
                            alertVisible = false
                        }) {
                            Text("OK")
                        }
                    }
                }
            }

            item {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    itemVerticalAlignment = Alignment.CenterVertically,
                ) {
                    var isDefaultButtonChecked by remember { mutableStateOf(false) }
                    var isFilledButtonChecked by remember { mutableStateOf(false) }

                    AdaptiveIconToggleButton(
                        checked = isDefaultButtonChecked,
                        onCheckedChange = {
                            isDefaultButtonChecked = it
                        },
                        icon = IconSource.vector {
                            if (isDefaultButtonChecked) AdaptiveIcons.Outlined.CheckCircle else AdaptiveIcons.Outlined.Clear
                        }
                    )
                    AdaptiveIconToggleButton(
                        checked = isDefaultButtonChecked,
                        onCheckedChange = {
                            isDefaultButtonChecked = it
                        },
                        enabled = false,
                        icon = IconSource.vector { if (isDefaultButtonChecked) AdaptiveIcons.Outlined.CheckCircle else AdaptiveIcons.Outlined.Clear }
                    )
                    AdaptiveFilledIconToggleButton(
                        checked = isFilledButtonChecked,
                        onCheckedChange = {
                            isFilledButtonChecked = it
                        },
                        adaptation = {
                            cupertino {
                                size = CupertinoIconButtonSize.Large
                            }
                        },
                        icon = IconSource.vector { if (isFilledButtonChecked) AdaptiveIcons.Outlined.Check else AdaptiveIcons.Outlined.Clear }
                    )
                    AdaptiveFilledIconToggleButton(
                        checked = isFilledButtonChecked,
                        onCheckedChange = {
                            isFilledButtonChecked = it
                        },
                        enabled = false,
                        adaptation = {
                            cupertino {
                                size = CupertinoIconButtonSize.Large
                            }
                        },
                        icon = IconSource.vector { if (isFilledButtonChecked) AdaptiveIcons.Outlined.Check else AdaptiveIcons.Outlined.Clear }
                    )
                }
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    var a by remember { mutableStateOf(true) }
                    var b by remember { mutableStateOf(false) }
                    var c by remember { mutableStateOf(ToggleableState.Indeterminate) }

                    AdaptiveCheckbox(checked = a, onCheckedChange = { a = it })
                    AdaptiveCheckbox(checked = b, onCheckedChange = { b = it })
                    AdaptiveTriStateCheckbox(state = c, onClick = {
                        c =
                            when (c) {
                                ToggleableState.On -> ToggleableState.Off
                                ToggleableState.Off -> ToggleableState.Indeterminate
                                ToggleableState.Indeterminate -> ToggleableState.On
                            }
                    })
                }
            }

            item {
                AdaptiveDatePicker(
                    state = rememberCupertinoDatePickerState(),
                    modifier = Modifier.fillMaxWidth(),
                    adaptation = {
                        material {
                            headline = null
                            showModeToggle = false
                            title = null
                        }
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalAdaptiveApi::class, ExperimentalCupertinoApi::class)
@Composable
private fun ColorButtons(
    onColorsChanged: (light: Color, dark: Color) -> Unit,
    testColor: Color,
) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AdaptiveIconButton(
            onClick = {
                onColorsChanged(
                    CupertinoColors.systemBlue(false),
                    CupertinoColors.systemBlue(true)
                )
            },
            adaptation = {
                cupertino {
                    colors = CupertinoIconButtonDefaults.bezeledButtonColors(
                        contentColor = CupertinoColors.systemBlue,
                    )
                }
                material {
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = CupertinoColors.systemBlue,
                    )
                }
            },
        ) {
            CupertinoIcon(
                imageVector = CupertinoIcons.Default.Paintpalette,
                contentDescription = null,
            )
        }
        AdaptiveIconButton(
            onClick = {
                onColorsChanged(
                    CupertinoColors.systemGreen(false),
                    CupertinoColors.systemGreen(true)
                )
            },
            adaptation = {
                cupertino {
                    colors = CupertinoIconButtonDefaults.bezeledButtonColors(
                        contentColor = CupertinoColors.systemGreen,
                    )
                }
                material {
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = CupertinoColors.systemGreen,
                    )
                }
            }
        ) {
            CupertinoIcon(
                imageVector = CupertinoIcons.Default.Paintpalette,
                contentDescription = null,
            )
        }
        AdaptiveIconButton(
            onClick = {
                onColorsChanged(
                    CupertinoColors.systemPurple(false),
                    CupertinoColors.systemPurple(true)
                )
            },
            adaptation = {
                cupertino {
                    colors = CupertinoIconButtonDefaults.bezeledButtonColors(
                        contentColor = CupertinoColors.systemPurple,
                    )
                }
                material {
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = CupertinoColors.systemPurple,
                    )
                }
            }
        ) {
            CupertinoIcon(
                imageVector = CupertinoIcons.Default.Paintpalette,
                contentDescription = null,
            )
        }

        AdaptiveIconButton(
            onClick = {
                onColorsChanged(
                    CupertinoColors.systemOrange(false),
                    CupertinoColors.systemOrange(true)
                )
            },
            adaptation = {
                cupertino {
                    colors = CupertinoIconButtonDefaults.bezeledButtonColors(
                        contentColor = CupertinoColors.systemOrange,
                    )
                }
                material {
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = CupertinoColors.systemOrange,
                    )
                }
            }
        ) {
            CupertinoIcon(
                imageVector = CupertinoIcons.Default.Paintpalette,
                contentDescription = null,
            )
        }
        AdaptiveIconButton(
            onClick = {
                onColorsChanged(
                    CupertinoColors.systemRed(false),
                    CupertinoColors.systemRed(true)
                )
            },
            adaptation = {
                cupertino {
                    colors = CupertinoIconButtonDefaults.bezeledButtonColors(
                        contentColor = CupertinoColors.systemRed,
                    )
                }
                material {
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = CupertinoColors.systemRed,
                    )
                }
            }
        ) {
            CupertinoIcon(
                imageVector = CupertinoIcons.Default.Paintpalette,
                contentDescription = null
            )
        }
        AdaptiveIconButton(
            onClick = {},
            adaptation = {
                cupertino {
                    colors = CupertinoIconButtonDefaults.borderlessButtonColors(
                        contentColor = testColor,
                    )
                }
                material {
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = testColor,
                    )
                }
            }
        ) {
            CupertinoIcon(
                imageVector = CupertinoIcons.Default.Paintpalette,
                contentDescription = null,
            )
        }
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
