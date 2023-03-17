package com.vunh.coreapp.common

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.provider.Settings
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.vunh.coreapp.R
import com.vunh.coreapp.SplashActivity
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class Utils {
    companion object {

        fun setAnimationClickItem(view: View, duration: Long = 1000) {
            val animation: Animation = AlphaAnimation(0.3f, 1.0f)
            animation.duration = duration
            view.startAnimation(animation)
        }

        @SuppressLint("HardwareIds")
        fun getDeviceId(context: Context): String {
            return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        }

        fun getWidthScreen(marginHorizontal: Int = 0): Int {
            return Resources.getSystem().displayMetrics.widthPixels - marginHorizontal
        }

        fun getHeightScreen(marginVertical: Int = 0): Int {
            return Resources.getSystem().displayMetrics.widthPixels - marginVertical
        }

        fun hideKeyboard(activity: Activity) {
            val imm: InputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            var view = activity.currentFocus
            if (view == null) {
                view = View(activity)
            }
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun isValidPhone(phone: String): Boolean {
            return phone.substring(0,1) == "0"
        }

        fun callWithPhoneNumber(context: Context, phoneNumber: String) {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            context.startActivity(callIntent)
        }

        private const val TRIM_START_1 = "<start1>"
        private const val TRIM_END_1 = "<end1>"
        fun addOneClickableSpanTextView(
            textView: TextView,
            text: String,
            clickableSpan: ClickableSpan?
        ) {
            var contentText = text
            val tClickableText = contentText.substring(
                contentText.indexOf(TRIM_START_1) + 8,
                contentText.indexOf(TRIM_END_1)
            )
            contentText = contentText.replace(TRIM_START_1, "")
            contentText = contentText.replace(TRIM_END_1, "")
            val spanString = SpannableString(contentText)
            spanString.setSpan(
                clickableSpan, contentText.indexOf(tClickableText),
                contentText.indexOf(tClickableText) + tClickableText.length,
                Spanned.SPAN_EXCLUSIVE_INCLUSIVE
            )
            textView.movementMethod = LinkMovementMethod.getInstance()
            textView.text = spanString
        }

        fun setPositionView(positionX: Int, width: Int, rootView: ConstraintLayout, viewId: Int) {
            val cs = ConstraintSet()
            cs.clone(rootView)
            val bias = positionX.toFloat() / width
            Log.e("TLOG", "positionX $positionX  width: $width  bias: $bias")
            cs.setHorizontalBias(viewId, bias)
            cs.applyTo(rootView)
        }

        fun getScreenShot(view: View): Bitmap {
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            val bgDrawable = view.background
            if (bgDrawable != null) {
                bgDrawable.draw(canvas)
            } else {
                canvas.drawColor(Color.WHITE)
            }
            view.draw(canvas)
            return bitmap
        }

        fun shareBitmap(context: Context, bitmap: Bitmap, fileName: String) {
            //---Save bitmap to external cache directory---//
            //get cache directory
            val cachePath = File(context.externalCacheDir, "core_images/")
            cachePath.mkdirs()

            //create png file
            val file = File(cachePath, "${fileName}.png")
            val fileOutputStream: FileOutputStream
            try {
                fileOutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            //---Share File---//
            //get file uri
            val myImageFileUri = FileProvider.getUriForFile(
                context,
                context.packageName.toString() + ".provider",
                file
            )

            //create a intent
            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.putExtra(Intent.EXTRA_STREAM, myImageFileUri)
            intent.type = "image/png"
            context.startActivity(Intent.createChooser(intent, "Share with"))
        }

        //region Permissions
        fun isAcceptPermissions(context: Context,permissions: Array<String>): Boolean {
          for (permission in permissions){
              if( ContextCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED)
                  return false
          }
            return true
        }

        fun restartApp(activity: Activity) {
            val intent =  Intent(activity, SplashActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            activity.finish()
            activity.overridePendingTransition(0, 0)
            activity.startActivity(intent)
        }


//        fun gotoProduct(fragmentActivity: CustomFragmentActivity) {
//            val fragment = ProductFragment()
//            fragmentActivity.supportFragmentManager.commit {
//                addToBackStack(ProductFragment.TAG)
//                replace(R.id.fragment_container_view_home, fragment)
//            }
//        }

//        fun backToCheckLocationIssue(fragmentActivity: CustomFragmentActivity) {
//            fragmentActivity.supportFragmentManager.popBackStack(CheckLocationIssueFragment.TAG, 0)
//        }

        fun parseListToSplitString(list: MutableList<String>): String {
            var newText = ""
            if(list.isNotEmpty()) {
                for (i in 0 until list.size) {
                    if(i > 0) newText += ", "
                    newText += list[i]
                }
            }
            return newText
        }

        const val LOCALE_VN = "vi"
        const val FORMAT_DATE = "yyyy-MM-dd'T'HH:mm:ss"
        fun String.toDataUI(): Date? {
            var result: Date? = null
            val sdf = SimpleDateFormat(FORMAT_DATE, Locale(LOCALE_VN))
            try {
                result = sdf.parse(this)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return result
        }

        fun Date.toCalendar(): Calendar {
            val result = Calendar.getInstance()
            result.time = this
            return result
        }
    }
}
