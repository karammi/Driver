package ir.brn.driver.ext

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

internal inline fun <reified A : AppCompatActivity> intentOf(context: Context): Intent {
    return Intent(context, A::class.java)
}