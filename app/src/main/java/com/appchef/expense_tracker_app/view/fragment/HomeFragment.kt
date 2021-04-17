package com.appchef.expense_tracker_app.view.fragment

import android.app.Activity
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.appchef.expense_tracker_app.databinding.FragmentHomeBinding
import com.appchef.expense_tracker_app.model.sms.MonthlyExpenses
import com.appchef.expense_tracker_app.model.sms.SmsModel
import com.appchef.expense_tracker_app.utils.MonthUtility

class HomeFragment : Fragment() {

    // Bindings.
    private lateinit var homeBinding: FragmentHomeBinding

    // ArrayList for Users SMS and Last 12 MonthExpense List
    private var smsList= arrayListOf<SmsModel>()
    private var totalSpentListLastTwelveMonth = arrayListOf<MonthlyExpenses>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding =
            FragmentHomeBinding.inflate(inflater, container, false)
        if(ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                requireContext() as Activity,
                arrayOf(android.Manifest.permission.READ_SMS),
                111
            )
        }
        else{
            readMsg()
        }

        return homeBinding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==111 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            readMsg()

        }
    }

    private fun readMsg() {
        val smsBuilder = StringBuilder()

        val SMS_URI_ALL = "content://sms/"

        try {
            val uri= Uri.parse(SMS_URI_ALL)

            val cur: Cursor? = requireActivity().contentResolver.query(uri, null, null, null, "date desc")

            if (cur!!.moveToFirst()) {

                do {
                    val id = cur.getString(cur.getColumnIndex("_id"))
                    val msgBody = cur.getString(cur.getColumnIndex("body"))
                    val msgAddress = cur.getString(cur.getColumnIndex("address"))
                    val msgTime = cur.getString(cur.getColumnIndex("date")).toLong()


                    if (!msgAddress.isNullOrEmpty())
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
    private fun parsingTransactionalSms(msgTime: Long, msgBody: String, id: String, msgAddress: String) {
        if (System.currentTimeMillis() - msgTime <= 28927800000) {
            if ((msgBody.contains("sent to", true) || msgBody.contains("to block card", true) || msgBody.contains("debited", true) || msgBody.contains("transferred", true) || msgBody.contains("withdrawn", true) || msgBody.contains("debit", true))) {

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
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Jan", msgListOfJan[0].yearOfSms, expenseJan))
        }


        //Feb Month List
        val msgListOfFeb = smsList.filter { it.monthOfSms == 1 }
        var expenseFeb = 0.0
        msgListOfFeb.forEach {
            expenseFeb += it.amountPaid
        }
        if (expenseFeb != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Feb", msgListOfFeb[0].yearOfSms, expenseFeb))
        }

        //Mar Month List
        val msgListOfMar = smsList.filter { it.monthOfSms == 2 }
        var expenseMar = 0.0
        msgListOfMar.forEach {
            expenseMar += it.amountPaid
        }
        if (expenseMar != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Mar", msgListOfMar[0].yearOfSms, expenseMar))
        }

        //Apr Month List
        val msgListOfApr = smsList.filter { it.monthOfSms == 3 }
        var expenseApr = 0.0
        msgListOfApr.forEach {
            expenseApr += it.amountPaid
        }
        if (expenseApr != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Apr", msgListOfApr[0].yearOfSms, expenseApr))
        }


        //May Month List
        val msgListOfMay = smsList.filter { it.monthOfSms == 4 }
        var expenseMay = 0.0
        msgListOfMay.forEach {
            expenseMay += it.amountPaid
        }
        if (expenseMay != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("May", msgListOfMay[0].yearOfSms, expenseMay))
        }

        //Jun Month List
        val msgListOfJun = smsList.filter { it.monthOfSms == 5 }
        var expenseJun = 0.0
        msgListOfJun.forEach {
            expenseJun += it.amountPaid
        }
        if (expenseJun != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Jun", msgListOfJun[0].yearOfSms, expenseJun))
        }
        //Jul Month List
        val msgListOfJul = smsList.filter { it.monthOfSms == 6 }
        var expenseJul = 0.0
        msgListOfJul.forEach {
            expenseJul += it.amountPaid
        }
        if (expenseJul != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Jul", msgListOfJul[0].yearOfSms, expenseJul))
        }


        //Aug Month List
        val msgListOfAug = smsList.filter { it.monthOfSms == 7 }
        var expenseAug = 0.0
        msgListOfAug.forEach {
            expenseAug += it.amountPaid
        }
        if (expenseAug != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Aug", msgListOfAug[0].yearOfSms, expenseAug))
        }


        //Sep Month List
        val msgListOfSep = smsList.filter { it.monthOfSms == 8 }
        var expenseSep = 0.0
        msgListOfSep.forEach {
            expenseSep += it.amountPaid
        }
        if (expenseSep != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Sep", msgListOfSep[0].yearOfSms, expenseSep))
        }

        //Oct Month List
        val msgListOfOct = smsList.filter { it.monthOfSms == 9 }
        var expenseOct = 0.0
        msgListOfOct.forEach {
            expenseOct += it.amountPaid
        }

        if (expenseOct != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Oct", msgListOfOct[0].yearOfSms, expenseOct))
        }


        //Nov Month List
        val msgListOfNov = smsList.filter { it.monthOfSms == 10 }
        var expenseNov = 0.0
        msgListOfNov.forEach {
            expenseNov += it.amountPaid
        }
        if (expenseNov != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Nov", msgListOfNov[0].yearOfSms, expenseNov))
        }

        //Dec Month List
        val msgListOfDec = smsList.filter { it.monthOfSms == 11 }
        var expenseDec = 0.0
        msgListOfDec.forEach {
            expenseDec += it.amountPaid
        }
        if (expenseDec != 0.0) {
            totalSpentListLastTwelveMonth.add(MonthlyExpenses("Dec", msgListOfDec[0].yearOfSms, expenseDec))
        }

        Log.d("TEST", "Total Money Spent for Every month - > $totalSpentListLastTwelveMonth ")
    }

}