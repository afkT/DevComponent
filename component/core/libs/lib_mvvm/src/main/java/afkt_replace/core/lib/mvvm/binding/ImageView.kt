package afkt_replace.core.lib.mvvm.binding

import afkt_replace.core.lib.ui.animation.circularRevealedAtCenter
import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import dev.base.DevSource
import dev.engine.image.listener.BitmapListener
import dev.expand.engine.image.display
import dev.expand.engine.log.log_dTag
import dev.mvvm.base.Config

// ========================
// = Image BindingAdapter =
// ========================

private const val TAG = "Image_BindingAdapter"

@BindingAdapter(
    value = ["binding_source_anim", "binding_engine"],
    requireAll = false
)
fun ImageView.bindingImageSourceAnim(
    source: DevSource?,
    engine: String?
) {
    if (Config.printLog(engine)) {
        TAG.log_dTag(
            engine = engine,
            message = "bindingImageSourceAnim\nsource: %s\nengine: %s",
            args = arrayOf(source, engine)
        )
    }
    display(
        engine = engine,
        source = source,
        listener = object : BitmapListener() {
            override fun onStart(source: DevSource?) {
            }

            override fun onResponse(
                source: DevSource?,
                value: Bitmap?
            ) {
                circularRevealedAtCenter()
            }

            override fun onFailure(
                source: DevSource?,
                throwable: Throwable?
            ) {
            }
        }
    )
}