package com.fortune.paper.core.util

import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarIdentifierISO8601
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSTimeZone
import platform.Foundation.timeZoneWithName

actual fun getTodayDateString(): String {
    val formatter = NSDateFormatter()
    formatter.dateFormat = "yyyy-MM-dd"
    formatter.timeZone = NSTimeZone.timeZoneWithName("Asia/Seoul")!!
    return formatter.stringFromDate(NSDate())
}
