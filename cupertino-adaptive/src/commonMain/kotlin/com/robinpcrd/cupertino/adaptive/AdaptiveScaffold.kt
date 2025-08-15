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



package com.robinpcrd.cupertino.adaptive

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import com.robinpcrd.cupertino.CupertinoScaffold
import com.robinpcrd.cupertino.CupertinoScaffoldDefaults
import com.robinpcrd.cupertino.ExperimentalCupertinoApi
import com.robinpcrd.cupertino.FabPosition


/**
 * A flag in the Cupertino adaptation scope indicating whether the scaffold content
 * contains native UIKit views.
 *
 * When set to true, translucent app bar effects like haze and blur are disabled
 * to prevent rendering issues with UIKit content on iOS.
 *
 * This flag allows proper visual integration when embedding UIKit Views inside Compose.
 * It applies only to the Cupertino adaptation of AdaptiveScaffold.
 *
 * Usage:
 * ```
 * AdaptiveScaffold(
 *     adaptation = {
 *         cupertino {
 *             hasUIKitContent = true
 *         }
 *     }
 * ) { ... }
 * ```
 */
@OptIn(ExperimentalCupertinoApi::class)
@ExperimentalAdaptiveApi
@Composable
fun AdaptiveScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    contentWindowInsets: WindowInsets = CupertinoScaffoldDefaults.contentWindowInsets,
    adaptation: AdaptationScope<CupertinoScaffoldAdaptation, ScaffoldAdaptation>.() -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    AdaptiveWidget(
        adaptation = remember {
            ScaffoldAdaptationImpl()
        },
        adaptationScope = adaptation,
        cupertino = { adaptation ->
            CupertinoScaffold(
                modifier = modifier,
                topBar = topBar,
                bottomBar = bottomBar,
                snackbarHost = snackbarHost,
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = floatingActionButtonPosition,
                containerColor = containerColor.takeOrElse { adaptation.containerColor },
                contentColor = contentColor.takeOrElse { adaptation.contentColor },
                contentWindowInsets = contentWindowInsets,
                content = content,
                hasUIKitContent = adaptation.hasUIKitContent,
            )
        },
        material = {
            Scaffold(
                modifier = modifier,
                topBar = topBar,
                bottomBar = bottomBar,
                snackbarHost = snackbarHost,
                floatingActionButton = floatingActionButton,
                floatingActionButtonPosition = when(floatingActionButtonPosition) {
                    FabPosition.End -> androidx.compose.material3.FabPosition.End
                    else -> androidx.compose.material3.FabPosition.Center
                },
                containerColor = it.containerColor,
                contentColor = it.contentColor,
                contentWindowInsets = contentWindowInsets,
                content = content
            )
        }
    )
}

@Stable
class ScaffoldAdaptation internal constructor(
    contentColor: Color,
    containerColor: Color
) {
    internal var contentColor by mutableStateOf(contentColor)
    internal var containerColor by mutableStateOf(containerColor)
}

@Stable
class CupertinoScaffoldAdaptation internal constructor(
    contentColor: Color,
    containerColor: Color,
    hasUIKitContent: Boolean = false,
) {
    internal var contentColor by mutableStateOf(contentColor)
    internal var containerColor by mutableStateOf(containerColor)

    /**
     * Set to true when the scaffold content contains native UIKit views.
     * If true, translucent app bar effects (haze/blur) are disabled to prevent compatibility
     * issues with platform views on iOS. Defaults to false.
     */
    var hasUIKitContent by mutableStateOf(hasUIKitContent)
}

@OptIn(ExperimentalAdaptiveApi::class)
@Stable
private class ScaffoldAdaptationImpl :
    Adaptation<CupertinoScaffoldAdaptation, ScaffoldAdaptation>() {

    @Composable
    override fun rememberCupertinoAdaptation(): CupertinoScaffoldAdaptation {
        val contentColor = CupertinoScaffoldDefaults.contentColor
        val containerColor = CupertinoScaffoldDefaults.containerColor

        return remember(contentColor, containerColor) {
            CupertinoScaffoldAdaptation(
                contentColor = contentColor,
                containerColor = containerColor,
            )
        }
    }

    @Composable
    override fun rememberMaterialAdaptation(): ScaffoldAdaptation {
        val containerColor = MaterialTheme.colorScheme.background
        val contentColor = contentColorFor(containerColor)

        return remember(contentColor, containerColor) {
            ScaffoldAdaptation(
                contentColor = contentColor,
                containerColor = containerColor
            )
        }
    }
}