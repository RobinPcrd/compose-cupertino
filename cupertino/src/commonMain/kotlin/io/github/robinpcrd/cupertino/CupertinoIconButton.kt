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

package io.github.robinpcrd.cupertino

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.robinpcrd.cupertino.theme.CupertinoTheme

sealed interface CupertinoIconButtonSize {
    val buttonSize: Dp
    val iconSize: Dp

    val isSpecified: Boolean
        get() = this != Unspecified || (this.buttonSize != Dp.Unspecified && this.iconSize != Dp.Unspecified)

    data object Small : CupertinoIconButtonSize {
        override val buttonSize: Dp = 28.dp
        override val iconSize: Dp = 18.dp
    }

    data object Medium : CupertinoIconButtonSize {
        override val buttonSize: Dp = 34.dp
        override val iconSize: Dp = 18.dp
    }

    data object Large : CupertinoIconButtonSize {
        override val buttonSize: Dp = 50.dp
        override val iconSize: Dp = 20.dp
    }

    data object Unspecified : CupertinoIconButtonSize {
        override val buttonSize: Dp = Dp.Unspecified
        override val iconSize: Dp = Dp.Unspecified
    }

    data class Custom(
        override val buttonSize: Dp,
        override val iconSize: Dp
    ) : CupertinoIconButtonSize
}

@ExperimentalCupertinoApi
@Composable
fun CupertinoIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier.Companion,
    enabled: Boolean = true,
    colors: CupertinoIconButtonColors = CupertinoIconButtonDefaults.plainButtonColors(),
    border: BorderStroke? = null,
    size: CupertinoIconButtonSize = CupertinoIconButtonSize.Unspecified,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    val isPlain = colors.containerColor == Color.Transparent
    val indication = if (isPlain) PlainButtonIndication else LocalIndication.current

    CupertinoSurface(
        onClick = onClick,
        modifier = modifier.then(
            if (size.isSpecified) {
                Modifier.size(size.buttonSize)
            } else {
                Modifier.size(CupertinoButtonTokens.IconButtonSize)
            }
        ),
        enabled = enabled,
        interactionSource = interactionSource,
        color = colors.containerColor(enabled),
        contentColor = colors.contentColor(enabled),
        border = border,
        shape = IconButtonDefaults.filledShape,
        indication = indication,
    ) {
        CompositionLocalProvider(LocalCupertinoIconSize provides size) {
            Box(
                contentAlignment = Alignment.Companion.Center,
                propagateMinConstraints = false,
            ) {
                content()
            }
        }
    }
}

@ExperimentalCupertinoApi
@Composable
fun CupertinoIconToggleButton(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier.Companion,
    enabled: Boolean = true,
    shape: Shape = IconButtonDefaults.filledShape,
    colors: CupertinoIconToggleButtonColors = CupertinoIconToggleButtonDefaults.plainButtonColors(),
    size: CupertinoIconButtonSize = CupertinoIconButtonSize.Unspecified,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
) {

    val isPlain = colors.containerColor == Color.Transparent
    val indication = if (isPlain) PlainButtonIndication else LocalIndication.current

    CupertinoSurface(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier.then(
            if (size.isSpecified) {
                Modifier.size(size.buttonSize)
            } else {
                Modifier.size(CupertinoButtonTokens.IconButtonSize)
            }
        ),
        enabled = enabled,
        interactionSource = interactionSource,
        color = colors.containerColor(enabled, checked).value,
        contentColor = colors.contentColor(enabled, checked).value,
        shape = shape,
        indication = indication,
    ) {
        CompositionLocalProvider(LocalCupertinoIconSize provides size) {
            Box(
                contentAlignment = Alignment.Companion.Center,
            ) {
                content()
            }
        }
    }
}

@Immutable
class CupertinoIconButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
) {

    /**
     * Returns a copy of this [CupertinoIconButtonColors], optionally overriding some of the values. This
     * uses the [Color.Unspecified] to mean “use the value from the source”
     */
    fun copy(
        containerColor: Color = this.containerColor,
        contentColor: Color = this.contentColor,
        disabledContainerColor: Color = this.disabledContainerColor,
        disabledContentColor: Color = this.disabledContentColor,
    ) = CupertinoIconButtonColors(
        containerColor.takeOrElse { this.containerColor },
        contentColor.takeOrElse { this.contentColor },
        disabledContainerColor.takeOrElse { this.disabledContainerColor },
        disabledContentColor.takeOrElse { this.disabledContentColor },
    )

    /**
     * Represents the container color for this icon button, depending on [enabled].
     *
     * @param enabled whether the icon button is enabled
     */
    @Stable
    internal fun containerColor(enabled: Boolean): Color =
        if (enabled) containerColor else disabledContainerColor

    /**
     * Represents the content color for this icon button, depending on [enabled].
     *
     * @param enabled whether the icon button is enabled
     */
    @Stable
    internal fun contentColor(enabled: Boolean): Color =
        if (enabled) contentColor else disabledContentColor

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is CupertinoIconButtonColors) return false

        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (disabledContainerColor != other.disabledContainerColor) return false
        if (disabledContentColor != other.disabledContentColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + disabledContainerColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        return result
    }
}

@Immutable
class CupertinoIconToggleButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val checkedContainerColor: Color,
    val checkedContentColor: Color,
) {

    /**
     * Returns a copy of this [CupertinoIconToggleButtonColors], optionally overriding some of the values. This
     * uses the [Color.Unspecified] to mean “use the value from the source”
     */
    fun copy(
        containerColor: Color = this.containerColor,
        contentColor: Color = this.contentColor,
        disabledContainerColor: Color = this.disabledContainerColor,
        disabledContentColor: Color = this.disabledContentColor,
        checkedContainerColor: Color = this.checkedContainerColor,
        checkedContentColor: Color = this.checkedContentColor
    ) = CupertinoIconToggleButtonColors(
        containerColor.takeOrElse { this.containerColor },
        contentColor.takeOrElse { this.contentColor },
        disabledContainerColor.takeOrElse { this.disabledContainerColor },
        disabledContentColor.takeOrElse { this.disabledContentColor },
        checkedContainerColor.takeOrElse { this.checkedContainerColor },
        checkedContentColor.takeOrElse { this.checkedContentColor }
    )

    /**
     * Represents the container color for this icon button, depending on [enabled] and [checked].
     *
     * @param enabled whether the icon button is enabled
     * @param checked whether the icon button is checked
     */
    @Composable
    internal fun containerColor(enabled: Boolean, checked: Boolean): State<Color> {
        val target =
            when {
                !enabled -> disabledContainerColor
                !checked -> containerColor
                else -> checkedContainerColor
            }
        return rememberUpdatedState(target)
    }

    /**
     * Represents the content color for this icon button, depending on [enabled] and [checked].
     *
     * @param enabled whether the icon button is enabled
     * @param checked whether the icon button is checked
     */
    @Composable
    internal fun contentColor(enabled: Boolean, checked: Boolean): State<Color> {
        val target =
            when {
                !enabled -> disabledContentColor
                !checked -> contentColor
                else -> checkedContentColor
            }
        return rememberUpdatedState(target)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || other !is CupertinoIconToggleButtonColors) return false

        if (containerColor != other.containerColor) return false
        if (contentColor != other.contentColor) return false
        if (disabledContainerColor != other.disabledContainerColor) return false
        if (disabledContentColor != other.disabledContentColor) return false
        if (checkedContainerColor != other.checkedContainerColor) return false
        if (checkedContentColor != other.checkedContentColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = containerColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + disabledContainerColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        result = 31 * result + checkedContainerColor.hashCode()
        result = 31 * result + checkedContentColor.hashCode()

        return result
    }
}

object CupertinoIconButtonDefaults {

    @Composable
    @ReadOnlyComposable
    fun filledButtonColors(
        containerColor: Color = CupertinoTheme.colorScheme.tertiarySystemBackground,
        contentColor: Color = CupertinoTheme.colorScheme.accent,
        disabledContainerColor: Color = CupertinoTheme.colorScheme.quaternarySystemFill,
        disabledContentColor: Color = CupertinoTheme.colorScheme.tertiaryLabel,
    ): CupertinoIconButtonColors = CupertinoIconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )

    @Composable
    @ReadOnlyComposable
    fun plainButtonColors(
        containerColor: Color = Color.Companion.Transparent,
        contentColor: Color = CupertinoTheme.colorScheme.accent,
        disabledContainerColor: Color = Color.Companion.Transparent,
        disabledContentColor: Color = CupertinoTheme.colorScheme.tertiaryLabel,
    ): CupertinoIconButtonColors = CupertinoIconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
    )
}

object CupertinoIconToggleButtonDefaults {

    @Composable
    @ReadOnlyComposable
    fun filledButtonColors(
        containerColor: Color = CupertinoTheme.colorScheme.tertiarySystemBackground,
        contentColor: Color = CupertinoTheme.colorScheme.accent,
        disabledContainerColor: Color = CupertinoTheme.colorScheme.quaternarySystemFill,
        disabledContentColor: Color = CupertinoTheme.colorScheme.tertiaryLabel,
        checkedContainerColor: Color = CupertinoTheme.colorScheme.accent,
        checkedContentColor: Color = Color.Companion.White,
    ): CupertinoIconToggleButtonColors = CupertinoIconToggleButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        checkedContainerColor = checkedContainerColor,
        checkedContentColor = checkedContentColor,
    )

    @Composable
    @ReadOnlyComposable
    fun plainButtonColors(
        containerColor: Color = Color.Companion.Transparent,
        contentColor: Color = LocalContentColor.current,
        disabledContainerColor: Color = Color.Companion.Transparent,
        disabledContentColor: Color = CupertinoTheme.colorScheme.tertiaryLabel,
        checkedContainerColor: Color = Color.Companion.Transparent,
        checkedContentColor: Color = CupertinoTheme.colorScheme.accent,
    ): CupertinoIconToggleButtonColors = CupertinoIconToggleButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        checkedContainerColor = checkedContainerColor,
        checkedContentColor = checkedContentColor
    )
}