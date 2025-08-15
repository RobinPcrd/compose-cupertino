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