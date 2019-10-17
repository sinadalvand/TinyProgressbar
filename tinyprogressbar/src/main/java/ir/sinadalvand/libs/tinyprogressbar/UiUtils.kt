package ir.sinadalvand.libs.tinyprogressbar

import android.content.res.Resources
import android.util.TypedValue

internal object UiUtils {


    internal fun dpToPx(dp: Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), Resources.getSystem().displayMetrics)
            .toInt()
    }

    internal fun pxToDp(px: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, Resources.getSystem().displayMetrics).toInt()
    }
}