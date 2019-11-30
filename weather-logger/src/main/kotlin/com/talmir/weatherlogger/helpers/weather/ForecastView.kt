package com.talmir.weatherlogger.helpers.weather

import android.animation.ArgbEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ArrayRes
import androidx.annotation.RequiresApi
import com.talmir.weatherlogger.R
import java.util.Locale

class ForecastView : LinearLayout {
    private var gradientPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var currentGradient: IntArray = IntArray(3)
    private var weatherDescription: TextView
    private var weatherTemperature: TextView
    private var weatherImage: ImageView
    private var evaluator: ArgbEvaluator = ArgbEvaluator()
    private var sunset: Long = 0
    private var sunrise: Long = 0
    private var now: Long = 0

    companion object {
        private const val FACTOR: Long = 1000
    }

    init {
        setWillNotDraw(false)
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL
        View.inflate(context, R.layout.view_forecast, this)
        weatherDescription = findViewById(R.id.weather_description)
        weatherImage = findViewById(R.id.weather_image)
        weatherTemperature = findViewById(R.id.weather_temperature)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private fun initGradient() {
        val centerX = width * 0.5f
        val gradient: Shader = LinearGradient(
            centerX, 0f, centerX, height.toFloat(),
            currentGradient, null,
            Shader.TileMode.MIRROR
        )
        gradientPaint.shader = gradient
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (currentGradient.isEmpty())
            initGradient()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), gradientPaint)
        super.onDraw(canvas)
    }

    fun setForecast(forecast: Forecast) {
        val weatherType = forecast.weatherType
        currentGradient = weatherToGradient(weatherType)
        sunrise = forecast.sunrise * FACTOR
        sunset = forecast.sunset * FACTOR
        now = System.currentTimeMillis()
        if (width != 0 && height != 0)
            initGradient()
        weatherDescription.text = weatherType
        weatherTemperature.text = String.format(Locale.ROOT, "%d", forecast.temperature)
        invalidate()
        weatherImage.animate()
            .scaleX(1f).scaleY(1f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(300)
            .start()
    }

    fun onScroll(fraction: Float, oldForecast: Forecast, newForecast: Forecast) {
        weatherImage.scaleX = fraction
        weatherImage.scaleY = fraction
        currentGradient = mix(
            fraction,
            weatherToGradient(newForecast.weatherType),
            weatherToGradient(oldForecast.weatherType))
        initGradient()
        invalidate()
    }

    private fun mix(fraction: Float, startColors: IntArray, endColors: IntArray) =
        intArrayOf(
            (evaluator.evaluate(fraction, startColors[0], endColors[0]) as Int),
            (evaluator.evaluate(fraction, startColors[1], endColors[1]) as Int),
            (evaluator.evaluate(fraction, startColors[2], endColors[2]) as Int)
        )

    private fun weatherToGradient(weatherType: String) =
        if (now in (sunset + 1) until sunrise) { // darken colors
//                        switch (weatherType) {
//                            case PERIODIC_CLOUDS:
//                                return colors(R.array.gradientPeriodicClouds);
//                            case CLOUDY:
//                                return colors(R.array.gradientCloudy);
//                            case MOSTLY_CLOUDY:
//                                return colors(R.array.gradientMostlyCloudy);
//                            case PARTLY_CLOUDY:
//                                return colors(R.array.gradientPartlyCloudy);
//                            case Clear:

            colors(R.array.gradientClear)
            //                default:
            //                    throw new IllegalArgumentException();
            //            }
        } else { // lighten colors
            when (weatherType) {
                WeatherTypes.THUNDERSTORM -> colors(R.array.gradientThunderstorm)
                WeatherTypes.DRIZZLE -> colors(R.array.gradientDrizzle)
                WeatherTypes.RAIN -> colors(R.array.gradientRain)
                WeatherTypes.SNOW -> colors(R.array.gradientSnow)
                WeatherTypes.ATMOSPHERE -> colors(R.array.gradientAtmosphere)
                WeatherTypes.CLEAR -> colors(R.array.gradientClear)
                WeatherTypes.CLOUDS -> colors(R.array.gradientClouds)
                else -> {
                    println(weatherType)
                    colors(R.array.gradientClouds)
                    //throw IllegalArgumentException()
                }
            }
        }

    private fun colors(@ArrayRes res: Int) =
        context.resources.getIntArray(res)
}
