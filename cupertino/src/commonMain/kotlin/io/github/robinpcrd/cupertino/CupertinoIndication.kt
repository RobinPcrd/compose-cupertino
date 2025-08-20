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

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.FocusInteraction
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.node.DrawModifierNode
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Cupertino click effect
 * */
@Composable
@ExperimentalCupertinoApi
fun rememberCupertinoIndication(color: Color = CupertinoIndication.DefaultColor): Indication =
    remember(color) {
        CupertinoIndication(color)
    }

internal class CupertinoIndication(
    private val color: Color,
) : IndicationNodeFactory {
    companion object {
        val DefaultColor: Color
            @Composable
            @ReadOnlyComposable
            get() = LocalContentColor.current.copy(alpha = DefaultAlpha)

        const val DefaultAlpha = 0.1f
    }

    override fun create(interactionSource: InteractionSource): Modifier.Node =
        CupertinoIndicationNode(color, interactionSource)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CupertinoIndication) return false
        return color == other.color
    }

    override fun hashCode(): Int = color.hashCode()
}

internal class CupertinoIndicationNode(
    val color: Color,
    val interactionSource: InteractionSource,
) : Modifier.Node(), DrawModifierNode {
    private var animationJob: Job? = null
    private val animatedAlpha = Animatable(0f)
    private val pressInteractions = mutableSetOf<PressInteraction.Press>()
    private val focusInteractions = mutableSetOf<FocusInteraction.Focus>()

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        pressInteractions.add(interaction)
                        animateAlphaTo(1f)
                    }

                    is PressInteraction.Release -> {
                        // Correctly remove the corresponding PressInteraction.Press
                        pressInteractions.remove(interaction.press)
                        if (pressInteractions.isEmpty()) {
                            animateAlphaTo(if (focusInteractions.isNotEmpty()) 0.5f else 0f)
                        }
                    }

                    is PressInteraction.Cancel -> {
                        // Correctly remove the corresponding PressInteraction.Press
                        pressInteractions.remove(interaction.press)
                        if (pressInteractions.isEmpty()) {
                            animateAlphaTo(if (focusInteractions.isNotEmpty()) 0.5f else 0f)
                        }
                    }

                    is FocusInteraction.Focus -> {
                        focusInteractions.add(interaction)
                        if (pressInteractions.isEmpty()) {
                            animateAlphaTo(0.5f)
                        }
                    }

                    is FocusInteraction.Unfocus -> {
                        focusInteractions.remove(interaction.focus)
                        if (pressInteractions.isEmpty() && focusInteractions.isEmpty()) {
                            animateAlphaTo(0f)
                        }
                    }
                }
            }
        }
    }

    override fun onDetach() {
        animationJob?.cancel()
    }

    private fun animateAlphaTo(targetAlpha: Float) {
        animationJob?.cancel()
        animationJob = coroutineScope.launch {
            animatedAlpha.animateTo(targetAlpha, tween(durationMillis = 150))
        }
    }

    override fun ContentDrawScope.draw() {
        if (animatedAlpha.value > 0f) {
            drawRect(color = color, alpha = animatedAlpha.value)
        }
        drawContent()
    }
}

data object PlainButtonIndication : IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): Modifier.Node =
        PlainButtonIndicationNode(interactionSource)
}

private class PlainButtonIndicationNode(
    private val interactionSource: InteractionSource
) : Modifier.Node(), DrawModifierNode {
    private var animationJob: Job? = null
    private var isPressed by mutableStateOf(false)
    private val animatedAlpha = Animatable(1f)
    private val paint = Paint()

    override fun onAttach() {
        startInteractionCollection()
    }

    override fun onDetach() {
        animationJob?.cancel()
    }

    private fun startInteractionCollection() {
        coroutineScope.launch {
            interactionSource.interactions.collect { interaction ->
                when (interaction) {
                    is PressInteraction.Press -> {
                        isPressed = true
                        animateAlphaTo(CupertinoButtonTokens.PressedPlainButonAlpha)
                    }

                    is PressInteraction.Release,
                    is PressInteraction.Cancel -> {
                        isPressed = false
                        animateAlphaTo(1f)
                    }
                }
            }
        }
    }

    private fun animateAlphaTo(targetAlpha: Float) {
        animationJob?.cancel()
        animationJob = coroutineScope.launch {
            animatedAlpha.animateTo(targetAlpha, tween(durationMillis = 150))
        }
    }

    override fun ContentDrawScope.draw() {
        // Apply alpha to the entire content
        drawWithLayer {
            this@draw.drawContent()
        }
    }

    private fun DrawScope.drawWithLayer(block: DrawScope.() -> Unit) {
        val alpha = animatedAlpha.value
        if (alpha < 1f) {
            drawContext.canvas.saveLayer(
                bounds = size.toRect(),
                paint = paint.apply { this.alpha = alpha }
            )
            block()
            drawContext.canvas.restore()
        } else {
            block()
        }
    }
}