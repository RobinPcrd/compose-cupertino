import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.MapKit.MKMapView

@Composable
actual fun MapView(
    modifier: Modifier,
) {
    UIKitView(
        factory = {
            MKMapView()
        },
        modifier = modifier
    )
}