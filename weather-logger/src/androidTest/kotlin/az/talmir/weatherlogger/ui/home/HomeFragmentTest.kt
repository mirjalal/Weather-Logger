package az.talmir.weatherlogger.ui.home

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import az.talmir.weatherlogger.R
import az.talmir.weatherlogger.ServiceLocator
import az.talmir.weatherlogger.data.FakeForecastRepository
import az.talmir.weatherlogger.data.IForecastRepository
import az.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import az.talmir.weatherlogger.helpers.Constants
import az.talmir.weatherlogger.helpers.weather.ForecastAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import java.util.Date

//@RunWith(AndroidJUnit4::class)
//@MediumTest
//@ExperimentalCoroutinesApi
//class HomeFragmentTest {
//
//    private lateinit var fakeRepository: IForecastRepository
//    private val forecasts = listOf(
//        ForecastDataEntity(Constants.BAKU_CITY_ID, 802, 14, 762, 67, 28f, 32131132, 1231124, Date()), // data for Baku
//        ForecastDataEntity(Constants.SUMGAIT_CITY_ID, 511, 16, 762, 82, 36f, 32131132, 1231124, Date()),   // data for Sumgait
//        ForecastDataEntity(Constants.LENKARAN_CITY_ID, 622, 35, 762, 44, 14f, 32131132, 1231124, Date()),  // data for Lenkaran
//        ForecastDataEntity(Constants.SHAMAKHI_CITY_ID, 701, 15, 762, 67, 65f, 32131132, 1231124, Date()),  // data for Shamakhi
//        ForecastDataEntity(Constants.GOYCHAY_CITY_ID, 200, 9, 762, 67, 87f, 32131132, 1231124, Date())    // data for Goychay
//    )
//
//    @Before
//    fun initRepository() {
//        fakeRepository = FakeForecastRepository()
//        ServiceLocator.forecastsRepository = fakeRepository
//    }
//
//    @After
//    fun cleanUp() = runBlockingTest {
//        ServiceLocator.resetRepository()
//    }
//
//    @Test
//    fun clickForecast_navigateToForecastDetailsFragmentOfBaku() = runBlockingTest {
//        val scenario = launchFragmentInContainer<HomeFragment>(Bundle(), R.style.AppTheme)
//        scenario.moveToState(Lifecycle.State.DESTROYED)
//        scenario.moveToState(Lifecycle.State.RESUMED)
//        scenario.moveToState(Lifecycle.State.STARTED)
//        scenario.moveToState(Lifecycle.State.CREATED)
//
//        // GIVEN - on the main screen with a forecast
//        fakeRepository.saveForecastData(forecasts)
//
//
//        val navController = mock(NavController::class.java)
//
//        scenario.onFragment {
//            Navigation.setViewNavController(it.view!!, navController)
//        }
//
//        // WHEN - click on the first item
//        onView(withId(R.id.forecast_view)).perform(click())
//
//        // THEN - verify that we navigate to the first detail screen
//        verify(navController).navigate(
//            HomeFragmentDirections.actionFragmentHomeToFragmentCityForecastDetails(forecasts[0].cityId)
//        )
//    }
//
//    @Test
//    fun clickForecast_navigateToForecastDetailsFragmentOfLenkaran() = runBlockingTest {
//        // GIVEN - on the main screen with a forecast
//        fakeRepository.saveForecastData(forecasts)
//
//        val scenario = launchFragmentInContainer<HomeFragment>(Bundle(), R.style.AppTheme)
//
//        val navController = mock(NavController::class.java)
//
//        scenario.onFragment {
//            Navigation.setViewNavController(it.view!!, navController)
//        }
//
//        // WHEN - click on the first item
//        onView(withId(R.id.forecast_city_picker)).perform(
//            RecyclerViewActions.scrollToPosition<ForecastAdapter.ForecastViewHolder>(2)
//        ) // scroll to Lenkaran city
//
//        delay(2_000)
//        onView(withId(R.id.forecast_view)).perform() // swipe 2 times to go to Lenkaran city
//
//        // THEN - verify that we navigate to the first detail screen
//        verify(navController).navigate(
//            HomeFragmentDirections.actionFragmentHomeToFragmentCityForecastDetails(forecasts[2].cityId)
//        )
//    }
//}
