package dev.olaore.measuringcustomviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.min

class TestView @JvmOverloads
    constructor(
        private val ctx: Context,
        private val attributeSet: AttributeSet? = null,
        private val defStyleAttr: Int = 0
    ) : View(ctx, attributeSet, defStyleAttr) {

    private var circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.GRAY)
        canvas?.drawCircle(
            width / 2f, height / 2f, 40f, circlePaint
        )

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val desiredWidth = suggestedMinimumWidth + paddingRight + paddingLeft
        val desiredHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        Log.v("MeasuringCustomViews", "$suggestedMinimumHeight $suggestedMinimumWidth")

        Log.v("MeasuringCustomViews", "Height Spec: ${ MeasureSpec.toString(heightMeasureSpec) }")
        Log.v("MeasuringCustomViews", "Width Spec: ${ MeasureSpec.toString(widthMeasureSpec) }")

        setMeasuredDimension(
                measureDimension(desiredWidth, widthMeasureSpec),
                measureDimension(desiredHeight, heightMeasureSpec)
        )

    }

    private fun measureDimension(desiredSize: Int, measuredSpec: Int): Int {
        var result = 0
        val mode = MeasureSpec.getMode(measuredSpec)
        val specSize = MeasureSpec.getSize(measuredSpec)

        if (mode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (mode == MeasureSpec.AT_MOST) {
                result = min(result, specSize)
            }
        }

        return result

    }

}