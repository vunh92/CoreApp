package com.vunh.coreapp.ui.custom_dialog.calendar

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.DatePicker
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.children
import com.vunh.coreapp.R
import com.vunh.coreapp.common.daysOfWeekFromLocale
import com.vunh.coreapp.common.next
import com.vunh.coreapp.common.previous
import com.vunh.coreapp.common.setTextColorRes
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

class VSACalendarPicker : FrameLayout {

    private var selectedDate: LocalDate? = null

    @SuppressLint("NewApi")
    private val today = LocalDate.now()

    @SuppressLint("NewApi")
    private var currentMonth = YearMonth.now()
    @SuppressLint("NewApi")
    private var startMonth = currentMonth.minusMonths(10)
    @SuppressLint("NewApi")
    private var endMonth = currentMonth.plusMonths(10)

    @SuppressLint("NewApi")
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MMMM")

    private var dateChangeListener: DatePicker.OnDateChangedListener? = null

    val year: Int
        @SuppressLint("NewApi")
        get() {
           return selectedDate?.year ?: LocalDate.now().year
        }

    val month: Int
        @SuppressLint("NewApi")
        get() {
            return selectedDate?.monthValue ?: LocalDate.now().monthValue
        }

    val dayOfMonth: Int
        @SuppressLint("NewApi")
        get() {
            return selectedDate?.dayOfMonth ?: LocalDate.now().dayOfMonth
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    @SuppressLint("NewApi")
    fun init(
        year: Int,
        monthOfYear: Int,
        dayOfMonth: Int,
        dateChangeListener: DatePicker.OnDateChangedListener
    ) {
        currentMonth = YearMonth.of(year, monthOfYear)
        startMonth = currentMonth.minusMonths(10)
        endMonth = currentMonth.plusMonths(10)

        selectedDate = LocalDate.of(year, monthOfYear, dayOfMonth)

        this.dateChangeListener = dateChangeListener
        setupData()
    }

    @SuppressLint("NewApi")
    private fun setupData() {
        val daysOfWeek = daysOfWeekFromLocale()

        findViewById<LinearLayout>(R.id.legendLayout).children.forEachIndexed { index, view ->
            (view as TextView).apply {
                text =
                    daysOfWeek[index].getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toUpperCase(
                        Locale.ENGLISH
                    )
            }
        }

        findViewById<CalendarView>(R.id.exOneCalendar).setup(startMonth, endMonth, daysOfWeek.first())
        findViewById<CalendarView>(R.id.exOneCalendar).scrollToMonth(currentMonth)

        @SuppressLint("NewApi")
        class DayViewContainer(view: View) : ViewContainer(view) {
            // Will be set when this container is bound. See the dayBinder.
            lateinit var day: CalendarDay

            val textView = view.findViewById<TextView>(R.id.exOneDayText)

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        if (selectedDate == day.date) {
                            findViewById<CalendarView>(R.id.exOneCalendar).notifyDayChanged(day)
                        } else {
                            val oldDate = selectedDate
                            selectedDate = day.date
                            findViewById<CalendarView>(R.id.exOneCalendar).notifyDateChanged(day.date)
                            oldDate?.let {
                                findViewById<CalendarView>(R.id.exOneCalendar).notifyDateChanged(it)
                            }
                        }

                        selectedDate?.let {
                            dateChangeListener?.onDateChanged(
                                null,
                                it.year,
                                it.month.value,
                                it.dayOfMonth
                            )
                        }
                    }
                }
            }
        }


        findViewById<CalendarView>(R.id.exOneCalendar).dayBinder = object : DayBinder<DayViewContainer> {

            override fun create(view: View) = DayViewContainer(view)

            @SuppressLint("NewApi")
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.textView
                textView.text = day.date.dayOfMonth.toString()
                if (day.owner == DayOwner.THIS_MONTH) {
                    when (day.date) {
                        selectedDate -> {
                            textView.setTextColorRes(R.color.calendar_day_text_selected)
                            textView.setBackgroundResource(R.drawable.bg_calendar_selected)
                        }
                        today -> {
                            textView.setTextColorRes(R.color.calendar_today_text)
                            textView.background = null
                        }
                        else -> {
                            textView.setTextColorRes(R.color.calendar_day_text)
                            textView.background = null
                        }
                    }
                } else {
                    textView.text = ""
                    textView.background = null
                }
            }
        }

        findViewById<CalendarView>(R.id.exOneCalendar).monthScrollListener = {
            findViewById<TextView>(R.id.exOneMonthText).text =
                "${monthTitleFormatter.format(it.yearMonth)} ${it.yearMonth.year}"
        }

        findViewById<AppCompatImageView>(R.id.tvwBack).setOnClickListener {
            findViewById<CalendarView>(R.id.exOneCalendar).findFirstVisibleMonth()?.let {
                findViewById<CalendarView>(R.id.exOneCalendar).smoothScrollToMonth(it.yearMonth.previous)
            }
        }

        findViewById<AppCompatImageView>(R.id.tvwNext).setOnClickListener {
            findViewById<CalendarView>(R.id.exOneCalendar).findFirstVisibleMonth()?.let {
                findViewById<CalendarView>(R.id.exOneCalendar).smoothScrollToMonth(it.yearMonth.next)
            }
        }
    }

    private fun init(attrs: AttributeSet) {
        val rootView = inflate(context, R.layout.widget_vsa_calendar_picker, this)
    }

    private fun applyXmlAttributes(attrs: AttributeSet) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.VSACalendarPicker)
        try {

        } finally {
            a.recycle()
        }
    }

}