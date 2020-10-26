package ude.student.fadu.view.component

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import com.google.android.material.slider.Slider
import com.google.android.material.slider.Slider.OnChangeListener

class VerticalSlider @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs) {

	private val slider: Slider = Slider(context, attrs)

	init {
		slider.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, Gravity.CENTER)
		slider.rotation = 90f
		addView(slider)
	}

	@Synchronized
	override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
		slider.layoutParams.width = MeasureSpec.getSize(heightMeasureSpec) - slider.haloRadius - 2
		super.onMeasure(widthMeasureSpec, heightMeasureSpec)
		setMeasuredDimension(slider.measuredHeight, measuredHeight)
	}

	fun addOnChangeListener(listener: OnChangeListener) = slider.addOnChangeListener(listener)

	fun setValue(value: Float) {
		slider.value = value
	}

}