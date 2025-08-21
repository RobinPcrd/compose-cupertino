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
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.IconToggleButtonColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedIconToggleButton
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
            IconToggleButtonAdaptation(type = IconToggleButtonType.Borderless)
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
    AdaptiveIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        adaptation = adaptation,
        content = {
            IconContent(icon)
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
            IconToggleButtonAdaptation(type = IconToggleButtonType.BezeledFilled)
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
    AdaptiveFilledIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        adaptation = adaptation,
        content = {
            IconContent(icon)
        }
    )
}

@ExperimentalAdaptiveApi
@ExperimentalCupertinoApi
@Composable
fun AdaptiveTonalIconToggleButton(
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
            IconToggleButtonAdaptation(type = IconToggleButtonType.Bezeled)
        },
        adaptationScope = adaptation,
        material = {
            FilledTonalIconToggleButton(
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
fun AdaptiveTonalIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    adaptation: AdaptationScope<CupertinoIconToggleButtonAdaptation, MaterialIconToggleButtonAdaptation>.() -> Unit = {},
    icon: IconSource,
) {
    AdaptiveTonalIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        adaptation = adaptation,
        content = {
            IconContent(icon)
        }
    )
}

@ExperimentalAdaptiveApi
@ExperimentalCupertinoApi
@Composable
fun AdaptiveOutlinedIconToggleButton(
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
            IconToggleButtonAdaptation(type = IconToggleButtonType.BezeledGray)
        },
        adaptationScope = adaptation,
        material = {
            OutlinedIconToggleButton(
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
fun AdaptiveOutlinedIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    adaptation: AdaptationScope<CupertinoIconToggleButtonAdaptation, MaterialIconToggleButtonAdaptation>.() -> Unit = {},
    icon: IconSource,
) {
    AdaptiveOutlinedIconToggleButton(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        adaptation = adaptation,
        content = {
            IconContent(icon)
        }
    )
}

@Composable
private fun IconContent(icon: IconSource) {
    val painter = icon.rememberPainter()
    when (LocalTheme.current) {
        Theme.Cupertino -> CupertinoIcon(
            painter = painter,
            contentDescription = icon.contentDescription,
            tint = icon.tint.takeOrElse { LocalContentColor.current },
        )

        Theme.Material3 -> Icon(
            painter = painter,
            contentDescription = icon.contentDescription,
            tint = icon.tint.takeOrElse { LocalContentColor.current },
        )
    }
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

private enum class IconToggleButtonType {
    Bezeled, BezeledGray, BezeledFilled, Borderless;
}

@ExperimentalAdaptiveApi
private class IconToggleButtonAdaptation(
    private val type: IconToggleButtonType,
) : Adaptation<CupertinoIconToggleButtonAdaptation, MaterialIconToggleButtonAdaptation>() {

    @Composable
    override fun rememberCupertinoAdaptation(): CupertinoIconToggleButtonAdaptation {
        val colors = when (type) {
            IconToggleButtonType.Bezeled -> CupertinoIconToggleButtonDefaults.bezeledButtonColors()
            IconToggleButtonType.BezeledGray -> CupertinoIconToggleButtonDefaults.bezeledGrayButtonColors()
            IconToggleButtonType.BezeledFilled -> CupertinoIconToggleButtonDefaults.bezeledFilledButtonColors()
            IconToggleButtonType.Borderless -> CupertinoIconToggleButtonDefaults.borderlessButtonColors()
        }

        val shape = when (type) {
            IconToggleButtonType.Bezeled -> IconButtonDefaults.filledShape
            IconToggleButtonType.BezeledGray -> IconButtonDefaults.outlinedShape
            IconToggleButtonType.BezeledFilled -> IconButtonDefaults.filledShape
            IconToggleButtonType.Borderless -> IconButtonDefaults.outlinedShape
        }

        return remember(colors) {
            CupertinoIconToggleButtonAdaptation(
                colors = colors,
                shape = shape,
            )
        }
    }

    @Composable
    override fun rememberMaterialAdaptation(): MaterialIconToggleButtonAdaptation {
        val colors = when (type) {
            IconToggleButtonType.Bezeled -> IconButtonDefaults.filledTonalIconToggleButtonColors()
            IconToggleButtonType.BezeledGray -> IconButtonDefaults.outlinedIconToggleButtonColors()
            IconToggleButtonType.BezeledFilled -> IconButtonDefaults.filledIconToggleButtonColors()
            IconToggleButtonType.Borderless -> IconButtonDefaults.iconToggleButtonColors()
        }

        val shape = when (type) {
            IconToggleButtonType.Bezeled -> IconButtonDefaults.filledShape
            IconToggleButtonType.BezeledGray -> IconButtonDefaults.outlinedShape
            IconToggleButtonType.BezeledFilled -> IconButtonDefaults.filledShape
            IconToggleButtonType.Borderless -> IconButtonDefaults.outlinedShape
        }

        return remember(colors, shape) {
            MaterialIconToggleButtonAdaptation(
                colors = colors,
                shape = shape,
            )
        }
    }
}