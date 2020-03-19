package az.talmir.weatherlogger.helpers

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ConstantsTest {

    @Test
    fun buildApiUrl_returnsFullApiUrl() {
        val apiValidUrl = "https://api.openweathermap.org/data/2.5/group?id=587084,584923,147613,585156,586482&units=metric&appid=d64bc1686044c643b68f9573b2a5cea2"

        val generatedUrl = Constants.buildApiUrl()

        assertThat(generatedUrl, `is`(apiValidUrl))
    }
}