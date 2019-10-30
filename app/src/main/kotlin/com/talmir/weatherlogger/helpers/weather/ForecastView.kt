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
import android.view.LayoutInflater
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.LinearLayout
import androidx.annotation.ArrayRes
import androidx.annotation.RequiresApi
import coil.api.load
import com.talmir.weatherlogger.R
import com.talmir.weatherlogger.databinding.ViewForecastBinding


class ForecastView : LinearLayout {

    private val FACTOR: Long = 1000

    private val gradientPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var currentGradient: IntArray

    private var binding: ViewForecastBinding

    private val evaluator = ArgbEvaluator()

    private var sunset = 0L
    private var sunrise = 0L
    private var now = 0L

    init {
        setWillNotDraw(false)

        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL

        binding = ViewForecastBinding.inflate(LayoutInflater.from(context))
    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs)

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    @JvmOverloads
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private fun initGradient() {
        val centerX = width * .5f
        val gradient = LinearGradient(
            centerX, 0f, centerX, height.toFloat(),
            currentGradient, null,
            Shader.TileMode.MIRROR)
        gradientPaint.shader = gradient
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (currentGradient != null) {
            initGradient()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), gradientPaint)
        super.onDraw(canvas)
    }

    fun setForecast(forecast: Forecast) {
        val weatherTypes = forecast.weatherType // FIXME:
        currentGradient = weatherToGradient(weatherTypes)
        sunrise = forecast.sunrise * FACTOR
        sunset = forecast.sunset * FACTOR
        now = System.currentTimeMillis()

        if (width != 0 && height != 0)
            initGradient()

        binding.dataWeatherDescription = weatherTypes.toString()
        binding.dataWeatherTemperature = forecast.temperature

//        binding.weatherImage.load(weatherTypes.getWeatherType(1))
        invalidate()

        binding.weatherImage.animate()
            .scaleX(1f).scaleY(1f)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .setDuration(300)
            .start()
    }

    fun onScroll(fraction: Float, oldForecast: Forecast, newForecast: Forecast) {
        binding.weatherImage.scaleX = fraction
        binding.weatherImage.scaleY = fraction
        currentGradient = mix(
            fraction,
            weatherToGradient(newForecast.weatherType),
            weatherToGradient(oldForecast.weatherType)
        )
        initGradient()
        invalidate()
    }

    private fun mix(fraction: Float, startColors: IntArray, endColors: IntArray) =
        intArrayOf(
            (evaluator.evaluate(fraction, startColors[0], endColors[0]) as Int),
            (evaluator.evaluate(fraction, startColors[1], endColors[1]) as Int),
            (evaluator.evaluate(fraction, startColors[2], endColors[2]) as Int)
        )

    private fun weatherToGradient(weatherType: Int) =
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
                else -> throw IllegalArgumentException()
            }
        }

    private fun colors(@ArrayRes res: Int) =
        context.resources.getIntArray(res)
}
