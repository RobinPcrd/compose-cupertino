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

package io.github.robinpcrd.cupertino.adaptive

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import io.github.robinpcrd.cupertino.CupertinoIconButton
import io.github.robinpcrd.cupertino.CupertinoIconButtonColors
import io.github.robinpcrd.cupertino.CupertinoIconButtonDefaults
import io.github.robinpcrd.cupertino.ExperimentalCupertinoApi

@ExperimentalAdaptiveApi
@ExperimentalCupertinoApi
@Composable
fun AdaptiveIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    adaptation: AdaptationScope<CupertinoIconButtonAdaptation, MaterialIconButtonAdaptation>.() -> Unit = {},
    content: @Composable (() -> Unit)
) {
    AdaptiveWidget(
        adaptation = remember {
            IconButtonAdaptation(type = IconButtonType.Borderless)
        },
        adaptationScope = adaptation,
        material = {
            IconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = content,
                colors = it.colors,
            )
        },
        cupertino = {
            CupertinoIconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = content,
                colors = it.colors
            )
        }
    )
}

@ExperimentalAdaptiveApi
@ExperimentalCupertinoApi
@Composable
fun AdaptiveFilledIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    adaptation: AdaptationScope<CupertinoIconButtonAdaptation, MaterialIconButtonAdaptation>.() -> Unit = {},
    content: @Composable (() -> Unit)
) {
    AdaptiveWidget(
        adaptation = remember {
            IconButtonAdaptation(type = IconButtonType.BezeledFilled)
        },
        adaptationScope = adaptation,
        material = {
            FilledIconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = content,
                colors = it.colors,
            )
        },
        cupertino = {
            CupertinoIconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = content,
                colors = it.colors
            )
        }
    )
}

@ExperimentalAdaptiveApi
@ExperimentalCupertinoApi
@Composable
fun AdaptiveOutlinedIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    adaptation: AdaptationScope<CupertinoIconButtonAdaptation, MaterialIconButtonAdaptation>.() -> Unit = {},
    content: @Composable (() -> Unit)
) {
    AdaptiveWidget(
        adaptation = remember {
            IconButtonAdaptation(type = IconButtonType.BezeledGray)
        },
        adaptationScope = adaptation,
        material = {
            OutlinedIconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = content,
                colors = it.colors,
            )
        },
        cupertino = {
            CupertinoIconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = content,
                colors = it.colors
            )
        }
    )
}

@ExperimentalAdaptiveApi
@ExperimentalCupertinoApi
@Composable
fun AdaptiveTonalIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    adaptation: AdaptationScope<CupertinoIconButtonAdaptation, MaterialIconButtonAdaptation>.() -> Unit = {},
    content: @Composable (() -> Unit)
) {
    AdaptiveWidget(
        adaptation = remember {
            IconButtonAdaptation(type = IconButtonType.Bezeled)
        },
        adaptationScope = adaptation,
        material = {
            FilledTonalIconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = content,
                colors = it.colors,
            )
        },
        cupertino = {
            CupertinoIconButton(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = content,
                colors = it.colors
            )
        }
    )
}

@Stable
class CupertinoIconButtonAdaptation internal constructor(
    colors: CupertinoIconButtonColors,
) {
    var colors: CupertinoIconButtonColors by mutableStateOf(colors)
}

@Stable
class MaterialIconButtonAdaptation internal constructor(
    colors: IconButtonColors
) {
    var colors: IconButtonColors by mutableStateOf(colors)

}

private enum class IconButtonType {
    Bezeled, BezeledGray, BezeledFilled, Borderless;
}

@OptIn(ExperimentalAdaptiveApi::class)
private class IconButtonAdaptation(
    private val type: IconButtonType,
) : Adaptation<CupertinoIconButtonAdaptation, MaterialIconButtonAdaptation>() {

    @Composable
    override fun rememberCupertinoAdaptation(): CupertinoIconButtonAdaptation {
        val colors = when (type) {
            IconButtonType.Bezeled -> CupertinoIconButtonDefaults.bezeledButtonColors()
            IconButtonType.BezeledGray -> CupertinoIconButtonDefaults.bezeledGrayButtonColors()
            IconButtonType.BezeledFilled -> CupertinoIconButtonDefaults.bezeledFilledButtonColors()
            IconButtonType.Borderless -> CupertinoIconButtonDefaults.borderlessButtonColors()
        }

        return remember(colors) {
            CupertinoIconButtonAdaptation(
                colors = colors,
            )
        }
    }

    @Composable
    override fun rememberMaterialAdaptation(): MaterialIconButtonAdaptation {
        val colors = when (type) {
            IconButtonType.Bezeled -> IconButtonDefaults.filledTonalIconButtonColors()
            IconButtonType.BezeledGray -> IconButtonDefaults.outlinedIconButtonColors()
            IconButtonType.BezeledFilled -> IconButtonDefaults.filledIconButtonColors()
            IconButtonType.Borderless -> IconButtonDefaults.iconButtonColors()
        }

        return remember(colors) {
            MaterialIconButtonAdaptation(
                colors = colors,
            )
        }
    }
}