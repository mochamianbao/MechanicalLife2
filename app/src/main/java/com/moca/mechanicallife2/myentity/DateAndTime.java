package com.moca.mechanicallife2.myentity;

import java.util.Calendar;

public class DateAndTime {

        public int myYear;
        public int myMonth;
        public int myDay;
        public int myHour;
        public int myMinute;
        public int myWeek;
        public int myDateNumber;
        public Calendar myCalendar;
        public String strWeekFlag;

        public int getMyDateNumber() {

                myDateNumber = myYear*10000+myMonth*100+myDay;
                return myDateNumber;
        }
}
