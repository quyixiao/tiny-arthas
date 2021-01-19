package com.arthas.business.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.util.NumberUtil;

import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 时间工具类
 *
 * @author huangyunsong
 * @description
 * @createDate 2015年12月21日
 */
public class DateUtil {
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);


    /**
     * 1秒对应的毫秒数
     */
    public static final long MILLIS_IN_A_SECOND = 1000;
    /**
     * 1分对应的秒数
     */
    public static final long SECONDS_IN_A_MINUTE = 60;
    /**
     * 1小时对应的分钟数
     */
    public static final long MINUTES_IN_AN_HOUR = 60;
    /**
     * 1天对应的小时数
     */
    public static final long HOURS_IN_A_DAY = 24;
    /**
     * 1星期对应的天数
     */
    public static final int DAYS_IN_A_WEEK = 7;
    /**
     * 1年对应的月数
     */
    public static final int MONTHS_IN_A_YEAR = 12;
    /**
     * 1天对应的毫秒数
     */
    public static final long millSecondsInOneDay = HOURS_IN_A_DAY * MINUTES_IN_AN_HOUR * SECONDS_IN_A_MINUTE * MILLIS_IN_A_SECOND;
    /**
     * 1分钟对应的毫秒数
     */
    public static final long millSecondsInOneMinute = SECONDS_IN_A_MINUTE * MILLIS_IN_A_SECOND;

    public static final String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    /**
     * Default date format
     */
    public static final String DEFAULT_DATE = "yyyy-MM-dd";

    public static final String DATE_TIME_MMDDHHMM = "MM/dd HH:mm";

    /**
     * MM-dd HH:mm
     */
    public static final String MONTH_MATCH_PATTERN = "MM-dd HH:mm";

    /**
     * yyyy/MM/dd HH:mm
     */
    public static final String DATE_TIME_MINUTEE = "yyyy/MM/dd HH:mm";

    /**
     * yyyy-MM-dd
     */
    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";

    public static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    /**
     * milliseconds in a second.
     */
    public static final long SECOND = 1000;
    /**
     * milliseconds in a minute.
     */
    public static final long MINUTE = SECOND * 60;
    /**
     * milliseconds in a hour.
     */
    public static final long HOUR = MINUTE * 60;
    /**
     * milliseconds in a day.
     */
    public static final long DAY = 24 * HOUR;
    /**
     * yyyy-MM
     */
    public static final String MONTH_PATTERN = "yyyy-MM";
    /**
     * yyyyMM
     */
    public static final String MONTH_SHOT_PATTERN = "yyyyMM";
    /**
     * yyyyMMdd
     */
    public static final String DEFAULT_PATTERN = "yyyyMMdd";
    /**
     * yyyyMMdd
     */
    public static final String DEFAULT_DAY = "dd";
    /**
     * yyyyMMdd
     */
    public static final String DEFAULT_PATTERN_WITH_HYPHEN = "yyyy-MM-dd";

    public static final String DEFAULT_PATTERN_WITH_DOT = "yyyy.MM.dd";

    public static final String DEFAULT_CHINESE_PATTERN_NOT_YEAH = "MM月dd日";

    public static final String DEFAULT_CHINESE_FULL_PATTERN = "yyyy年MM月dd日HH点mm分";

    public static final String HOUR_PATTERN = "yyyyMMddHH";
    /**
     * yyyyMMddHHmmss
     */
    public static final String FULL_PATTERN = "yyyyMMddHHmmss";
    /**
     * yyyyMMdd HH:mm:ss
     */
    public static final String FULL_STANDARD_PATTERN = "yyyyMMdd HH:mm:ss";
    /**
     * MM.dd HH:mm
     */
    public static final String FULL_MATCH_PATTERN = "MM.dd HH:mm";
    /**
     * HH:mm
     */
    public static final String SHORT_MATCH_PATTERN = "HH:mm";
    /**
     * yyyy-MM-dd HH:mm
     */
    public static final String DATE_TIME_MINUTE = "yyyy-MM-dd HH:mm";
    /**
     * <pre>
     * yyyy-MM-dd HH:mm:ss
     * </pre>
     */
    public static final String DATE_TIME_SHORT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_HOUR = "yyyy-MM-dd HH";

    /**
     * <pre>
     * yyyy-MM-dd HH:mm:ss.SSS
     * </pre>
     */
    public static final String DATE_TIME_FULL = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_TIME_FULL_ALL = "yyyyMMddHHmmssSSS";
    public static final String NO_END_DATE_FORMAT = "9999-12-31 23:59:59";
    public static final Long NO_END_DATE_TIME = 253402271999000L;
    public static final Date NO_END_DATE = new Date(NO_END_DATE_TIME);
    public static final String FINAL_END_DATE_STR = "9999-12-31";

    /**
     * 一个月
     */
    public static final int DAY_OF_MONTH = 30;

    /**
     * 一星期
     */
    public static final int DAY_OF_WEEK = 7;

    /**
     * yyyyMM
     */
    public static final String YEAR_MONTH_PATTERN = "yyyyMM";


    /**
     * yyyy/MM/dd
     */
    public static final String DEFAULT_PATTERN_WITH_SLASH = "yyyy/MM/dd";

    /**
     * yyyy年MM月dd日
     */
    public static final String DEFAULT_CHINESE_PATTERN = "yyyy年MM月dd日";
    /**
     * 格式 ：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATEFORMAT_STR_001 = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式 ：yyyy-MM-dd
     */
    public static final String DATEFORMAT_STR_002 = "yyyy-MM-dd";
    /**
     * 格式 ：MM-dd
     */
    public static final String DATEFORMAT_STR_003 = "MM-dd";
    /**
     * 格式 ：HH:mm:ss
     */
    public static final String DATEFORMAT_STR_004 = "HH:mm:ss";
    /**
     * 格式 ：yyyy-MM
     */
    public static final String DATEFORMAT_STR_005 = "yyyy-MM";

    /**
     * 格式 ：yyyyMMddHHmmss
     */
    public static final String DATEFORMAT_STR_011 = "yyyyMMddHHmmss";
    /**
     * 格式 ：yyyyMMdd
     */
    public static final String DATEFORMAT_STR_012 = "yyyyMMdd";

    /**
     * 格式 ：yyyy年MM月dd日 HH时mm分ss秒
     */
    public static final String DATEFORMAT_STR_021 = "yyyy年MM月dd日 HH时mm分ss秒";
    /**
     * 格式 ：yyyy年MM月dd日
     */
    public static final String DATEFORMAT_STR_022 = "yyyy年MM月dd日";
    /**
     * 格式 ：MM月dd日 hh:mm
     */
    public static final String DATEFORMAT_STR_023 = "MM月dd日 hh:mm";
    public static final String DATEFORMAT_STR_024 = "MM月dd日 HH:mm:ss";

    public static final String DATE_NULL = "0000-00-00";
    public static final String DATE_TIME_NULL = "0000-00-00 00:00:00";


    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    public static final DateTimeFormatter YEAR_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter SHORT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmss");
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter LONG_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");


    /**
     * 日期格式化
     *
     * @param date    日期
     * @param formate 格式，以本类定义的格式为准，可自行添加。
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static String dateToString(Date date, String formate) {
        if (date == null) {
            return "";
        }

        return new SimpleDateFormat(formate).format(date);
    }

    /**
     * 字符串转日期
     *
     * @param dateString
     * @param format     日期格式
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static Date strToDate(String dateString, String format) {
        if (dateString == null) {
            throw new InvalidParameterException("dateString cannot be null!");
        }
        try {
            return new SimpleDateFormat(format).parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("parse error! [dateString:" + dateString + ";format:" + format + "]");
        }
    }

    /**
     * 计算两个日期之间相差的周年数，忽略时间
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getYearsBetween(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new InvalidParameterException("startDate and endDate cannot be null!");
        }
        if (startDate.after(endDate)) {
            throw new InvalidParameterException("startDate cannot be after endDate!");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);

        calendar.setTime(endDate);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DATE);

        int result = year2 - year1;
        if (month2 < month1) {
            result--;
        } else if (month2 == month1 && day2 < day1) {
            result--;
        }
        return result;
    }

    /**
     * 根据生日获取年龄
     *
     * @param birthday
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static int getAge(Date birthday) {
        return getYearsBetween(birthday, new Date()) + 1;
    }

    /**
     * 计算两个时间之间相差的天数,满一天算一天
     *
     * @param startDate
     * @param endDate
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static int getDaysDiffFloor(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new InvalidParameterException("startDate and endDate cannot be null!");
        }
        if (startDate.after(endDate)) {
            throw new InvalidParameterException("startDate cannot be after endDate!");
        }
        double days = Math.floor((endDate.getTime() - startDate.getTime()) / (double) millSecondsInOneDay);  //满一天算一天
        return Convert.strToInt(String.format("%.0f", days), 0);
    }

    /**
     * 计算两个时间之间相差的天数，不满一天按一天算
     *
     * @param startDate
     * @param endDate
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static int getDaysDiffCeil(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new InvalidParameterException("startDate and endDate cannot be null!");
        }
        if (startDate.after(endDate)) {
            throw new InvalidParameterException("startDate cannot be after endDate!");
        }
        double days = Math.ceil((endDate.getTime() - startDate.getTime()) / (double) millSecondsInOneDay);  //不满一天按一天算
        return Convert.strToInt(String.format("%.0f", days), 0);
    }


    /**
     * 计算两个时间之间相差的分钟数,满一分钟算一分钟
     *
     * @param startDate
     * @param endDate
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static long getMinutesDiffFloor(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new InvalidParameterException("startDate and endDate cannot be null!");
        }
        if (startDate.after(endDate)) {
            throw new InvalidParameterException("startDate cannot be after endDate!");
        }
        double days = Math.floor((endDate.getTime() - startDate.getTime()) / (double) millSecondsInOneMinute);
        return Convert.strToLong(String.format("%.0f", days), 0);
    }

    /**
     * 日期增加
     *
     * @param date         日期
     * @param calendarType 增加类型，如：Calendar.YEAR，Calendar.DAY_OF_YEAR，Calendar.MONTH
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static Date add(Date date, int calendarType, int calendarValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarType, calendarValue);
        return calendar.getTime();
    }

    /**
     * 在日期上添加多几天并返回日期.
     *
     * @param date
     * @param days
     * @return
     */
    public static Date add(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);

        return cal.getTime();
    }


    /**
     * 日期增加n月
     *
     * @param date  日期
     * @param month 月数
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static Date addMonth(Date date, int month) {
        return add(date, Calendar.MONTH, month);
    }


    /**
     * 日期减少n年
     *
     * @param date 日期
     * @param year 年数
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static Date minusYear(Date date, int year) {
        return add(date, Calendar.YEAR, -year);
    }

    /**
     * 日期减少n月
     *
     * @param date  日期
     * @param month 月数
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static Date minusMonth(Date date, int month) {
        return add(date, Calendar.MONTH, -month);
    }

    /**
     * 日期减少n天
     *
     * @param date 日期
     * @param day  天数
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static Date minusDay(Date date, int day) {
        return add(date, Calendar.DAY_OF_YEAR, -day);
    }

    /**
     * 获取日
     *
     * @param time
     * @return
     */
    public static int getHour(Date time) {
        if (time == null) {
            throw new InvalidParameterException("time cannot be null!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 获取分钟
     *
     * @param time
     * @return
     */
    public static int getMini(Date time) {
        if (time == null) {
            throw new InvalidParameterException("time cannot be null!");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        return calendar.get(Calendar.MINUTE);
    }


    /**
     * 判断date1是否在date2之后       如:“2015-12-06 12：05：15”.after("2015-12-06 12:17:16") = true
     *
     * @return
     * @author liudong
     * @createDate 2015年12月24日
     */
    public static boolean isDatetimeAfter(Date date1, Date date2) {
        return date1.after(date2);
    }

    /**
     * 判断time1是否在time2之后，忽略日期部分
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean isTimeAfter(Date time1, Date time2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(time1);
        calendar1.set(1900, 1, 1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(time2);
        calendar2.set(1900, 1, 1);
        return calendar1.after(calendar2);
    }

    /**
     * 判断time1是否在time2之前，忽略日期部分
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean isTimeBefore(Date time1, Date time2) {
        return isTimeAfter(time2, time1);
    }


    /**
     * 判断是否过期
     *
     * @param validTime 生产日期
     * @param time      有效期 单位秒
     * @return
     * @author huangyunsong
     * @createDate 2015年12月21日
     */
    public static boolean isInValidTime(Date validTime, int time) {
        long currTime = System.currentTimeMillis();
        long valid = validTime.getTime();

        return ((currTime - valid) < time * 1000);
    }

    /**
     * 获取时间的时间轴表示
     *
     * @param date
     * @return
     */
    public static String getTimeLine(Date date) {
        long now = System.currentTimeMillis();
        long da1 = date.getTime();
        String timeline = "";
        if (now > da1) {//之前
            long a = now - da1;
            if (a / 1000 == 0) {
                timeline = "刚刚";
            } else {
                long a1 = a / 1000;
                if (a1 < 60) {
                    timeline = a1 + "秒前";
                } else {
                    long b = a1 / 60;
                    if (b < 60) {
                        if (b > 30) {
                            timeline = "半小时前";
                        } else {
                            timeline = b + "分钟前";
                        }
                    } else {
                        long c = b / 60;
                        if (c < 24) {
                            timeline = c + "小时前";
                        } else {
                            long d = c / 24;
                            if (d < 30) {
                                if (d > 7) {
                                    timeline = (d / 7) + "周前";
                                } else {
                                    timeline = d + "天前";
                                }
                            } else {
                                long e = d / 30;
                                if (e < 12) {
                                    timeline = e + "月前";
                                } else {
                                    timeline = dateToString(date, "yy/MM/dd");
                                }
                            }
                        }
                    }
                }
            }
        } else {
            long a = da1 - now;
            if (a / 1000 == 0) {
                timeline = "刚刚";
            } else {


                long a1 = a / 1000;
                {
                    if (a1 < 60) {
                        timeline = a1 + "秒后";
                    } else {
                        long b = a1 / 60;

                        if (b < 60) {
                            if (b == 30) {
                                timeline = "半小时后";
                            } else {
                                timeline = b + "分钟后";
                            }
                        } else {
                            long c = b / 60;
                            if (c < 24) {
                                timeline = c + "小时后";
                            } else {
                                long d = c / 24;
                                if (d < 30) {
                                    if (d % 7 == 0) {
                                        timeline = (d / 7) + "周后";
                                    } else {
                                        timeline = d + "天后";
                                    }
                                } else {
                                    long e = d / 30;
                                    if (e < 12) {
                                        timeline = e + "月后";
                                    } else {
                                        timeline = dateToString(date, "yy/MM/dd");
                                    }
                                }

                            }

                        }
                    }
                }

            }
        }
        return timeline;
    }


    /**
     * 获取当前月份的第一天
     *
     * @return
     * @author liudong
     * @createDate 2015年12月24日
     */
    public static String getMonthFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        String s = sdf.format(calendar.getTime());
        StringBuffer str = new StringBuffer().append(s).append(" 00:00:00");

        return str.toString();
    }

    /**
     * 获取当前月份的最后一天
     *
     * @return
     * @author liudong
     * @createDate 2015年12月24日
     */
    public static String getMonthLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String s = sdf.format(calendar.getTime());
        StringBuffer str = new StringBuffer().append(s).append(" 23:59:59");

        return str.toString();
    }

    /**
     * 得到当前时间距2013-11-01 00:00:00的小时数
     *
     * @return
     */
    public static String getHours() {
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = simple.parse("2013-11-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millisecond = System.currentTimeMillis() - date.getTime();
        long temp = 1000 * 60 * 60;
        long hours = millisecond / temp;

        return Long.toString(hours);
    }

    /**
     * 返回标准格式的当前时间
     *
     * @return [yyyy-MM-dd k:mm:ss]
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    }


    /**
     * 获得当前周- 周日的日期
     *
     * @return
     * @author Songjia
     * @createDate 2016年4月27日
     */
    public static String getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        String preMonday = sdf.format(monday);
        return preMonday;
    }

    /**
     * 将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
     *
     * @param startDateStr
     * @return
     */
    public static Date strDateToStartDate(String startDateStr) {

        return DateUtil.strToDate(startDateStr + " 00:00:00");
    }

    /**
     * 将yyyy-MM-dd拼接成yyyy-MM-dd :HH:mm:ss
     *
     * @return
     */
    public static Date strDateToEndDate(String endDateStr) {

        return DateUtil.strToDate(endDateStr + " 23:59:59");
    }

    public static Date strToDate(String dateString) {
        if (null == dateString)
            return new Date();

        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }


    public static Date strToDate1(String dateString) {
        if (null == dateString)
            return new Date();

        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String simple(Date date) {
        if (null == date)
            return "";

        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 每月1-5号可申请
     *
     * @return
     * @author wangning
     * @date 2017-8-2
     */
    public static boolean isCanApply() {
        Calendar cal = Calendar.getInstance();
        int temp = cal.get(Calendar.DAY_OF_MONTH);
        if (temp >= 6) {
            return true;
        }
        return false;

    }


    /**
     * 日期格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static Date dateToDate(Date date, String format) {
        if (date == null) {
            throw new InvalidParameterException("date cannot be null!");
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);

        Date newDate = null;
        String dateStr = DateUtil.dateToString(date, format);
        try {
            newDate = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("parse error! [dateString:" + dateStr + ";format:" + format + "]");
        }

        return newDate;
    }

    /**
     * Format date as given date format.
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     */
    public static String formatStandDate(final Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 获取简单的日期
     *
     * @return
     */
    public static String getSimpleDate() {
        SimpleDateFormat time = new SimpleDateFormat(DATE_TIME_FULL_ALL);
        String now = time.format(new Date());

        return now;
    }

    /**
     * 直播活动时间区间
     *
     * @return
     */
    public static int getTimeInterval() {
        String timeStr1 = "2018-07-05 20:30:00";
        String timeStr2 = "2018-07-09 20:30:00";
        String timeStr3 = "2018-07-12 20:30:00";
        String timeStr4 = "2018-07-15 23:59:59";
        int interval = 1;
        if (strToDate(timeStr4, DATE_TIME_SHORT).getTime() < System.currentTimeMillis()) {
            interval = 5;
        } else if (strToDate(timeStr4, DATE_TIME_SHORT).getTime() > System.currentTimeMillis() && strToDate(timeStr3, DATE_TIME_SHORT).getTime() < System.currentTimeMillis()) {
            interval = 4;
        } else if (strToDate(timeStr3, DATE_TIME_SHORT).getTime() > System.currentTimeMillis() && strToDate(timeStr2, DATE_TIME_SHORT).getTime() < System.currentTimeMillis()) {
            interval = 3;
        } else if (strToDate(timeStr2, DATE_TIME_SHORT).getTime() > System.currentTimeMillis() && strToDate(timeStr1, DATE_TIME_SHORT).getTime() < System.currentTimeMillis()) {
            interval = 2;
        }
        return interval;
    }


    /**
     * 时间的前几天
     *
     * @param date
     * @param day
     * @return
     */
    public static Date getAfterDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -day);
        Date afterDate = calendar.getTime();
        return afterDate;
    }

    public static long getSpacedDay(String startTime, String endTime) {
        try {
            Date start = DateUtil.strToDate(startTime, "yyyy-MM-dd 00:00:00");
            Date end = DateUtil.strToDate(endTime, "yyyy-MM-dd 00:00:00");
            return (end.getTime() - start.getTime()) / (24 * 3600 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getSpaceDay(Date startTime, Date endTime) {
        try {
            Date start = DateUtil.dateToDate(startTime, "yyyy-MM-dd 00:00:00");
            Date end = DateUtil.dateToDate(endTime, "yyyy-MM-dd 00:00:00");
            return (end.getTime() - start.getTime()) / (24 * 3600 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static final Map<String, Integer> weeksMap = new HashMap<String, Integer>() {{
        put("周日", 7);
        put("周一", 1);
        put("周二", 2);
        put("周三", 3);
        put("周四", 4);
        put("周五", 5);
        put("周六", 6);
    }};

    public static final Map<Integer, String> weeksChinese = new HashMap<Integer, String>() {{
        put(7, "周日");
        put(1, "周一");
        put(2, "周二");
        put(3, "周三");
        put(4, "周四");
        put(5, "周五");
        put(6, "周六");
    }};


    public static Integer getIntWeek(String week) {
        if (StringUtil.isEmpty(week)) {
            return null;
        }
        return weeksMap.get(week.trim());
    }


    /**
     * Parse a date format string into a java.util.Date object
     *
     * @param dateStr Date string
     * @param dateFmt Date format to parse the string
     * @return
     * @throws ParseException If the string and the format is not match, exception will be thrown
     */
    public static Date toUtilDate(String dateStr, String dateFmt) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateFmt);
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    //原始时间
    public static final Date INIT_DATE = new Date(0l);

    public static String formatDate(final Date date) {
        if (null == date) {
            return "";
        }
        return formatDate(date, DEFAULT_PATTERN);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static Date formatDateToYYYYMMdd(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN_WITH_HYPHEN);
        try {
            date = sdf.parse(sdf.format(date));
            return date;
        } catch (ParseException e) {
            return null;
        }

    }

    public static String formatWithDateTimeShort(final Date date) {
        if (date == null) {
            return "";
        }
        return formatDate(date, DATE_TIME_SHORT);
    }


    public static String simpleDateFormat_yyyy_MM_dd_HH_mm_ss(final Object date) {
        try {
            if (date == null) {
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (date instanceof Date) {
                Date date1 = (Date) date;
                return sdf.format(date1);
            }
        } catch (Exception e) {
            logger.error("simpleDateFormat_yyyy_MM_dd_HH_mm_ss date =" + date, e);
        }

        return "";
    }

    public static String formatWithDateTimeFullAll(final Date date) {
        if (date == null) {
            return "";
        }
        return formatDate(date, DATE_TIME_FULL_ALL);
    }

    public static String formatWithDateTimeHyphen(final Date date) {
        if (date == null) {
            return "";
        }
        return formatDate(date, DEFAULT_PATTERN_WITH_HYPHEN);
    }

    public static String formatWithDateTimeFullPattern(final Date date) {
        if (date == null) {
            return "";
        }
        return formatDate(date, FULL_STANDARD_PATTERN);
    }

    public static String formatDate(final Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * Format date as given date format.
     *
     * @param timeStamp 毫秒级别时间戳
     * @return 格式化后的日期字符串
     */
    public static String formatDate(long timeStamp, String format) {
        Date date = new Date(timeStamp);
        return new SimpleDateFormat(format).format(date);
    }

    public static String formatWithDateTimeHyphenAddDays(final Date date, int days) {
        if (date == null) {
            return "";
        }
        return formatDate(addDays(date, days), DEFAULT_PATTERN_WITH_HYPHEN);
    }

    /**
     * Add specified number of days to the given date.
     *
     * @param date date
     * @param days Int number of days to add
     * @return revised date
     */
    public static Date addDays(final Date date, int days) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);

        return new Date(cal.getTime().getTime());
    }


    public static Date addHoures(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        return cal.getTime();
    }

    public static Date addMins(final Date date, int mins) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, mins);

        return new Date(cal.getTime().getTime());
    }

    public static Date addSeconds(final Date date, int seconds) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, seconds);

        return new Date(cal.getTime().getTime());
    }


    public static Date parseDate(final String str, final String parsePatterns) {
        if (StringUtils.isBlank(str) || StringUtils.isBlank(parsePatterns)) {
            return null;
        }
        if (DATE_NULL.equals(str) || DATE_TIME_NULL.equals(str)) {
            return null;
        }
        SimpleDateFormat parser = new SimpleDateFormat(parsePatterns);
        try {
            return parser.parse(str);
        } catch (Exception e) {
            logger.error("解析时间字符串异常！");
        }
        return null;
    }

    /**
     * 转换long类型到时,分,秒,毫秒的格式.
     *
     * @param time long type
     * @return
     */
    public static String convert(long time) {
        long ms = time % 1000;
        time /= 1000;

        int h = Integer.valueOf("" + (time / 3600));
        int m = Integer.valueOf("" + ((time - h * 3600) / 60));
        int s = Integer.valueOf("" + (time - h * 3600 - m * 60));

        return h + "小时(H)" + m + "分(M)" + s + "秒(S)" + ms + "毫秒(MS)";
    }

    /**
     * judge the srcDate is between startDate and endDate
     *
     * @param srcDate
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isBetweenDateRange(final Date srcDate, final Date startDate, final Date endDate) {
        if (srcDate != null && startDate != null && endDate != null) {
            return srcDate.getTime() >= startDate.getTime() && srcDate.getTime() <= endDate.getTime();
        }
        return false;
    }

    /**
     * 获取指定的时间
     *
     * @param dayOffSet
     * @param hourOffSet
     * @param minuteOffSet
     * @param secondsOffSet
     * @return
     */
    public static Date getCertainDate(int dayOffSet, int hourOffSet, int minuteOffSet, int secondsOffSet) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dayOffSet);
        calendar.set(Calendar.HOUR, hourOffSet);
        calendar.set(Calendar.MINUTE, minuteOffSet);
        calendar.set(Calendar.SECOND, secondsOffSet);
        return calendar.getTime();
    }

    public static Date getCentainOffDate(Date date, int dayOffSet) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayOffSet);
        return calendar.getTime();
    }

    public static Date getNowDate() {
        return new Date();
    }

    /**
     * get date time as "yyyyMMddhhmmss"
     *
     * @return the current date with time component
     */
    public static String getNowYearMonthDay() {
        return formatDate(new Date(), DEFAULT_PATTERN);
    }

    /**
     * get date time as "yyyyMMddhhmmss"
     *
     * @return the current date with time component
     */
    public static String getNowYearMonthDay(Date date) {
        return formatDate(date, DEFAULT_PATTERN);
    }

    public static String getDateTimeFullAll(Date date) {
        if (date == null) {
            return "";
        }
        try {
            return formatDate(date, DATE_TIME_FULL_ALL);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     *
     * @param beginDate
     * @param endDate
     * @return
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }


    /**
     * 根据单位字段比较两个时间
     *
     * @param date      时间1
     * @param otherDate 时间2
     * @param withUnit  单位字段，从Calendar field取值
     * @return 等于返回0值, 大于返回大于0的值 小于返回小于0的值
     */
    public static int compareTime(Date date, Date otherDate, int withUnit) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        Calendar otherDateCal = Calendar.getInstance();
        otherDateCal.setTime(otherDate);

        dateCal.clear(Calendar.YEAR);
        dateCal.clear(Calendar.MONTH);
        dateCal.set(Calendar.DATE, 1);
        otherDateCal.clear(Calendar.YEAR);
        otherDateCal.clear(Calendar.MONTH);
        otherDateCal.set(Calendar.DATE, 1);
        switch (withUnit) {
            case Calendar.HOUR:
                dateCal.clear(Calendar.MINUTE);
                otherDateCal.clear(Calendar.MINUTE);
            case Calendar.MINUTE:
                dateCal.clear(Calendar.SECOND);
                otherDateCal.clear(Calendar.SECOND);
            case Calendar.SECOND:
                dateCal.clear(Calendar.MILLISECOND);
                otherDateCal.clear(Calendar.MILLISECOND);
            case Calendar.MILLISECOND:
                break;
            default:
                throw new IllegalArgumentException("withUnit 单位字段 " + withUnit + " 不合法！！");
        }
        return dateCal.compareTo(otherDateCal);
    }

    /**
     * 获得当前的日期毫秒
     *
     * @return
     */
    public static long nowTimeMillis() {
        return System.currentTimeMillis();
    }


    /**
     * 计算 second 秒后的时间
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 计算 minute 分钟后的时间
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 计算 hour 小时后的时间
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date, int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }

    /**
     * 得到day的起始时间点。
     *
     * @param date
     * @return
     */
    public static Date getDayStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 得到day的终止时间点.
     *
     * @param date
     * @return
     */
    public static Date getDayEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }


    /**
     * 得到day的终止时间点.
     *
     * @param date
     * @return
     */
    public static Date getDayEndNew(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
//		calendar.add(Calendar.DAY_OF_MONTH, 1);
//		calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    /**
     * 计算 day 天后的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        return calendar.getTime();
    }

    /**
     * 得到month的终止时间点.
     *
     * @param date
     * @return
     */
    public static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar.getTime();
    }

    public static Date addYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 365 * year);
        return calendar.getTime();
    }


    /**
     * 返回java.sql.Timestamp型的SYSDATE
     *
     * @return java.sql.Timestamp型的SYSDATE
     * @history
     * @since 1.0
     */
    public static java.sql.Timestamp getSysDateTimestamp() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }


    /**
     * 日期转换为字符串 MM月dd日 hh:mm
     *
     * @param date
     * @return
     */
    public static String dateStr(Date date) {
        return dateStr(date, DATEFORMAT_STR_023);
    }


    /**
     * 日期转换为字符串 yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String dateStr2(Date date) {
        return dateStr(date, DATEFORMAT_STR_002);
    }

    /**
     * yyyy年MM月dd日HH时mm分ss秒
     *
     * @param date
     * @return
     */
    public static String dateStr5(Date date) {
        return dateStr(date, DATEFORMAT_STR_021);
    }

    /**
     * yyyyMMddHHmmss
     *
     * @param date
     * @return
     */
    public static String dateStr3(Date date) {
        return dateStr(date, DATEFORMAT_STR_011);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static String dateStr4(Date date) {
        return dateStr(date, DATEFORMAT_STR_001);

    }


    /**
     * yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String dateStr7(Date date) {
        return dateStr(date, DATEFORMAT_STR_012);
    }

    /**
     * MM-dd
     *
     * @param date
     * @return
     */
    public static String dateStr8(Date date) {
        return dateStr(date, DATEFORMAT_STR_003);
    }


    /**
     * yyyyMMdd
     *
     * @param date
     * @return
     */
    public static String dateStr9(Date date) {
        return dateStr(date, DATEFORMAT_STR_022);
    }

    /**
     * 将时间戳转换为Date
     *
     * @param times
     * @return
     */
    public static Date getDate(String times) {
        long time = Long.parseLong(times);
        return new Date(time * 1000);
    }

    public static String dateStr(String times) {
        return dateStr(getDate(times));
    }

    public static String dateStr2(String times) {
        return dateStr2(getDate(times));
    }

    public static String dateStr3(String times) {
        return dateStr3(getDate(times));
    }

    public static String dateStr4(String times) {
        return dateStr4(getDate(times));
    }

    public static String dateStr5(String times) {
        return dateStr5(getDate(times));
    }

    /**
     * 将Date转换为时间戳
     *
     * @param date
     * @return
     */
    public static long getTime(Date date) {
        return date.getTime() / 1000;
    }


    /**
     * 默认的valueOf 方法，格式化 yyyy-mm-dd HH:mm:ss
     *
     * @param str
     * @return
     */
    public static Date valueOf(String str) {
        return valueOf(str, DATEFORMAT_STR_001);
    }

    /**
     * yyyymmdd 格式化 成 yyyy-mm-dd
     *
     * @param str
     * @return
     */
    public static String formatDate(String str) {
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        String sfstr = "";
        try {
            sfstr = sf2.format(sf1.parse(str));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return sfstr;
    }

    /**
     * 自定义format格式化字符串为date
     *
     * @param str           要格式化的字符串
     * @param dateFormatStr
     * @return
     */
    public static Date valueOf(String str, String dateFormatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatStr);
        ParsePosition pos = new ParsePosition(0);
        Date strtoDate = formatter.parse(str, pos);
        return strtoDate;
    }


    /**
     * @param Begin
     * @param end   传入开始时间 和 结束时间 格式如：2012-09-07
     * @return 返回Map 获取相隔多少年 get("YEAR")及为俩个时间年只差，月 天，类推 Key ： YEAR MONTH DAY 如果开始时间 晚于 结束时间 return null；
     * @author lijie
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static Map getApartTime(String Begin, String end) {
        String[] temp = Begin.split("-");
        String[] temp2 = end.split("-");
        if (temp.length > 1 && temp2.length > 1) {
            Calendar ends = Calendar.getInstance();
            Calendar begin = Calendar.getInstance();

            begin.set(StringUtil.toInt(temp[0]), StringUtil.toInt(temp[1]), StringUtil.toInt(temp[2]));
            ends.set(StringUtil.toInt(temp2[0]), StringUtil.toInt(temp2[1]), StringUtil.toInt(temp2[2]));
            if (begin.compareTo(ends) < 0) {
                Map map = new HashMap();
                ends.add(Calendar.YEAR, -StringUtil.toInt(temp[0]));
                ends.add(Calendar.MONTH, -StringUtil.toInt(temp[1]));
                ends.add(Calendar.DATE, -StringUtil.toInt(temp[2]));
                map.put("YEAR", ends.get(Calendar.YEAR));
                map.put("MONTH", ends.get(Calendar.MONTH) + 1);
                map.put("DAY", ends.get(Calendar.DATE));
                return map;
            }
        }
        return null;
    }

    /**
     * 前/后?分钟
     *
     * @param d
     * @param minute
     * @return
     */
    public static Date rollMinute(Date d, int minute) {
        return new Date(d.getTime() + minute * 60 * 1000);
    }

    /**
     * 前/后?小时
     *
     * @param d
     * @param hour
     * @return
     */
    public static Date rollHour(Date d, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    /**
     * 前/后?天
     *
     * @param d
     * @param day
     * @return
     */
    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 前/后?月
     *
     * @param d
     * @param mon
     * @return
     */
    public static Date rollMon(Date d, int mon) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.MONTH, mon);
        return cal.getTime();
    }

    /**
     * 前/后?年
     *
     * @param d
     * @param year
     * @return
     */
    public static Date rollYear(Date d, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    public static Date rollDate(Date d, int year, int mon, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, mon);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 获取当前时间-时间戳字符串
     *
     * @return
     */
    public static String getNowTimeStr() {
        String str = Long.toString(System.currentTimeMillis() / 1000);
        return str;
    }

    /**
     * 获取当前时间-时间戳
     *
     * @return
     */
    public static int getNowTime() {
        return Integer.parseInt(StringUtil.isNull(System.currentTimeMillis() / 1000));
    }

    /**
     * 将Date转换为时间戳
     *
     * @param time
     * @return
     */
    public static String getTimeStr(Date time) {
        long date = time.getTime();
        String str = Long.toString(date / 1000);
        return str;
    }

    public static String getTimeStr(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        String str = DateUtil.getTimeStr(date);
        return str;
    }

    public static String rollMonth(Date addTime, String time_limit) {
        Date t = DateUtil.rollDate(addTime, 0, StringUtil.toInt(time_limit), 0);
        return t.getTime() / 1000 + "";
    }

    public static String rollDay(Date addTime, String time_limit_day) {
        Date t = DateUtil.rollDate(addTime, 0, 0, StringUtil.toInt(time_limit_day));
        return t.getTime() / 1000 + "";
    }

    public static Date getIntegralTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastIntegralTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getLastSecIntegralTime(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static long getTime(String format) {
        long t = 0;
        if (StringUtil.isBlank(format))
            return t;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(format);
            t = date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t;
    }

    // 获取本周日的日期
    @SuppressWarnings("unused")
    public static String getCurrentWeekday() {
        int weeks = 0;
        int mondayPlus = DateUtil.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
        Date monday = currentDate.getTime();

        //DateFormat df = DateFormat.getDateInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获得当前日期与本周日相差的天数
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    // 获得本周一的日期
    @SuppressWarnings("unused")
    public static String getMondayOFWeek() {
        int weeks = 0;
        int mondayPlus = DateUtil.getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();

        //DateFormat df = DateFormat.getDateInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }

    // 获取当前月第一天：
    public static String getFirstDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        return first;
    }

    // 获取当月最后一天
    public static String getLastDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        return last;
    }

    /**
     * 日期月份处理
     *
     * @param d     时间
     * @param month 相加的月份，正数则加，负数则减
     * @return
     */
    public static Date timeMonthManage(Date d, int month) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(d);
        rightNow.add(Calendar.MONTH, month);
        return rightNow.getTime();
    }

    /**
     * 获取指定年月的最后一天
     *
     * @param year_time  指定年
     * @param month_time 指定月
     * @return
     */
    public static Date monthLastDay(int year_time, int month_time) {
        Calendar cal = Calendar.getInstance();
        cal.set(year_time, month_time, 0, 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 获取指定年月的第一天
     *
     * @param year_time  指定年
     * @param month_time 指定月
     * @return
     */
    public static Date monthFirstDay(int year_time, int month_time) {
        Calendar cal = Calendar.getInstance();
        cal.set(year_time, month_time - 1, 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取指定时间月的第一天
     *
     * @param d 指定时间，为空代表当前时间
     * @return
     */
    public static Date currMonthFirstDay(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null)
            cal.setTime(d);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取指定时间月的最后一天
     *
     * @param d 指定时间，为空代表当前时间
     * @return
     */
    public static Date currMonthLastDay(Date d) {
        Calendar cal = Calendar.getInstance();
        if (d != null)
            cal.setTime(d);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 获取指定时间的年
     *
     * @param date 指定时间
     * @return
     */
    public static int getTimeYear(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取指定时间的月
     *
     * @param date 指定时间
     * @return
     */
    public static int getTimeMonth(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取指定时间的天
     *
     * @param date 指定时间
     * @return
     */
    public static int getTimeDay(Date date) {
        if (date == null)
            date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    public static Date getFirstSecIntegralTime(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(d.getTime());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.DATE, 0);
        return cal.getTime();
    }

    /**
     * 指定天数 d + day天后的结束时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date rollDayEndTime(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        return cal.getTime();
    }

    /**
     * 指定天数 d + day天后的开始时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date rollDayStartTime(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 00, 00, 00);
        return cal.getTime();
    }

    /**
     * 获取指定时间天的结束时间
     *
     * @param d
     * @return
     */
    public static Date getDayEndTime(long d) {
        Date day = null;
        if (d <= 0) {
            day = new Date();
        } else {
            day = new Date(d * 1000);
        }
        Calendar cal = Calendar.getInstance();
        if (day != null) {
            cal.setTimeInMillis(day.getTime());
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        return cal.getTime();
    }


    /**
     * 获取指定时间天的结束时间
     *
     * @param day
     * @return
     */
    public static Date getDayEndTime(Date day) {
        Calendar cal = Calendar.getInstance();
        if (day != null) {
            cal.setTimeInMillis(day.getTime());
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
        cal.set(Calendar.MILLISECOND, 000);
        return cal.getTime();
    }

    /**
     * 获取指定时间天的开始时间
     *
     * @param day
     * @return
     */
    public static Date getDayStartTime(Date day) {
        Calendar cal = Calendar.getInstance();
        if (day != null) {
            cal.setTimeInMillis(day.getTime());
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取指定时间天的开始时间
     *
     * @param d
     * @return
     */
    public static Date getDayStartTime(long d) {
        Date day = null;
        if (d <= 0) {
            day = new Date();
        } else {
            day = new Date(d * 1000);
        }
        Calendar cal = Calendar.getInstance();
        if (day != null) {
            cal.setTimeInMillis(day.getTime());
        }
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 获取19位的格式时间
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDateByFullDateStr(String dateStr) {
        if (StringUtil.isBlank(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取10位的格式时间
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDateByDateStr(String dateStr) {
        if (StringUtil.isBlank(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取10位的格式时间
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDateByDateMdStr(String dateStr) {
        if (StringUtil.isBlank(dateStr)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-m-d");
            return sdf.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param date1 开始时间
     * @param date2 结束时间
     * @return
     */
    public static int daysBetween(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        DateFormat sdf = new SimpleDateFormat(DATEFORMAT_STR_012);
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(DateUtil.dateStr7(date1));
            Date d2 = sdf.parse(DateUtil.dateStr7(date2));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf((time2 - time1) / 86400000L));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的小时数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int hoursBetween(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(date1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(date2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf((time2 - time1) / 3600000L));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的分钟数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int minuteBetween(Date date1, Date date2) {

        DateFormat sdf = new SimpleDateFormat(DATEFORMAT_STR_001);
        Calendar cal = Calendar.getInstance();
        try {
            Date d1 = sdf.parse(DateUtil.dateStr4(date1));
            Date d2 = sdf.parse(DateUtil.dateStr4(date2));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            return Integer.parseInt(String.valueOf((time2 - time1) / (1000 * 60)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 字符串转时间
     *
     * @param str
     * @param dateFormatStr
     * @return
     */
    public static Date StrToDate(String str, String dateFormatStr) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date StrToDate(String str) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 比较两个时间大小
     *
     * @param date1
     * @param date2 date1>date2= ture
     * @return
     */
    public static boolean compare(Date date1, Date date2) {
        DateFormat sdf = new SimpleDateFormat(DATEFORMAT_STR_001);
        Calendar cal = Calendar.getInstance();
        boolean b = false;
        try {
            Date d1 = sdf.parse(DateUtil.dateStr4(date1));
            Date d2 = sdf.parse(DateUtil.dateStr4(date2));
            cal.setTime(d1);
            long time1 = cal.getTimeInMillis();
            cal.setTime(d2);
            long time2 = cal.getTimeInMillis();
            if (time1 > time2)
                b = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return b;
    }


    /**
     * 获取昨天 开始时间
     *
     * @return
     */
    public static Date getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        Date time = cal.getTime();
        return time;

    }


    /**
     * 获取昨天 开始时间
     *
     * @return
     */
    public static Date getSimpleYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        Date time = cal.getTime();
        return DateUtils.truncate(time, Calendar.DATE);
    }


    /**
     * 获取昨天 开始时间
     *
     * @return
     */
    public static String getSimpleYesterdayStr() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        Date time = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT_STR_002);
        return sdf.format(time);
    }


    public static final int FIRST_DAY = Calendar.MONDAY;

    public static String getWeekStartdays() {
        Calendar calendar = Calendar.getInstance();
        setToFirstDay(calendar);

        Date date = DateUtil.getDayStartTime(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    public static final int REAL_FIRST_DAY = Calendar.SUNDAY;

    public static final String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DATEONLY = "yyyy-MM-dd";

    public static String getRealWeekStartdays() {
        Calendar calendar = Calendar.getInstance();
        setToRealFirstDay(calendar);

        Date date = DateUtil.getDayStartTime(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    private static void setToRealFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != REAL_FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }

    public static String getRealWeekEnddays() {
        Calendar calendar = Calendar.getInstance();
        setToRealFirstDay(calendar);
        calendar.add(Calendar.DATE, 6);
        Date date = DateUtil.getDayEndTime(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(date);
    }

    public static String getWeekEnddays() {
        Calendar calendar = Calendar.getInstance();
        setToFirstDay(calendar);
        calendar.add(Calendar.DATE, 6);
        Date date = DateUtil.getDayEndTime(calendar.getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(date);
    }

    private static void setToFirstDay(Calendar calendar) {
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
    }


    static Date parseDate(String str, String[] strings) {
        return null;
    }


    public static int getCurrentHour() {
        Calendar cal = Calendar.getInstance();
        int a = cal.get(Calendar.HOUR_OF_DAY);
        return a;
    }

    /**
     * 获取当前日期字符串
     *
     * @return
     */
    public static String getCurrentDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//等价于now.toLocaleString()
        return sdf.format(DateUtil.getNow());
    }


    /**
     * 获取当前日期字符串
     *
     * @return
     */
    public static String getCurrentTimeStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(DateUtil.getNow());
    }

    /**
     * 获取当前简单的年月日
     *
     * @return
     */
    public static String getCurrentSimpleDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");//等价于now.toLocaleString()
        return sdf.format(DateUtil.getNow());
    }


    /**
     * 获取格式化时间
     *
     * @param enterTime
     * @return
     */
    public static String getFormateDateStr(Date enterTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        return simpleDateFormat.format(enterTime);
    }


    /**
     * 根据指定的Format转化java.util.Date到String
     *
     * @param dt   java.util.Date instance
     * @param sFmt Date format , DATE_FORMAT_DATEONLY or DATE_FORMAT_DATETIME
     * @return
     * @history
     * @since 1.0
     */
    public static String toString(Date dt, String sFmt) {
        if (dt == null || sFmt == null || "".equals(sFmt)) {
            return "";
        }
        return toString(dt, new SimpleDateFormat(sFmt));
    }

    /**
     * 利用指定SimpleDateFormat instance转换java.util.Date到String
     *
     * @param dt        java.util.Date instance
     * @param formatter SimpleDateFormat Instance
     * @return
     * @history
     * @since 1.0
     */
    private static String toString(Date dt, SimpleDateFormat formatter) {
        String sRet = null;

        try {
            sRet = formatter.format(dt).toString();
        } catch (Exception e) {
            sRet = null;
        }

        return sRet;
    }


    public static int getTimeDistance(Date beginDate, Date endDate) {
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(beginDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        long beginTime = beginCalendar.getTime().getTime();
        long endTime = endCalendar.getTime().getTime();
        int betweenDays = (int) ((endTime - beginTime) / (1000 * 60 * 60 * 24));//先算出两时间的毫秒数之差大于一天的天数

        endCalendar.add(Calendar.DAY_OF_MONTH, -betweenDays);//使endCalendar减去这些天数，将问题转换为两时间的毫秒数之差不足一天的情况
        endCalendar.add(Calendar.DAY_OF_MONTH, -1);//再使endCalendar减去1天
        if (beginCalendar.get(Calendar.DAY_OF_MONTH) == endCalendar.get(Calendar.DAY_OF_MONTH))//比较两日期的DAY_OF_MONTH是否相等
            return betweenDays + 1; //相等说明确实跨天了
        else
            return betweenDays + 0; //不相等说明确实未跨天
    }

    public static int getTimeSecond(String callTimeStr) {
        if (StringUtil.isEmpty(callTimeStr)) {
            return 0;
        }
        String[] strArr = callTimeStr.split(":");
        if (strArr.length >= 3) {
            return tool.util.NumberUtil.getInt(strArr[0]) * 3600 + tool.util.NumberUtil.getInt(strArr[1]) * 60 + NumberUtil.getInt(strArr[2]);
        } else {
            logger.info("通话时长格式缺失,callTimeStr:" + callTimeStr);
            return 0;
        }
    }

    /**
     * 计算两个时间相差多少个年
     *
     * @param start
     * @param end
     * @return
     */
    public static int yearsBetween(Date start, Date end) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(start);
        endDate.setTime(end);
        return (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR));
    }

    /**
     * 计算两个时间相差多少个月
     *
     * @param start
     * @param end
     * @return int
     */
    public static int monthsBetween(Date start, Date end) {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        startDate.setTime(start);
        endDate.setTime(end);
        int result = yearsBetween(start, end) * 12 + endDate.get(Calendar.MONTH) - startDate.get(Calendar.MONTH);
        return Math.abs(result);

    }

    public static int countTwoDayWeek(Date startDate, Date endDate) {
        Date start = (Date) startDate;
        Date end = (Date) endDate;
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        long time1 = cal.getTimeInMillis();
        cal.setTime(end);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);
        Double days = Double.parseDouble(String.valueOf(between_days));
        if ((days / 7) > 0 && (days / 7) <= 1) {
            //不满一周的按一周算
            return 1;
        } else if (days / 7 > 1) {
            int day = days.intValue();
            if (day % 7 > 0) {
                return day / 7 + 1;
            } else {
                return day / 7;
            }
        } else if ((days / 7) == 0) {
            return 0;
        } else {
            //负数返还null
            return -1;
        }
    }

    /**
     * 　　 @Description:近n年
     * 　　 @param ：
     * 　　 @author ：zc
     * 　　 @date ： 2018/5/21 19:25
     */
    public static Date getYear(int count) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, count); //年份减1
        return c.getTime();
    }

    /**
     * 近n月
     *
     * @param count
     * @return
     */

    public static Date getmonth(int count) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, count);
        return c.getTime();
    }

    /**
     * @return boolean
     * @Description 判断是否在凌晨时间段
     * @Date 19:30 2018/9/20
     * @Param [time]
     **/
    public static boolean isWeeHours(Date time) {
        boolean bool = false;
        try {
            Date startTime = new SimpleDateFormat(DATEFORMAT_STR_004).parse("23:00:00");
            Date endTime = new SimpleDateFormat(DATEFORMAT_STR_004).parse("06:00:00");
            if (time.getTime() == startTime.getTime()
                    || time.getTime() == endTime.getTime()) {
                return true;
            }

            Calendar date = Calendar.getInstance();
            date.setTime(time);

            Calendar begin = Calendar.getInstance();
            begin.setTime(startTime);

            Calendar end = Calendar.getInstance();
            end.setTime(endTime);
            if (date.after(end) && date.before(begin)) {
                bool = false;
            } else {
                bool = true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bool;
    }

    public static String getWeekDay(Integer date) {
        Date week = null;
        try {
            week = toUtilDate(date.toString(), "yyyyMMdd");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getWeekStr(week);
    }

    public static String getWeekStr(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return weeks[dayOfWeek - 1];
    }


    public static String getYYYYMMDD(String year, String monthAndDay) {
        try {
            Date date1 = getYYYYMMDDHHMM(year, monthAndDay);
            SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyyMMdd");
            return myFmt2.format(date1);
        } catch (Exception e) {
            LoggerUtils.info("getYYYYMMDD exeption ,year =" + year + ",monthAndDay=" + monthAndDay);
        }
        return null;
    }

    public static Date getYYYYMMDDHHMM(String year, String monthAndDay) {
        try {
            String time = year + "-" + monthAndDay;
            SimpleDateFormat myFmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date1 = myFmt1.parse(time);
            return date1;
        } catch (ParseException e) {
            LoggerUtils.info("getYYYYMMDDHHMM exeption ,year =" + year + ",monthAndDay=" + monthAndDay);
        }
        return null;
    }

    public static Date getYYYYMMDD(String value) {
        try {
            value = value + " 00:00:00";
            SimpleDateFormat myFmt1 = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss);
            return myFmt1.parse(value);
        } catch (Exception e) {
            LoggerUtils.info("getYYYYMMDD exeption ,value =" + value);
        }
        return null;
    }

    //返回指定星期中的天数
    public static int weekDay(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;
    }


    //返回指定小时
    public static int hour(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.HOUR_OF_DAY);
    }

    //返回指定分钟
    public static int minute(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.MINUTE);
    }



    /**
     * Exchange day format string like yyyy-MM-dd into a java.util.Date
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date toDefaultDate(String dateStr) throws ParseException {
        return toUtilDate(dateStr, DEFAULT_DATE);
    }


    public static Date getNoEndDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_SHORT);
        try {
            return sdf.parse(NO_END_DATE_FORMAT);
        } catch (ParseException e) {
            return new Date();
        }
    }

    /**
     * 返回一个Date默认最大值
     *
     * @return
     */
    public static Date getFinalDate() {
        return parseDate(FINAL_END_DATE_STR, DEFAULT_PATTERN_WITH_HYPHEN);
    }


    /**
     * 返回一个Date默认最大值
     *
     * @return
     */
    public static String getSimpleDateStr(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN_WITH_HYPHEN);
            return sdf.format(date);
        } catch (Exception e) {

        }
        return null;
    }


    /**
     * 返回一个Date默认最大值
     *
     * @return
     */
    public static String getSimpleDateDayStr(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DAY);
            return sdf.format(date);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * Add specified number of months to the date given.
     *
     * @param date   Date
     * @param months Int number of months to add
     * @return Date
     */
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * Get date one day after specified one.
     *
     * @param date1 Date 1
     * @param date2 Date 2
     * @return true if date1 after date2
     */
    public static boolean afterDay(final Date date1, final Date date2) {
        return date1.after(date2);
//        return getStartOfDate(date1).after(getStartOfDate(date2));
    }


    /**
     * Get date one day before specified one.
     *
     * @param date1 test date
     * @param date2 date when
     * @return true if date1 is before date2
     */
    public static boolean beforeDay(final Date date1, final Date date2) {
        return getStartOfDate(date1).before(getStartOfDate(date2));
    }

    /**
     * long类型的milliseconds转换成Date类型的时间
     *
     * @param time
     * @return
     */
    public static Date convert(long time, Date defaultDate) {
        try {
            Date date = new Date(time);
            return date;
        } catch (Exception e) {
            return defaultDate;
        }
    }

    /**
     * 转换long类型到时,分,秒,毫秒的格式.
     *
     * @param time long type
     * @return
     */
    public static String convertEn(long time) {
        long ms = time % 1000;
        time /= 1000;

        int h = Integer.valueOf("" + (time / 3600));
        int m = Integer.valueOf("" + ((time - h * 3600) / 60));
        int s = Integer.valueOf("" + (time - h * 3600 - m * 60));

        return h + "H" + m + "M" + s + "S" + ms + "MS";
    }

    /**
     * @param aDate
     * @return
     */
    public static String convertDateToString(String pattern, Date aDate) {
        return getDateTime(pattern, aDate);
    }

    /**
     * 毫秒转换成时间
     *
     * @param millis
     * @return
     */
    public static Date convertMillisToDate(long millis, Date defaultDate) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTimeInMillis(millis);
            return calendar.getTime();
        } catch (Exception e) {
            return defaultDate;
        }
    }

    /**
     * This method generates a string representation of a date/time in the format you specify on input
     *
     * @param aMask   the date pattern the string is in
     * @param strDate a string representation of a date
     * @return a converted Date object
     * @throws ParseException when String doesn't match the expected format
     * @see SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (logger.isDebugEnabled()) {
            logger.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
        }

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            logger.error("ParseException: " + pe);
        }

        return date;
    }

    /**
     * 此方法获取今天 12:00:00的时间，例：2019-03-30 12:00:00
     *
     * @return the current date without time component
     */
    public static Date currentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }


    /**
     * @return get system current date
     */
    public static Date getCurrentDate() {
        Long time = System.currentTimeMillis();
        return new Date(time);
    }


    /**
     * 获取dayOff天之前（后）的当前时间
     * <p>
     * if < 0 , return dayOff天前的时间
     * <p>
     * if > 0 , return dayOff后的时间
     *
     * @return
     */
    public static String getCertainDayStr(int dayOff) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dayOff);
        return formatDate(calendar.getTime(), DEFAULT_PATTERN);
    }

    /**
     * 格式化日期，返回yyyy-mm-dd
     *
     * @param date
     * @return
     */
    public static String formatShortDate(Date date) {

        return formatDate(date, DEFAULT_PATTERN_WITH_HYPHEN);
    }

    /**
     * 获取dayOff天之前（后）的当前时间
     * <p>
     * if < 0 , return dayOff天前的时间
     * <p>
     * if > 0 , return dayOff后的时间
     *
     * @return
     */
    public static Date getCertainDay(int dayOff) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, dayOff);
        return calendar.getTime();
    }

    public static String getCertainHour(int hourOff) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, hourOff);
        return formatDate(calendar.getTime(), HOUR_PATTERN);
    }

    public static String formatDateTime(Date date) {
        return formatDate(date, DATE_TIME_SHORT);
    }


    public static Date getAccuracyTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * <p><code>getAccuracyTimeWithAmount(Calendar.DAY_OF_MONTH, -5)</code>.
     *
     * @param field  the calendar field.
     * @param amount the amount of date or time to be added to the field.
     * @return
     */
    public static Date getAccuracyTimeWithAmount(int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * Format date as "MM月dd日 HH:mm".
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     */
    public static String formatFullMatchDate(final Date date) {
        return formatDate(date, FULL_MATCH_PATTERN);
    }

    /**
     * 返回MM月dd日
     *
     * @param srcDate
     * @return
     */
    public static String formatMonthAndDay(Date srcDate) {
        Calendar cal1 = GregorianCalendar.getInstance();
        cal1.setTime(srcDate);
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");

        return formatter.format(srcDate);
    }

    /**
     * 返回短日期格式
     *
     * @return [yyyy-mm-dd]
     */
    public static String formatShort(String strDate) {
        String ret = "";
        if (strDate != null && !"1900-01-01 00:00:00.0".equals(strDate) && strDate.indexOf("-") > 0) {
            ret = strDate;
            if (ret.indexOf(" ") > -1) {
                ret = ret.substring(0, ret.indexOf(" "));
            }
        }
        return ret;
    }

    /**
     * 格式化中文日期短日期格式
     *
     * @param gstrDate 输入欲格式化的日期
     * @return [yyyy年MM月dd日]
     */

    public static String formatShortDateC(Date gstrDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
        String pid = formatter.format(gstrDate);
        return pid;
    }

    /**
     * Format date as "HH:mm".
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     */
    public static String formatShortMatchDate(final Date date) {
        return formatDate(date, SHORT_MATCH_PATTERN);
    }

    /**
     * Return default datePattern (yyyy-MM-dd)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        return "yyyy-MM-dd";
    }

    public static String getDateTime(Date date) {
        return formatDate(date, DATE_TIME_SHORT);
    }

    /**
     * This method generates a string representation of a date's date/time in the format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            logger.error("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    public static String getDateTimeFull(Date date) {
        return formatDate(date, DATE_TIME_FULL);
    }


    public static String getDateTimePattern() {
        return DateUtil.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * 返回当前日
     *
     * @return [dd]
     */

    public static String getDay() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date nowc = new Date();
        String pid = formatter.format(nowc);
        return pid;
    }

    /**
     * 一天的结束时间，【注：只精确到毫秒】
     *
     * @param date
     * @return
     */
    public static Date getEndOfDate(final Date date) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return new Date(cal.getTime().getTime());
    }

    /**
     * 一天的结束时间，【注：只精确到秒】
     *
     * @param date
     * @return
     */
    public static Date getEndOfDatePrecisionSecond(final Date date) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 000);

        return new Date(cal.getTime().getTime());
    }

    /**
     * Return the end of the month based on the date passed as input parameter.
     *
     * @param date Date
     * @return Date endOfMonth
     */
    public static Date getEndOfMonth(final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        calendar.set(Calendar.DATE, 0);

        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }


    /**
     * 返回当前日
     *
     * @return [dd]
     */

    public static String getDay(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        String pid = formatter.format(date);
        return pid;
    }

    /**
     * 返回当前月份,如果date为null则返回当前月份
     *
     * @return [MM]
     */

    public static String getMonth(Date date) {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        String pid = formatter.format(date);
        return pid;
    }

    /**
     * 返回当前年份,如果date为null则返回当前年份
     *
     * @return [MM]
     */

    public static String getYear(Date date) {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        String pid = formatter.format(date);
        return pid;
    }

    /**
     * 返回标准格式的当前时间
     *
     * @return [yyyy-MM-dd k:mm:ss]
     */

    public static String getNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
        Date nowc = new Date();
        String pid = formatter.format(nowc);
        return pid;
    }

    public static String formatDateToYYYYMMddStr(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String formatDateToYYYYMMddHHmmss(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }

    }


    /**
     * 计算2个日前直接相差的天数
     *
     * @param cal1
     * @param cal2
     * @return
     */
    public static int getNumberOfDaysBetween(Calendar cal1, Calendar cal2) {
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);

        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);

        long elapsed = cal2.getTime().getTime() - cal1.getTime().getTime();
        return Long.valueOf(elapsed / DAY).intValue();
    }

    /**
     * 计算两日期之间相差的天数
     *
     * @param before
     * @param end
     * @return
     */
    public static int getNumberOfDaysBetween(Date before, Date end) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(before);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        return getNumberOfDaysBetween(cal1, cal2);
    }

    /**
     * 计算两日期之间相差的天数
     *
     * @param before
     * @param end
     * @return
     */
    public static long getNumberOfDatesBetween(Date before, Date end) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(before);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        return getNumberOfDaysBetween(cal1, cal2);
    }

    /**
     * 返回两个时间间隔的小时数
     *
     * @param before 起始时间
     * @param end    终止时间
     * @return 小时数
     */
    public static long getNumberOfHoursBetween(final Date before, final Date end) {
        long millisec = end.getTime() - before.getTime() + 1;
        return millisec / (60 * 60 * 1000);
    }

    /**
     * 返回两个时间间隔的分钟数
     *
     * @param before 起始时间
     * @param end    终止时间
     * @return 分钟数
     */
    public static long getNumberOfMinuteBetween(final Date before, final Date end) {
        long millisec = end.getTime() - before.getTime();
        return millisec / (60 * 1000);
    }

    public static int getNumberOfMonthsBetween(final Date before, final Date end) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(before);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(end);
        return (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12
                + (cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH));
    }

    public static int getNumberOfSecondsBetween(final double end, final double start) {
        if ((end == 0) || (start == 0)) {
            return -1;
        }

        return (int) (Math.abs(end - start) / SECOND);
    }


    /**
     * 返回两个时间间隔的秒数
     *
     * @param before 起始时间
     * @param end    终止时间
     * @return 分钟数
     */
    public static long getLongOfSecondsBetween(final Date before, final Date end) {
        long millisec = end.getTime() - before.getTime();
        return millisec / SECOND;
    }


    /**
     * 返回中文格式的当前日期
     *
     * @return [yyyy-mm-dd]
     */
    public static String getShortNow() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date nowc = new Date();
        String pid = formatter.format(nowc);
        return pid;
    }

    /**
     * Get start of date.
     *
     * @param date Date
     * @return Date Date
     */
    public static Date getStartOfDate(final Date date) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return new Date(cal.getTime().getTime());
    }

    /**
     * 返回当前时间24小时制式
     *
     * @return [H:mm]
     */

    public static String getTimeBykm() {
        SimpleDateFormat formatter = new SimpleDateFormat("H:mm");
        Date nowc = new Date();
        String pid = formatter.format(nowc);
        return pid;
    }

    public static Date getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    public static Date getTodayLast() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 检查日期的合法性
     *
     * @param sourceDate
     * @return
     */
    public static boolean inFormat(String sourceDate, String format) {
        if (sourceDate == null) {
            return false;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateFormat.setLenient(false);
            dateFormat.parse(sourceDate);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = GregorianCalendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = GregorianCalendar.getInstance();
        cal2.setTime(date2);

        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
                && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && (cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE)));
    }

    /**
     * Compare the two calendars whether they are in the same month.
     *
     * @param cal1 the first calendar
     * @param cal2 the second calendar
     * @return whether are in the same month
     */
    public static boolean isSameMonth(Calendar cal1, Calendar cal2) {
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
                && (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH));
    }

    /**
     * Compare the two dates whether are in the same month.
     *
     * @param date1 the first date
     * @param date2 the second date
     * @return whether are in the same month
     */
    public static boolean isSameMonth(Date date1, Date date2) {
        Calendar cal1 = GregorianCalendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = GregorianCalendar.getInstance();
        cal2.setTime(date2);
        return isSameMonth(cal1, cal2);
    }

    /**
     * get date time as "yyyyMMddhhmmss"
     *
     * @return the current date with time component
     */
    public static String now() {
        return formatDate(new Date(), FULL_PATTERN);
    }

    /**
     * change the string to date
     *
     * @param date
     * @return Date if failed return <code>null</code>
     */
    public static Date parseDate(String date) {
        return parseDate(date, DEFAULT_PATTERN, null);
    }


    /**
     * 日期转换为字符串 格式自定义
     *
     * @param date
     * @param f
     * @return
     */
    public static String dateStr(Date date, String f) {
        SimpleDateFormat format = new SimpleDateFormat(f);
        if (date != null) {
            String str = format.format(date);
            return str;
        }
        return "";
    }

    /**
     * yyyy年MM月dd日
     *
     * @param date
     * @return
     */
    public static String dateStr6(Date date) {
        return dateStr(date, DEFAULT_CHINESE_PATTERN);
    }

    /**
     * change the string to date
     *
     * @param date
     * @param df           DateFormat
     * @param defaultValue if parse failed return the default value
     * @return Date
     */
    public static Date parseDate(String date, String df, Date defaultValue) {
        if (date == null) {
            return defaultValue;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(df);

        try {
            return formatter.parse(date);
        } catch (ParseException e) {
        }

        return defaultValue;
    }

    private DateUtil() {

    }

    /**
     * parse object to date
     *
     * @param obj
     * @return
     */
    public static Date parseObjToDate(Object obj) {
        Date date = null;
        try {
            date = (Date) obj;
        } catch (Exception e) {
            date = Calendar.getInstance().getTime();
        }
        return date;
    }

    /**
     * 返回yyyyMMdd HH:mm:ss格式日期
     *
     * @return
     */
    public static Date parseDateyyyyMMddHHmmss(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        SimpleDateFormat parser = new SimpleDateFormat(FULL_STANDARD_PATTERN);
        try {
            return parser.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param s
     * @param e
     * @return boolean
     * @Title: compareDate
     * @Description: (日期比较 ， 如果s > = e 返回true 否则返回false)
     */
    public static boolean compareDate(Date s, Date e) {
        return s.getTime() >= e.getTime();
    }


    public static Date stringToDate(String date) throws ParseException {
        if (org.apache.commons.lang3.StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat fromat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fromat.parse(date);
    }

    public static Integer getCurrentYear() {
        Calendar calender = Calendar.getInstance();
        return calender.get(Calendar.YEAR);
    }


    public static Integer getCurrentYear(Date date) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        return calender.get(Calendar.YEAR);
    }

    public static Integer getCurrentDay(Date date) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        return calender.get(Calendar.DATE);
    }

    public static Integer getCurrentMonth(Date date) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        return calender.get(Calendar.MONTH) + 1;
    }


    public static Integer getCurrentMonth() {
        Calendar calender = Calendar.getInstance();
        return calender.get(Calendar.MONTH) + 1;
    }

    /**
     * 一天的结束时间，【注：只精确到毫秒】
     *
     * @param date
     * @return
     */
    public static Date getWithDrawOfDate(final Date date) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 13);
        cal.set(Calendar.MINUTE, 00);
        cal.set(Calendar.SECOND, 00);
        cal.set(Calendar.MILLISECOND, 000);

        return new Date(cal.getTime().getTime());
    }

    /**
     * 返回yyyy-MM-dd HH:mm:ss格式日期
     *
     * @return
     */
    public static Date parseDateTimeShortExpDefault(String dateStr, Date defaultDate) {
        if (dateStr == null || dateStr.length() == 0) {
            return defaultDate;
        }
        SimpleDateFormat parser = new SimpleDateFormat(DATE_TIME_SHORT);
        try {
            return parser.parse(dateStr);
        } catch (ParseException e) {
            return defaultDate;
        }
    }

    public static long getSecsEndOfDay() {
        Date nowDate = new Date();
        Date endDate = getEndOfDate(nowDate);
        long secs = (endDate.getTime() - nowDate.getTime()) / 1000;
        return secs;
    }

    public static Date getBeforeDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        Date beforeDate = calendar.getTime();
        return beforeDate;
    }


    public static Date getBeforeMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -minute);
        Date beforeDate = calendar.getTime();
        return beforeDate;
    }

    public static Date getAfterMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        Date beforeDate = calendar.getTime();
        return beforeDate;
    }


    /**
     * judge the srcDate is between startDate and endDate
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static long betweenSecond(Date startDate, Date endDate) {
        Long a = endDate.getTime() - startDate.getTime();
        return a / 1000;
    }

    /**
     * @Author：zgh
     * @Description：判断是否在某个时间内
     * @Date：15:40 2018/2/11
     */
    public static boolean checkDiscountTime(Date gmtStart, Date gmtEnd) {
        return System.currentTimeMillis() >= gmtStart.getTime() && System.currentTimeMillis() <= gmtEnd.getTime();
    }

    /**
     * @Author：zgh
     * @Description：判断两个时间相差
     * @Date：10:20 2018/2/12
     */
    public static long calculateTime(Date endTime) {
        long millisec = endTime.getTime() - System.currentTimeMillis();
        if (millisec > 0) {
            return millisec;
        }
        return 0l;
    }

    /**
     * 当前日期距账单日小于等于10天则返回下一个账单日
     *
     * @param billingDay
     * @return
     */
    public static Date calculateBillingDay(final Date billingDay) {
        int dayDiff = getNumberOfDaysBetween(new Date(), billingDay);
        if (dayDiff <= 10) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(billingDay);
            cal.add(Calendar.MONTH, 1);
            return cal.getTime();
        }
        return billingDay;
    }

    /**
     * 账单日推到下个月
     *
     * @param billingDay
     * @return
     */
    public static Date calculateNextBillingDay(final Date billingDay) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(billingDay);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 账单日推到下个月
     *
     * @param billingDay
     * @return
     */
    public static Date calculateNextBillingDayForDefault(final Date billingDay, final Integer defaultDay) {
        Calendar cal = Calendar.getInstance();
        if (billingDay != null) {
            cal.setTime(billingDay);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DAY_OF_MONTH, day);
        } else {
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DAY_OF_MONTH, defaultDay);
        }
        return cal.getTime();
    }

    /**
     * 设置月的天数
     *
     * @param billingDay
     * @return
     */
    public static Date calculateBillingDay(final Date billingDay, final Integer dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(billingDay);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        return cal.getTime();
    }

    /**
     * 时间戳计算天数差(含当前天数)
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int dateDiffrent(Date date1, Date date2) throws ParseException {
        int days = (int) ((dateDay(date2).getTime() - dateDay(date1).getTime()) / (1000 * 3600 * 24));
        return days + 1;
    }

    public static Date dateDay(Date date) throws ParseException {
        try {
            DateFormat dateFormat = new SimpleDateFormat(DEFAULT_PATTERN_WITH_HYPHEN);
            String nowdayTime = dateFormat.format(date);
            Date nowDate = dateFormat.parse(nowdayTime);
            return nowDate;
        } catch (ParseException e) {
            throw e;
        }
    }


    /**
     * 十位数字格式时间戳格式化为日期
     *
     * @param s
     * @return
     * @throws Exception
     */
    public static Date stampToTime(long s) {


        try {
            Date date = new Date(Long.valueOf(s + "000"));//将时间调整为yyyy-MM-dd HH:mm:ss时间样式
            return date;
        } catch (Exception e) {
            return new Date();
        }


    }


    /**
     * 返回当前的日期
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 返回当前时间
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * 返回当前日期时间
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * yyyy-MM-dd
     */
    public static String getCurrentDateStrNew() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * yyMMdd
     */
    public static String getCurrentShortDateStr() {
        return LocalDate.now().format(SHORT_DATE_FORMATTER);
    }

    public static String getCurrentMonthStr() {
        return LocalDate.now().format(YEAR_MONTH_FORMATTER);
    }

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }


    public static String getCurrentLongDateTimeStr() {
        return LocalDateTime.now().format(LONG_DATETIME_FORMATTER);
    }

    /**
     * yyMMddHHmmss
     */
    public static String getCurrentShortDateTimeStr() {
        return LocalDateTime.now().format(SHORT_DATETIME_FORMATTER);
    }

    /**
     * HHmmss
     */
    public static String getCurrentTimeStrNew() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    public static String getCurrentDateStr(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentDateTimeStr(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getCurrentTimeStr(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalTime parseLocalTime(String timeStr, String pattern) {
        return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDate(LocalDate date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDateTime(LocalDateTime datetime, String pattern) {
        return datetime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalTime(LocalTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    public static LocalDateTime parseLongLocalDateTime(String longDateTimeStr) {
        return LocalDateTime.parse(longDateTimeStr, LONG_DATETIME_FORMATTER);
    }

    public static LocalTime parseLocalTime(String timeStr) {
        return LocalTime.parse(timeStr, TIME_FORMATTER);
    }

    public static String formatLocalDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public static String formatLocalDateTime(LocalDateTime datetime) {
        return datetime.format(DATETIME_FORMATTER);
    }

    public static String formatLocalTime(LocalTime time) {
        return time.format(TIME_FORMATTER);
    }


    public static String sdfyyyyMMddHHmmss(final Object date) {
        try {
            if (date == null) {
                return "";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (date instanceof Date) {
                Date date1 = (Date) date;
                return sdf.format(date1);
            }
        } catch (Exception e) {
            logger.error("simpleDateFormat_yyyy_MM_dd_HH_mm_ss date =" + date, e);
        }

        return "";
    }


}



