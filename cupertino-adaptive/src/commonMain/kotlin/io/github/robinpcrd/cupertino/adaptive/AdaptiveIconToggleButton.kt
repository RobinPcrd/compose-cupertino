/*
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
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import io.github.robinpcrd.cupertino.CupertinoIcon
import io.github.robinpcrd.cupertino.CupertinoIconButtonSize
import io.github.robinpcrd.cupertino.CupertinoIconToggleButton
import io.github.robinpcrd.cupertino.CupertinoIconToggleButtonColors
import io.github.robinpcrd.cupertino.CupertinoIconToggleButtonDefaults
import io.github.robinpcrd.cupertino.ExperimentalCupertinoApi
import io.github.robinpcrd.cupertino.IconSource

@ExperimentalAdaptiveApi
@ExperimentalCupertinoApi
@Composable
fun AdaptiveIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    adaptation: AdaptationScope<CupertinoIconToggleButtonAdaptation, MaterialIconToggleButtonAdaptation>.() -> Unit = {},
    content: @Composable (() -> Unit),
) {
    AdaptiveWidget(
        adaptation = remember {
            IconToggleButtonAdaptation(isFilled = false)
        },
        adaptationScope = adaptation,
        material = {
            IconToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = content,
                colors = it.colors,
            )
        },
        cupertino = {
            CupertinoIconToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                colors = it.colors,
                shape = it.shape,
                size = it.size,
                content = content,
            )
        }
    )
}

@ExperimentalAdaptiveApi
@ExperimentalCupertinoApi
@Composable
fun AdaptiveIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    adaptation: AdaptationScope<CupertinoIconToggleButtonAdaptation, MaterialIconToggleButtonAdaptation>.() -> Unit = {},
    icon: IconSource,
) {
    val painter = icon.rememberPainter()

    AdaptiveWidget(
        adaptation = remember {
            IconToggleButtonAdaptation(isFilled = false)
        },
        adaptationScope = adaptation,
        material = {
            IconToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = {
                    Icon(
                        painter = painter,
                        contentDescription = icon.contentDescription,
                        tint = icon.tint.takeOrElse { LocalContentColor.current },
                    )
                },
                colors = it.colors,
            )
        },
        cupertino = {
            CupertinoIconToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                colors = it.colors,
                shape = it.shape,
                size = it.size,
                content = {
                    CupertinoIcon(
                        painter = painter,
                        contentDescription = icon.contentDescription,
                        tint = icon.tint.takeOrElse { LocalContentColor.current },
                    )
                },
            )
        }
    )
}

@ExperimentalAdaptiveApi
@ExperimentalCupertinoApi
@Composable
fun AdaptiveFilledIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    adaptation: AdaptationScope<CupertinoIconToggleButtonAdaptation, MaterialIconToggleButtonAdaptation>.() -> Unit = {},
    content: @Composable (() -> Unit),
) {
    AdaptiveWidget(
        adaptation = remember {
            IconToggleButtonAdaptation(isFilled = true)
        },
        adaptationScope = adaptation,
        material = {
            FilledIconToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                content = content,
                colors = it.colors,
                shape = it.shape,
            )
        },
        cupertino = {
            CupertinoIconToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                colors = it.colors,
                shape = it.shape,
                size = it.size,
                content = content,
            )
        }
    )
}

@ExperimentalAdaptiveApi
@ExperimentalCupertinoApi
@Composable
fun AdaptiveFilledIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    adaptation: AdaptationScope<CupertinoIconToggleButtonAdaptation, MaterialIconToggleButtonAdaptation>.() -> Unit = {},
    icon: IconSource,
) {
    val painter = icon.rememberPainter()

    AdaptiveWidget(
        adaptation = remember {
            IconToggleButtonAdaptation(isFilled = true)
        },
        adaptationScope = adaptation,
        material = {
            FilledIconToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                colors = it.colors,
                shape = it.shape,
                content = {
                    Icon(
                        painter = painter,
                        contentDescription = icon.contentDescription,
                        tint = icon.tint.takeOrElse { LocalContentColor.current },
                    )
                },
            )
        },
        cupertino = {
            CupertinoIconToggleButton(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                interactionSource = interactionSource,
                colors = it.colors,
                shape = it.shape,
                size = it.size,
                content = {
                    CupertinoIcon(
                        painter = painter,
                        contentDescription = icon.contentDescription,
                        tint = icon.tint.takeOrElse { LocalContentColor.current },
                    )
                },
            )
        }
    )
}

@Stable
class CupertinoIconToggleButtonAdaptation internal constructor(
    colors: CupertinoIconToggleButtonColors,
    shape: Shape,
    size: CupertinoIconButtonSize = CupertinoIconButtonSize.Unspecified,
) {
    var colors: CupertinoIconToggleButtonColors by mutableStateOf(colors)
    var shape: Shape by mutableStateOf(shape)
    var size: CupertinoIconButtonSize by mutableStateOf(size)
}

@Stable
class MaterialIconToggleButtonAdaptation internal constructor(
    colors: IconToggleButtonColors,
    shape: Shape,
) {
    var colors: IconToggleButtonColors by mutableStateOf(colors)
    var shape: Shape by mutableStateOf(shape)
}

@ExperimentalAdaptiveApi
private class IconToggleButtonAdaptation(
    private val isFilled: Boolean
) : Adaptation<CupertinoIconToggleButtonAdaptation, MaterialIconToggleButtonAdaptation>() {

    @Composable
    override fun rememberCupertinoAdaptation(): CupertinoIconToggleButtonAdaptation {
        val colors = if (isFilled)
            CupertinoIconToggleButtonDefaults.filledButtonColors()
        else
            CupertinoIconToggleButtonDefaults.plainButtonColors()

        val shape = if (isFilled)
            IconButtonDefaults.filledShape
        else
            IconButtonDefaults.outlinedShape

        return remember(colors) {
            CupertinoIconToggleButtonAdaptation(
                colors = colors,
                shape = shape,
            )
        }
    }

    @Composable
    override fun rememberMaterialAdaptation(): MaterialIconToggleButtonAdaptation {
        val colors = if (isFilled)
            IconButtonDefaults.filledIconToggleButtonColors()
        else
            IconButtonDefaults.iconToggleButtonColors()

        val shape = if (isFilled)
            IconButtonDefaults.filledShape
        else
            IconButtonDefaults.outlinedShape

        return remember(colors, shape) {
            MaterialIconToggleButtonAdaptation(
                colors = colors,
                shape = shape,
            )
        }
    }
}