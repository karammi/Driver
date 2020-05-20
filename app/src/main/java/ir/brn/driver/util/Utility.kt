package ir.brn.driver.util

import android.content.Context
import androidx.core.widget.ContentLoadingProgressBar
import com.chandapp.calendar.PersianCalendar
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object Utility {

    private val QR_PATTERN_MAIN = "((\\d[0-9]*?)+(\\d[0-9]*?)+(\\d[0-9]*?)+)"
    private val QR_PATTERN = "(\\d[0-9]*?){10}"


    private val alphaArray = arrayOf("الف", "ب", "پ", "ت", "ث", "ج", "چ", "ح", "خ", "د", "ذ", "ر", "ز", "ژ", "س", "ش", "ص", "ض", "ط", "ظ", "ع", "غ", "ف", "ق", "ک", "گ", "ل", "م", "ن", "و", "ه", "ی")

    fun getPersianAlphabetic(): List<String> {
        return Arrays.asList(*alphaArray)

    }

    fun checkQRPattern(qr: String): Boolean {
        val p = Pattern.compile(QR_PATTERN)
        return p.matcher(qr).matches()
    }


    private fun getTimeMillis(time: String): Long {
        var usedTimeMillis: Long = 0
        try {
            usedTimeMillis = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(time).time
        } catch (ex: ParseException) {
        }

        return usedTimeMillis
    }

    fun getPersianDate(currentDate: String): String {
        val calendar = PersianCalendar()
        calendar.timeInMillis = getTimeMillis(currentDate.trim() + " 00:00:00")
        return calendar.persianLongDate
    }

    fun showProgressBar(context: Context): ContentLoadingProgressBar {
        val pd =ContentLoadingProgressBar(context)
        pd.show()
//        if (pd. != null)
//            pd.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        pd.setContentView(R.layout.progress_dialog)
//        pd.isIndeterminate = true
//        pd.setCancelable(false)
//        pd.setCanceledOnTouchOutside(false)

        return pd
    }

}