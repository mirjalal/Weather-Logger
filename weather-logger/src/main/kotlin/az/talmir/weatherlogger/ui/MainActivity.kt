package az.talmir.weatherlogger.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import az.talmir.weatherlogger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TIME_INTERVAL = 1000 // # milliseconds, desired time passed between two back presses.
    }
    private var mBackPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }

//    override fun onBackPressed() {
//        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
//            finish()
//            return
//        } else
//            Toast.makeText(baseContext, "Tap again to exit", Toast.LENGTH_LONG).show()
//
//        mBackPressed = System.currentTimeMillis()
//    }
}
