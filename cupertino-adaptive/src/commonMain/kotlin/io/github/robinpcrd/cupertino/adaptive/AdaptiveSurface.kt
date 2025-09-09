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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Indication
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.robinpcrd.cupertino.CupertinoSurface
import io.github.robinpcrd.cupertino.LocalContentColor
import io.github.robinpcrd.cupertino.theme.CupertinoTheme

@ExperimentalAdaptiveApi
@Composable
fun AdaptiveSurface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    shadowElevation: Dp = 0.dp,
    content: @Composable () -> Unit,
) {
    AdaptiveWidget(
        material = {
            Surface(
                modifier = modifier,
                shape = shape,
                color = color.takeOrElse {
                    MaterialTheme.colorScheme.surface
                },
                contentColor = contentColor.takeOrElse {
                    MaterialTheme.colorScheme.onSurface
                },
                shadowElevation = shadowElevation,
                content = content,
            )
        },
        cupertino = {
            CupertinoSurface(
                modifier = modifier,
                shape = shape,
                color = color.takeOrElse {
                    CupertinoTheme.colorScheme.systemBackground
                },
                contentColor = contentColor.takeOrElse {
                    LocalContentColor.current
                },
                shadowElevation = shadowElevation,
                content = content,
            )
        },
    )
}

@ExperimentalAdaptiveApi
@Composable
fun AdaptiveSurface(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    indication: Indication? = LocalIndication.current,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    AdaptiveWidget(
        material = {
            Surface(
                onClick = onClick,
                enabled = enabled,
                modifier = modifier,
                shape = shape,
                color = color.takeOrElse {
                    MaterialTheme.colorScheme.surface
                },
                contentColor = contentColor.takeOrElse {
                    MaterialTheme.colorScheme.onSurface
                },
                shadowElevation = shadowElevation,
                border = border,
                interactionSource = interactionSource,
                content = content
            )
        },
        cupertino = {
            CupertinoSurface(
                onClick = onClick,
                modifier = modifier,
                enabled = enabled,
                shape = shape,
                color = color.takeOrElse {
                    CupertinoTheme.colorScheme.systemBackground
                },
                contentColor = contentColor.takeOrElse {
                    LocalContentColor.current
                },
                shadowElevation = shadowElevation,
                border = border,
                indication = indication,
                interactionSource = interactionSource,
                content = content,
            )
        }
    )
}

@ExperimentalAdaptiveApi
@Composable
fun AdaptiveSurface(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    color: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    indication: Indication? = LocalIndication.current,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
) {
    AdaptiveWidget(
        material = {
            Surface(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                shape = shape,
                color = color.takeOrElse {
                    MaterialTheme.colorScheme.surface
                },
                contentColor = contentColor.takeOrElse {
                    MaterialTheme.colorScheme.onSurface
                },
                shadowElevation = shadowElevation,
                border = border,
                interactionSource = interactionSource,
                content = content,
            )
        },
        cupertino = {
            CupertinoSurface(
                checked = checked,
                onCheckedChange = onCheckedChange,
                modifier = modifier,
                enabled = enabled,
                shape = shape,
                color = color.takeOrElse {
                    CupertinoTheme.colorScheme.systemBackground
                },
                contentColor = contentColor.takeOrElse {
                    LocalContentColor.current
                },
                shadowElevation = shadowElevation,
                border = border,
                indication = indication,
                interactionSource = interactionSource,
                content = content,
            )
        },
    )
}