package com.appchef.expense_tracker_app.view.fragment

import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.appchef.expense_tracker_app.databinding.FragmentExpenseTrackBinding
import com.appchef.expense_tracker_app.model.sms.MonthlyExpenses
import com.appchef.expense_tracker_app.model.sms.SmsModel
import com.appchef.expense_tracker_app.utils.MonthUtility
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate


class ExpenseTrackFragment : Fragment() {

    // binding object.
    private lateinit var expenseTrackBinding: FragmentExpenseTrackBinding

    // ArrayList for Users SMS and Last 12 MonthExpense List
    private var smsList= arrayListOf<SmsModel>()
    private var totalSpentListLastTwelveMonth = arrayListOf<MonthlyExpenses>()

    // View Model Object.
//    private val expenseDetailViewModel: ExpenseRecordViewModel by viewModels {
//        ExpenseTrackerViewModelFactory((requireActivity().application as ExpenseTrackerApplication).repository)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        expenseTrackBinding = FragmentExpenseTrackBinding.inflate(inflater, container, false)

        readMsg()

       val monthlyExpense=ArrayList<BarEntry>()

        totalSpentListLastTwelveMonth.sortBy { it.year }

        monthlyExpense.add(BarEntry(0f, totalSpentListLastTwelveMonth[0].expense.toFloat()))
        monthlyExpense.add(BarEntry(1f, totalSpentListLastTwelveMonth[1].expense.toFloat()))
        monthlyExpense.add(BarEntry(2f, totalSpentListLastTwelveMonth[2].expense.toFloat()))
        monthlyExpense.add(BarEntry(3f, totalSpentListLastTwelveMonth[3].expense.toFloat()))
        monthlyExpense.add(BarEntry(4f, totalSpentListLastTwelveMonth[4].expense.toFloat()))
        monthlyExpense.add(BarEntry(5f, totalSpentListLastTwelveMonth[5].expense.toFloat()))
        monthlyExpense.add(BarEntry(6f, totalSpentListLastTwelveMonth[6].expense.toFloat()))
        monthlyExpense.add(BarEntry(7f, totalSpentListLastTwelveMonth[7].expense.toFloat()))
        monthlyExpense.add(BarEntry(8f, totalSpentListLastTwelveMonth[8].expense.toFloat()))
        monthlyExpense.add(BarEntry(9f, totalSpentListLastTwelveMonth[9].expense.toFloat()))
        monthlyExpense.add(BarEntry(10f, totalSpentListLastTwelveMonth[10].expense.toFloat()))
        monthlyExpense.add(BarEntry(11f, totalSpentListLastTwelveMonth[11].expense.toFloat()))

        val xAxisLabel: ArrayList<String> = ArrayList()
        xAxisLabel.add(totalSpentListLastTwelveMonth[0].month + totalSpentListLastTwelveMonth[0].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[1].month+ totalSpentListLastTwelveMonth[1].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[2].month+ totalSpentListLastTwelveMonth[2].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[3].month+ totalSpentListLastTwelveMonth[3].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[4].month+ totalSpentListLastTwelveMonth[4].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[5].month+ totalSpentListLastTwelveMonth[5].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[6].month+ totalSpentListLastTwelveMonth[6].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[7].month+ totalSpentListLastTwelveMonth[7].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[8].month+ totalSpentListLastTwelveMonth[8].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[9].month+ totalSpentListLastTwelveMonth[9].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[10].month+ totalSpentListLastTwelveMonth[10].year)
        xAxisLabel.add(totalSpentListLastTwelveMonth[11].month+ totalSpentListLastTwelveMonth[11].year)

       val xAxis: XAxis = expenseTrackBinding.barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE;
        xAxis.setDrawGridLines(false)


         val yAxis = expenseTrackBinding.barChart.axisRight;
        yAxis.isEnabled = false

        val barDataSet=BarDataSet(monthlyExpense,"")
        barDataSet.valueTextSize=10f


        val data = BarData(barDataSet)

        expenseTrackBinding.barChart.xAxis.valueFormatter =  IndexAxisValueFormatter(xAxisLabel);
        val desc=Description()
        desc.text=""
       expenseTrackBinding.barChart.description=desc

        expenseTrackBinding.barChart.data = data
        expenseTrackBinding.barChart.setVisibleXRangeMaximum(3.5f)
        expenseTrackBinding.barChart.moveViewToX(5f)
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        xAxis.granularity = 1f

        expenseTrackBinding.barChart.animateY(1000);
        return expenseTrackBinding.root
    }

    private fun readMsg() {
        val smsBuilder = StringBuilder()

        val SMS_URI_ALL = "content://sms/"

        try {
            val uri= Uri.parse(SMS_URI_ALL)

            val cur: Cursor? = requireActivity().contentResolver.query(
                uri,
                null,
                null,
                null,
                "date desc"
            )

            if (cur!!.moveToFirst()) {

                do {
                    val id = cur.getString(cur.getColumnIndex("_id"))
                    val msgBody = cur.getString(cur.getColumnIndex("body"))
                    val msgAddress = cur.getString(cur.getColumnIndex("address"))
                    val msgTime = cur.getString(cur.getColumnIndex("date")).toLong()


                    parsingTransactionalSms(msgTime, msgBody, id, msgAddress)


                } while (cur.moveToNext())
                if (!cur.isClosed) {
                    cur.close()
                }
            } else {
                smsBuilder.append("no result!")
            }

            monthWiseTracking()

        }catch (ex: SQLiteException){
            ex.message?.let { Log.d("TEST", it) }
        }
    }
    private fun parsingTransactionalSms(
        msgTime: Long,
        msgBody: String,
        id: String,
        msgAddress: String
    ) {
        if (System.currentTimeMillis() - msgTime <= 28927800000) {
            if ((msgBody.contains("sent to", true) || msgBody.contains("to block card", true) || msgBody.contains(
                    "debited",
                    true
                ) || msgBody.contains("transferred", true) || msgBody.contains("withdrawn", true) || msgBody.contains(
                    "debit",
                    true
                ))) {

                val smsModel = SmsModel(id, msgBody, msgAddress, msgTime)

                smsList.add(smsModel)
                Log.d(
                    "TEST",
                    "Rs ${smsModel.amountPaid} Spent from ${smsModel.bankName} on ${
                        MonthUtility.hmMonth[smsModel.monthOfSms]
                    } ${smsModel.yearOfSms}"
                )
            }
        }
    }
    private fun monthWiseTracking() {
        //Jan Month List
        val msgListOfJan = smsList.filter { it.monthOfSms == 0 }
        var expenseJan = 0.0
        msgListOfJan.forEach {
            expenseJan += it.amountPaid
        }
        if (expenseJan != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Jan",
                    msgListOfJan[0].yearOfSms,
                    expenseJan
                )
            )
        }


        //Feb Month List
        val msgListOfFeb = smsList.filter { it.monthOfSms == 1 }
        var expenseFeb = 0.0
        msgListOfFeb.forEach {
            expenseFeb += it.amountPaid
        }
        if (expenseFeb != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Feb",
                    msgListOfFeb[0].yearOfSms,
                    expenseFeb
                )
            )
        }

        //Mar Month List
        val msgListOfMar = smsList.filter { it.monthOfSms == 2 }
        var expenseMar = 0.0
        msgListOfMar.forEach {
            expenseMar += it.amountPaid
        }
        if (expenseMar != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Mar",
                    msgListOfMar[0].yearOfSms,
                    expenseMar
                )
            )
        }

        //Apr Month List
        val msgListOfApr = smsList.filter { it.monthOfSms == 3 }
        var expenseApr = 0.0
        msgListOfApr.forEach {
            expenseApr += it.amountPaid
        }
        if (expenseApr != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Apr",
                    msgListOfApr[0].yearOfSms,
                    expenseApr
                )
            )
        }


        //May Month List
        val msgListOfMay = smsList.filter { it.monthOfSms == 4 }
        var expenseMay = 0.0
        msgListOfMay.forEach {
            expenseMay += it.amountPaid
        }
        if (expenseMay != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "May",
                    msgListOfMay[0].yearOfSms,
                    expenseMay
                )
            )
        }

        //Jun Month List
        val msgListOfJun = smsList.filter { it.monthOfSms == 5 }
        var expenseJun = 0.0
        msgListOfJun.forEach {
            expenseJun += it.amountPaid
        }
        if (expenseJun != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Jun",
                    msgListOfJun[0].yearOfSms,
                    expenseJun
                )
            )
        }
        //Jul Month List
        val msgListOfJul = smsList.filter { it.monthOfSms == 6 }
        var expenseJul = 0.0
        msgListOfJul.forEach {
            expenseJul += it.amountPaid
        }
        if (expenseJul != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Jul",
                    msgListOfJul[0].yearOfSms,
                    expenseJul
                )
            )
        }


        //Aug Month List
        val msgListOfAug = smsList.filter { it.monthOfSms == 7 }
        var expenseAug = 0.0
        msgListOfAug.forEach {
            expenseAug += it.amountPaid
        }
        if (expenseAug != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Aug",
                    msgListOfAug[0].yearOfSms,
                    expenseAug
                )
            )
        }


        //Sep Month List
        val msgListOfSep = smsList.filter { it.monthOfSms == 8 }
        var expenseSep = 0.0
        msgListOfSep.forEach {
            expenseSep += it.amountPaid
        }
        if (expenseSep != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Sep",
                    msgListOfSep[0].yearOfSms,
                    expenseSep
                )
            )
        }

        //Oct Month List
        val msgListOfOct = smsList.filter { it.monthOfSms == 9 }
        var expenseOct = 0.0
        msgListOfOct.forEach {
            expenseOct += it.amountPaid
        }

        if (expenseOct != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Oct",
                    msgListOfOct[0].yearOfSms,
                    expenseOct
                )
            )
        }


        //Nov Month List
        val msgListOfNov = smsList.filter { it.monthOfSms == 10 }
        var expenseNov = 0.0
        msgListOfNov.forEach {
            expenseNov += it.amountPaid
        }
        if (expenseNov != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Nov",
                    msgListOfNov[0].yearOfSms,
                    expenseNov
                )
            )
        }

        //Dec Month List
        val msgListOfDec = smsList.filter { it.monthOfSms == 11 }
        var expenseDec = 0.0
        msgListOfDec.forEach {
            expenseDec += it.amountPaid
        }
        if (expenseDec != 0.0) {
            totalSpentListLastTwelveMonth.add(
                MonthlyExpenses(
                    "Dec",
                    msgListOfDec[0].yearOfSms,
                    expenseDec
                )
            )
        }

        Log.d("TEST", "Total Money Spent for Every month - > $totalSpentListLastTwelveMonth ")
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This is for test purpose only needed to be updated or deleted.
//        expenseDetailViewModel.expenseDetails.observe(viewLifecycleOwner) { expense ->
//            expense.let {
//                for (item in it) {
//
//                    expenseTrackBinding.budgetTv.text = item.amount
//                    expenseTrackBinding.savedTv.text = item.date
//                    expenseTrackBinding.spentTv.text = item.title
//                }
//            }
//        }
    }
}

