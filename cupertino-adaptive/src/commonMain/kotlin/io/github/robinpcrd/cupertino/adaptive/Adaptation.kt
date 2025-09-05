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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Scope for customizing [material] and [cupertino] specs of the widget.
 *
 * Custom adaptive widgets can be created using [AdaptiveWidget] composable with implemented [Adaptation]
 *
 * @see AdaptiveWidget
 * @see Adaptation
 * */
@Stable
@ExperimentalAdaptiveApi
sealed interface AdaptationScope<C, M> {
    /**
     * Customize properties that are exclusive for Cupertino widget or have different default value
     *
     * @param block customization block
     * */
    fun cupertino(block: @Composable C.() -> Unit)

    /**
     * Customize properties that are exclusive for Material widget or have different default value
     *
     * @param block customization block
     * */
    fun material(block: @Composable M.() -> Unit)
}

@Stable
@ExperimentalAdaptiveApi
abstract class Adaptation<C, M> : AdaptationScope<C, M> {
    private var cupertino: @Composable C.() -> Unit = {}
    private var material: @Composable M.() -> Unit = {}
    private var configVersion by mutableStateOf(0)

    override fun cupertino(block: @Composable C.() -> Unit) {
        if (cupertino != block) {
            cupertino = block
            configVersion++
        }
    }

    override fun material(block: @Composable M.() -> Unit) {
        if (material != block) {
            material = block
            configVersion++
        }
    }

    /**
     * Create and remember initial cupertino adaptation state
     * */
    @Composable
    protected abstract fun rememberCupertinoAdaptation(): C

    /**
     * Create and remember initial material adaptation state
     * */
    @Composable
    protected abstract fun rememberMaterialAdaptation(): M

    @Composable
    internal fun rememberUpdatedCupertinoAdaptation(): C =
        key(configVersion) {
            rememberCupertinoAdaptation().also { cupertino.invoke(it) }
        }

    @Composable
    internal fun rememberUpdatedMaterialAdaptation(): M =
        key(configVersion) {
            rememberMaterialAdaptation().also { material.invoke(it) }
        }
}
