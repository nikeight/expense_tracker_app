package com.appchef.expense_tracker_app.model.sms

import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

data class SmsModel(val id:String, val body:String, val address:String, val msgTime:Long){
    var bankName:String = address.subSequence(3,9) as String
    var amountPaid:Double = 0.0
    var monthOfSms= Date(msgTime).month
    var yearOfSms= Date(msgTime).year+1900

    init {
        //detect amount using regex
        val p: Pattern = Pattern.compile("(?i)(?:(?:RS|INR|MRP)\\.?\\s?)(\\d+(:?\\,\\d+)?(\\,\\d+)?(\\.\\d{1,2})?)")
        val m: Matcher = p.matcher(body)
        if (m.find(0)) {
            amountPaid = m.group(1).replace(",","").toDouble()
        }


    }
}
