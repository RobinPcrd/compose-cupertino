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

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapView

@Composable
actual fun MapView(
    modifier: Modifier,
) {
    AndroidView(
        factory = {
            MapLibre.getInstance(it)
            MapView(it).also { mapView ->
                mapView.getMapAsync { map ->
                    map.setStyle("https://demotiles.maplibre.org/style.json")
                    map.cameraPosition =
                        CameraPosition.Builder().target(LatLng(0.0, 0.0)).zoom(1.0).build()
                }
            }
        },
        modifier = modifier,
    )
}