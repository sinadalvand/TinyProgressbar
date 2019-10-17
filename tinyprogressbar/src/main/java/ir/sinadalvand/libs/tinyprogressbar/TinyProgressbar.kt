package ir.sinadalvand.libs.tinyprogressbar

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Animatable
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.ColorUtils
import ir.sinadalvand.libs.tinyprogressbar.UiUtils.dpToPx


class TinyProgressbar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), Animator.AnimatorListener, Animatable {


    private val backpaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val front = Paint(Paint.ANTI_ALIAS_FLAG)
    private var morph = 0f
    private val anim = ValueAnimator.ofFloat(0f, 1f)


    private var roundCorner = true
    private var frontColor = Color.RED
    private var bgColor = ColorUtils.setAlphaComponent(frontColor, 70)
    private var progressPercent = 0.3f
    private var progressWidth = -1f
    private var progressHeight = dpToPx(3).toFloat()
    private var animationDuration = 1000


    init {

        val a = context.obtainStyledAttributes(attrs, R.styleable.TinyProgressbar)
        roundCorner = a.getBoolean(R.styleable.TinyProgressbar_roundCorner, true)
        frontColor = a.getColor(R.styleable.TinyProgressbar_progressColor, frontColor)
        bgColor = a.getColor(R.styleable.TinyProgressbar_bgColor, ColorUtils.setAlphaComponent(frontColor, 70))
        progressWidth = a.getDimension(R.styleable.TinyProgressbar_progressSize, -1f)
        progressPercent = a.getFloat(R.styleable.TinyProgressbar_progressRelative, progressPercent)
        progressHeight = a.getDimension(R.styleable.TinyProgressbar_progressHeight, progressHeight)
        animationDuration = a.getInteger(R.styleable.TinyProgressbar_animationDuration, animationDuration)
        val autoStart = a.getBoolean(R.styleable.TinyProgressbar_autoStart, true)
        a.recycle()


        if (roundCorner) {
            backpaint.strokeCap = Paint.Cap.ROUND
            front.strokeCap = Paint.Cap.ROUND
        }


        backpaint.color = bgColor
        front.color = frontColor
        backpaint.strokeWidth = progressHeight.toFloat()
        front.strokeWidth = progressHeight.toFloat()




        anim.duration = animationDuration.toLong()
        anim.repeatCount = ValueAnimator.INFINITE
        anim.repeatMode = ValueAnimator.REVERSE
        anim.addListener(this)
        anim.addUpdateListener {
            morph = it.animatedValue as Float
            invalidate()
        }

        if (autoStart)
            post {
                anim.start()
            }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(manageDimension(widthMeasureSpec, dpToPx(100)), manageDimension(heightMeasureSpec, dpToPx(10)))
    }

    private fun manageDimension(valueMeasureSpec: Int, value_desired: Int): Int {
        var valueFinal = 0
        val value = MeasureSpec.getSize(valueMeasureSpec)
        val valueMode = MeasureSpec.getMode(valueMeasureSpec)
        if (valueMode == MeasureSpec.EXACTLY) {
            valueFinal = value
        } else if (valueMode == MeasureSpec.AT_MOST) {
            valueFinal = Math.min(value, value_desired)
        } else {
            valueFinal = value_desired
        }
        return valueFinal
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        val offset = 5

        canvas.drawLine(offset + paddingLeft * 1f, measuredHeight / 2f, measuredWidth.toFloat() - offset - paddingRight, measuredHeight / 2f, backpaint)
        var size = if (progressWidth > 0) progressWidth - paddingLeft - paddingRight else (measuredWidth * progressPercent) - paddingLeft - paddingRight
        size = if (size < 0) (measuredWidth - paddingLeft - paddingRight) * 0.5f else size


        val min = paddingLeft + offset
        val max = measuredWidth - paddingRight - offset
        val seek = min + ((max - min) * morph)

        var start = seek - (size / 2)
        var end = seek + (size / 2)

        start = if (start < min) min.toFloat() else start
        end = if (end > max) max.toFloat() else end


        canvas.drawLine(start, measuredHeight / 2f, end, measuredHeight / 2f, front)

    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(visibility)
        if (visibility == GONE || visibility == INVISIBLE) {
            anim.cancel()
        } else {
            anim.start()
        }
    }

    override fun onAnimationEnd(animation: Animator?) {

    }

    override fun onAnimationStart(animation: Animator?) {

    }

    override fun onAnimationRepeat(animation: Animator?) {

    }

    override fun onAnimationCancel(animation: Animator?) {
        morph = 0.0f
        invalidate()
    }

    override fun isRunning(): Boolean {
        return anim.isRunning
    }

    override fun start() {
        anim.start()
    }

    override fun stop() {
        anim.cancel()
    }

    /**
     * change progressbar background
     *
     * @param color Int
     */
    fun setProgressbarBackground(color: Int) {
        this.bgColor = color
        backpaint.color = bgColor
        invalidate()
    }


    /**
     * change Progressbar handle Color
     *
     * @param color Int
     */
    fun setProgressbarColor(color: Int) {
        this.frontColor = color
        front.color = frontColor
        invalidate()
    }


    /**
     * change Progressbar Handle Width in (Px dim)
     *
     * if you use this method , the [TinyProgressbar.setProgressRelative] method not work .
     *
     * @param px Int
     */
    fun setProgressWidth(px: Int) {
        this.progressWidth = px.toFloat()
        invalidate()
    }


    /**
     *
     * change Progressbar size Relative to total width
     *
     * @param percent Float
     */
    fun setProgressRelative(percent: Float) {
        this.progressPercent = percent
        invalidate()
    }


    /**
     *
     * change Progressbar height
     * [TinyProgressbar.setProgressRelative]
     * @param px Int
     */
    fun setProgressHeight(px: Int) {
        this.progressHeight = px.toFloat()
        backpaint.strokeWidth = progressHeight.toFloat()
        front.strokeWidth = progressHeight.toFloat()
        invalidate()
    }


    /**
     * set animation duration
     * @param duration Long
     */
    fun setAnimationDuration(duration: Long) {
        anim.duration = duration
    }
}