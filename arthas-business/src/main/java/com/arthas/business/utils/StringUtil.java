package com.arthas.business.utils;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类-字符串处理
 *
 * @author xx
 * @version 2.0
 * @since 2014年1月28日
 */
public final class StringUtil extends tool.util.StringUtil {
    private static final Logger logger = Logger.getLogger(StringUtil.class);


    private static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANXSVyvH4C55YKzvTUCN0fvrpKjIC5lBzDe6QlHCeMZaMmnhJpG/O+aao0q7vwnV08nk14woZEEVHbNHCHcfP+gEIQ52kQvWg0L7DUS4JU73pXRQ6MyLREGHKT6jgo/i1SUhBaaWOGI9w5N2aBxj1DErEzI7TA1h/M3Ban6J5GZrAgMBAAECgYAHPIkquCcEK6Nz9t1cc/BJYF5AQBT0aN+qeylHbxd7Tw4puy78+8XhNhaUrun2QUBbst0Ap1VNRpOsv5ivv2UAO1wHqRS8i2kczkZQj8vcCZsRh3jX4cZru6NoBb6QTTFRS6DRh06iFm0NgBPfzl9PSc3VwGpdj9ZhMO+oTYPBwQJBAPApB74XhZG7DZVpCVD2rGmE0pAlO85+Dxr2Vle+CAgGdtw4QBq89cA/0TvqHPC0xZaYWK0N3OOlRmhO/zRZSXECQQDj7JjxrUaKTdbS7gD88qLZBbk8c07ghO0qDCpp8J2U6D9baVBOrkcz+fTh7B8LzyCo5RY8vk61v/rYqcgk1F+bAkEAvYkELUfPCGZBoCsXSSiEhXpn248nFh5yuWq0VecJ25uObtqN7Qw4PxOeg9SOJoHkdqehRGJuc9LaMDQ4QQ4+YQJAJaIaOsVWgV2K2/cKWLmjY9wLEs0jN/Uax7eMhUOCcWTLmUdRSDyEazOZWHhJRATmKpzwyATQMDhLrdySvGoIgwJBALusECkz5zT4lIujwUNO30LlO8PKPCSKiiQJk4pN60pv2AFX4s2xVdZlXsFJh6btIJ9CGrMvEmogZTIGWq1xOFs=";


    private static final String[] DUOTRIKEY = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V"};
    private static final String[] CARDINALNUM = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};


    public static final Long INVITE_START_VALUE = 60466176L;
    private static String REGEX_CHINESE = "[\u4e00-\u9fa5]";// 中文正则


    private static Pattern stringFilterRaplace = Pattern.compile("[`~!@#$%^&*()+=|;.<>?~！@#￥%……&*（）——+|【】；：。，、？️]");


    /**
     * 构造函数
     */
    private StringUtil() {

    }



    public static String toString(String separate, int... objs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objs.length; i++) {
            if (i > 0) sb.append(separate);
            sb.append(objs[i]);
        }
        return sb.toString();
    }


    public static String toStringArray(Object... list) {
        StringBuilder sb = new StringBuilder();
        int index = 0;

        for (Object o : list) {
            if (index > 0) sb.append(",");
            sb.append(o.toString());
            index++;
        }
        return sb.toString();
    }

    /**
     * 判断字符串数组是否包含某个元素
     *
     * @param arr
     * @param targetValue
     * @return
     */
    public static boolean isContainString(String[] arr, String targetValue) {
        return Arrays.asList(arr).contains(targetValue);
    }

    public static String toString(Collection list) {
        return toString(list, ",");
    }

    public static String toString(Collection list, String delim) {
        StringBuilder sb = new StringBuilder();
        int index = 0;

        for (Object o : list) {
            if (index > 0) sb.append(delim);
            sb.append(o.toString());
            index++;
        }
        return sb.toString();
    }

    public static String getRelativePath(File file, File parentFile) {
        return file.getAbsolutePath().replaceFirst("^\\Q" + parentFile.getAbsolutePath() + "\\E", "").replace("\\", "/");
    }


    public static String getRepairedFileUri(String fullpath) {
        return fullpath.replaceFirst("[\\\\/]$", "").replace("\\", "/").replace("//", "/");
    }

    ;


    public static boolean isMobile(String phone) {
        // 判断是否是手机号（国内）
        String pattern = "^1[3|4|5|7|8][0-9]{9}$";
        return phone.matches(pattern);
    }

    public static String getBirthdayFromIdNo(String idcard) {
        try {
            String birthday = idcard.substring(6, 10) + "-" + idcard.substring(10, 12) + "-" + idcard.substring(12, 14);

            return birthday;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }




    /**
     * 判断一个字符串是否含有数字
     *
     * @param content
     * @return
     */
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断一个字符串是否是数字结尾
     *
     * @param content
     * @return
     */
    public static boolean withDigitEnd(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return false;
    }

    /**
     * 通过StringBuffer来组装字符串
     *
     * @param strings
     * @return
     */
    public static String appendStrs(Object... strings) {
        StringBuffer sb = new StringBuffer();
        for (Object str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }


    /**
     * 通过StringBuffer来组装字符串
     *
     * @param strings
     * @return
     */
    public static StringBuffer appendSb(Object... strings) {
        StringBuffer sb = new StringBuffer();
        for (Object str : strings) {
            sb.append(str);
        }
        return sb;
    }


    public static String getClassName(String className) {
        if (StringUtil.isNotBlank(className) && className.contains(".")) {
            String classNames[] = className.split("\\.", className.length());
            if (classNames != null && classNames.length > 0) {
                return classNames[classNames.length - 1];
            }
        }
        return "";
    }

    /**
     * 判断这个人是否为亲属
     *
     * @param nickname
     * @return
     */
    public static boolean isFamily(String nickname) {
        if (StringUtil.isBlank(nickname)) return false;

        // xx妈妈 一般未别人的妈妈
        if (nickname.endsWith("妈妈") && nickname.length() >= 4) return false;
        String[] familyContains = {
                "爷", "父亲", "爸", "爹", "奶奶", "母亲", "妈", "娘", "舅", "叔", "伯", "妗", "姨", "婶", "姑", "侄", "甥",
                "丈夫", "老公", "妻", "老婆", "儿子", "女儿", "孙子", "孙女", "媳", "婿", "外公", "外婆",
                "哥哥", "姐姐", "妹妹", "弟弟", "大哥", "大姐", "二哥", "二姐", "二妹", "三哥", "三姐", "三妹", "幺", "老头"};
        for (String condition : familyContains) {
            if (nickname.contains(condition)) {
                return true;
            }
        }
        return false;
    }

    public static String removeLastDouHao(String sb) {
        String values = "";
        if (StringUtil.isNotBlank(sb.toString())) {
            String sbt = sb.toString();
            values = sbt.substring(0, sbt.length() - 1);
        }
        return values;
    }

    public static String removeLastDouHao(StringBuilder sb) {
        return removeLastDouHao(sb.toString());
    }


    public static String getBankData(String data) {
        if (data.contains("data")) {
            String banks[] = data.split("data");
            if (StringUtil.isNotBlank(banks[1])) {
                String bank1 = banks[1];
                if (bank1.length() > 3) {
                    bank1 = bank1.substring(3, bank1.length());
                    if (bank1.length() > 2) {
                        bank1 = bank1.substring(0, bank1.length() - 2);
                        return bank1;
                    }
                }
            }
        }
        return "";
    }


    public static String getSetDouHao(Set<String> sets) {
        StringBuilder sb = new StringBuilder();
        for (String set : sets) {
            sb.append(set).append(",");
        }
        return sb.toString();
    }



    public static String removeNumberKuoHao(String sb) {
        if (StringUtil.isNotBlank(sb)) {
            sb = sb.replaceAll("\\d+|-|\\+|\\(|\\)", "");
            return sb;
        }
        return sb;
    }


    public static String removeKuoHao(String sb) {
        if (StringUtil.isNotBlank(sb)) {
            sb = sb.replaceAll("\\+|\\(|\\)|\\[|\\]", "");
            return sb;
        }
        return sb;
    }


    public static String removeDouHao(String sb) {
        if (StringUtil.isNotBlank(sb)) {
            sb = sb.replaceAll("\\,", "");
            return sb;
        }
        return sb;
    }

    public static String removeNull(String sb) {
        if (StringUtil.isNotBlank(sb)) {
            return sb;
        }
        return "";
    }


    public static String getFootBallResult(String allRateScore) {
        String spfResult = null;
        try {
            String result[] = allRateScore.split(":");
            int first = Integer.parseInt(result[0].trim());
            int second = Integer.parseInt(result[1].trim());
            spfResult = getSpfResult(spfResult, first, second);
            return spfResult;
        } catch (NumberFormatException e) {
            logger.info("getFootBallResult exception ,allRateScore=" + allRateScore);
        }
        return spfResult;
    }

    public static String getFootBallResult(String allRateScore, int left) {
        String spfResult = null;
        try {
            String result[] = allRateScore.split(":");
            int first = Integer.parseInt(result[0].trim()) + left;
            int second = Integer.parseInt(result[1].trim());
            spfResult = getSpfResult(spfResult, first, second);
            return spfResult;
        } catch (NumberFormatException e) {
            logger.info("getFootBallResult exception ,allRateScore=" + allRateScore + ",left =" + left);
        }
        return spfResult;
    }


    private static String getSpfResult(String spfResult, int first, int second) {
        if (first > second) {
            spfResult = "3";
        } else if (first == second) {
            spfResult = "1";
        } else if (first < second) {
            spfResult = "0";
        }
        return spfResult;
    }


    public static String getFootballTotalResult(String allRateScore) {
        String spfResult = null;
        try {
            String result[] = allRateScore.split(":");
            int first = Integer.parseInt(result[0].trim());
            int second = Integer.parseInt(result[1].trim());

            int total = first + second;
            if (total >= 7) {
                return "7+";
            }
            return total + "";
        } catch (NumberFormatException e) {
            logger.info("getFootballTotalResult exception ,allRateScore=" + allRateScore);
        }

        return spfResult;
    }


    /***
     *
     * @param url
     * @return
     */
    public static Map<String, String> getUrlMap(String url) {
        Map<String, String> map = Maps.newHashMap();
        String urls[] = url.split("\\?");
        if (urls.length > 1 && StringUtil.isNotBlank(urls[1])) {
            String param = urls[1];
            String params[] = param.split("&");
            if (params != null && param.length() > 0) {
                for (String p : params) {
                    if (StringUtil.isNotBlank(p)) {
                        String ps[] = p.split("=");
                        if (ps != null && ps.length > 0) {
                            map.put(ps[0], ps[1]);
                        }
                    }
                }
            }
        }
        return map;
    }


    public static String replaceTabToBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }


    public static boolean isChinese(String chinese) {
        char c = chinese.charAt(0);
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }


    public static String removeChinese(String str) {
        // 去除中文
        Pattern pat = Pattern.compile(REGEX_CHINESE);
        Matcher mat = pat.matcher(str);
        return mat.replaceAll("");
    }


    public static String removeGang(String value) {
        try {
            return value.replaceAll("-", "");
        } catch (Exception e) {
            logger.info("removeGang exception , value=" + value);
        }
        return null;
    }

    /**
     * 玩法销售的是单关、过关、或者不销售
     */
    //仅仅销售过关
    private static String ONLY_SELL_PASS_WAY = "u-cir";
    //销售过关和单关
    private static String SELL_SINGLE_PASS_WAY = "u-dan";
    //该玩法不销售
    private static String NO_SELL_WAY = "u-kong";



    public static boolean validatePassword(String password) {
        boolean isOK = false;
        //排除数字相同的简单密码
        for (int i = 0; i < password.length() - 1; i++) {
            int p1 = password.charAt(i);
            int p2 = password.charAt(i + 1);
            if (p1 - p2 != 0) {
                isOK = true;
                break;
            }
        }
        if (!isOK) return false;
        //排除降序的简单密码
        isOK = false;
        for (int i = 0; i < password.length() - 1; i++) {
            int p1 = password.charAt(i);
            int p2 = password.charAt(i + 1);
            if (p1 - p2 != 1) {
                isOK = true;
                break;
            }
        }
        if (!isOK) return false;
        //排除升序的简单密码
        isOK = false;
        for (int i = 0; i < password.length() - 1; i++) {
            int p1 = password.charAt(i);
            int p2 = password.charAt(i + 1);
            if (p2 - p1 != 1) {
                isOK = true;
                break;
            }
        }
        if (!isOK) return false;

        String[] easyPassword = new String[]{"123123", "112233", "111222", "abcabc"
                , "aabbcc", "aaabbb"};
        //排除特定的简单密码
        for (int i = 0; i < easyPassword.length; i++) {
            if (easyPassword[i].equals(password)) {
                return false;
            }
        }
        return isOK;
    }

    /**
     * 根据星期获得数值
     *
     * @return
     */
    public static Integer getNumWeek(String week) {
        try {
            String[] weekDaysName = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
            for (int i = 0; i < weekDaysName.length; i++) {
                if (weekDaysName[i].equals(week)) {
                    return i;
                }
            }
        } catch (Exception e) {
            LoggerUtils.error("week is " + week, e);
        }
        return null;
    }


    /***
     *  将  [1,2,3,4,5,2,3] 转为 06 26 22 21 22#07 10
     * @param number
     * @return
     */
    public static String getDanGuanNumber(String number) {
        try {
            number = removeKuoHao(number);
            String numbers[] = number.split(",");
            StringBuilder sb = new StringBuilder();
            sb.append(numbers[0])
                    .append(" ")
                    .append(numbers[1])
                    .append(" ")
                    .append(numbers[2])
                    .append(" ")
                    .append(numbers[3])
                    .append(" ")
                    .append(numbers[4])
                    .append("#")
                    .append(numbers[5])
                    .append(" ")
                    .append(numbers[6]);
            return sb.toString();
        } catch (Exception e) {
            logger.info("getDanGuanNumber exception number is " + number);
        }
        return null;
    }


    /***
     *  将  [1,2,3,4,5,2,3] 转为 491
     * @param number
     * @return
     */
    public static int[] getPl3EncashNumber(String number) {
        try {
            int[] result = new int[3];
            number = removeKuoHao(number);
            String nums[] = number.split(",");
            result[0] = Integer.parseInt(nums[0]);
            result[1] = Integer.parseInt(nums[1]);
            result[2] = Integer.parseInt(nums[2]);
            return result;
        } catch (Exception e) {
            logger.info("getPlEncashNumber exception number is " + number);
        }
        return null;
    }

    /***
     *  将  [1,2,3,4,5,2,3] 转为 491
     * @param number
     * @return
     */
    public static int[] getPl5EncashNumber(String number) {
        try {
            int[] result = new int[5];
            number = removeKuoHao(number);
            String nums[] = number.split(",");
            result[0] = Integer.parseInt(nums[0]);
            result[1] = Integer.parseInt(nums[1]);
            result[2] = Integer.parseInt(nums[2]);
            result[3] = Integer.parseInt(nums[3]);
            result[4] = Integer.parseInt(nums[4]);
            return result;
        } catch (Exception e) {
            logger.info("getPlEncashNumber exception number is " + number);
        }
        return null;
    }


    /***
     *  将  [1,2,3,4,5,2,3] 转为 06 26 22 21 22#07 10
     * @param number
     * @return
     */
    public static String removeKuoHaoDouHao(String number) {
        try {
            number = removeKuoHao(number);
            number = removeDouHao(number);
            return number;
        } catch (Exception e) {
            logger.info("getPl5EncashNumber exception number is " + number);
        }
        return null;
    }


    public static String getOrderNo(String type, Long userId) {
        return StringUtil.appendStrs(type, getUniqueCode(userId), getTimeStr());
    }

    /**
     * 获取唯一编号
     *
     * @param userId
     * @return
     */
    public static String getUniqueCode(Long userId) {
        return Long.toString((userId + INVITE_START_VALUE), 36);
    }

    /**
     * 根据时间生成对应的字符串
     *
     * @return
     */
    public static String getTimeStr() {
        StringBuilder timeStr = new StringBuilder();
        timeStr.append(toBinaryByTime());
        timeStr.append(DateUtil.formatDate(new Date(), "mmssSSS"));
        logger.info("timeStr ==== " + timeStr);
        return timeStr.toString();
    }

    /**
     * 根据日期生成32为二进制字符串
     *
     * @return
     */
    public static String toBinaryByTime() {
        StringBuilder binary = new StringBuilder();
        Calendar cal = Calendar.getInstance();
        int year = (cal.get(Calendar.YEAR) - 2000) % 32;
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        binary.append(DUOTRIKEY[year]).append(DUOTRIKEY[month]).append(DUOTRIKEY[day]).append(DUOTRIKEY[hour]);
        return binary.toString();
    }


    public static Integer[] splitByCharAt(String v) {
        Integer balls[] = new Integer[v.length()];
        for (int i = 0; i < v.length(); i++) {
            balls[i] = Integer.parseInt(v.charAt(i) + "");
        }
        return balls;
    }


//    public static void main(String[] args) {
//
//        TwoTuple<Date,Date>  data  = getIssueStartTimeAndEndTime(20190123);
//        System.out.println(DateUtil.formatDateTime(data.getFirst()));
//        System.out.println(DateUtil.formatDateTime(data.getSecond()));
//        System.out.println(JSON.toJSONString(data));
//    }

    public static String getPl5DrawNumber(String drawNumber) {
        try {
            if (StringUtil.isNotBlank(drawNumber)) {
                String[] drawNumbers = drawNumber.split(",");
                return drawNumbers[0] + " " + drawNumbers[1] + " " + drawNumbers[2] + " " + drawNumbers[3] + " " + drawNumbers[4];
            }
        } catch (Exception e) {
            logger.error(" getPl5DrawNumber is excetpion " + drawNumber, e);
        }
        return "";
    }


    public static String getPl3DrawNumber(String drawNumber) {
        try {
            if (StringUtil.isNotBlank(drawNumber)) {
                String[] drawNumbers = drawNumber.split(",");
                return drawNumbers[0] + " " + drawNumbers[1] + " " + drawNumbers[2];
            }
        } catch (Exception e) {
            logger.error("getPl3DrawNumber is excetpion " + drawNumber, e);
        }
        return "";
    }


    public static String getLottoDrawNumber(String drawNumber) {
        try {
            if (StringUtil.isNotBlank(drawNumber)) {
                String[] drawNumbers = drawNumber.split("#");
                String as[] = drawNumbers[0].split(",");
                String bs[] = drawNumbers[1].split(",");
                return as[0] + " " + as[1] + " " + as[2] + " " + as[3] + " " + as[4] + " " + bs[0] + " " + bs[1];
            }
        } catch (Exception e) {
            logger.error("getLottoDrawNumber is excetpion " + drawNumber, e);
        }
        return "";
    }

    public static String getFiveRandomNum() {
        StringBuilder randomNum = new StringBuilder();
        int length = CARDINALNUM.length;
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            randomNum.append(CARDINALNUM[random.nextInt(length)]);
        }
        return randomNum.toString();
    }

    /**
     * fmai
     * 获取第三方订单号
     *
     * @param type 类型标识（固定3位）如：强风控 type="qfk"
     * @Param thirdMark 第三方标识,比如风控R,ups为U,YiBao为Y，M為魔蝎
     */
    public static String getThirdNo(String type, String thirdMark, Long userId) {
        /*if (StringUtil.isBlank(type) || type.length() != 3) {
            throw new LsdException(LsdExceptionCode.UPS_ORDERNO_BUILD_ERROR);
		}*/
        if (userId != null) {
            return StringUtil.appendStrs(type, thirdMark, getTimeStr(), getUniqueCode(userId));
        } else {
            return StringUtil.appendStrs(type, thirdMark, getTimeStr(), getFiveRandomNum());
        }
    }


    /**
     * fmai
     * 获取第三方订单号
     *
     * @param type 类型标识（固定6位）强风控为SR+4位产品编号
     * @Param thirdMark 第三方标识,比如风控R,ups为U,YiBao为Y，M為魔蝎
     */
    public static String getThirdNoBy6LengthType(String type, String thirdMark, Long userId) {
        if (userId != null) {
            return StringUtil.appendStrs(type, thirdMark, getTimeStr(), getUniqueCode(userId));
        } else {
            return StringUtil.appendStrs(type, thirdMark, getTimeStr(), getFiveRandomNum());
        }
    }

    public static String null2Str(Object str) {
        return (str != null) ? str.toString() : "";
    }

    /**
     * @param sStr
     * @return
     * @Title: UrlEncoder
     * @Description: 字符串编码
     */
    public final static String UrlEncoder(String sStr) {
        String sReturnCode = "";
        try {
            sReturnCode = URLEncoder.encode(null2Str(sStr), "utf-8");
        } catch (Exception ex) {
        }
        return sReturnCode;
    }

    /**
     * @param sStr
     * @return
     * @Title: UrlDecoder
     * @Description: 字符串解码
     */
    public static String UrlDecoder(String sStr) {
        if (isEmpty(sStr)) {
            return "";
        } else {
            String sReturnCode = sStr;
            try {
                sReturnCode = URLDecoder.decode(sStr, "utf-8");
            } catch (Exception e) {
            }
            return sReturnCode;
        }
    }

    /**
     * @param source 待处理字符串
     * @return
     * @方法描述：将字符串中的emoji符号转换为*
     * @author huyang 2017年4月6日下午12:38:04
     * @注意：本内容仅限于杭州霖梓网络科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
     */
    public static String filterEmoji(String source) {
        if (source != null) {
            Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            Matcher emojiMatcher = emoji.matcher(source);
            if (emojiMatcher.find()) {
                source = emojiMatcher.replaceAll("*");
                return source;
            }
            return source;
        }
        return source;
    }




    /**
     * 判断xlsx后缀
     */
    public static String getFirstStr(String value) {
        String result = "";
        if (StringUtil.isNotBlank(value)) {
            result = value.substring(0, 1);
        }


        return result;

    }

    /**
     * <b>功能描述:过滤通讯录中特殊字符
     * （不包含json 中会使用到的\ {} ,: 等特殊符号）
     * 并替换成""
     * </b>
     *
     * @param context 带替换字符串
     * @return java.lang.String
     * <b>修改历史:</b>(修改人,修改时间,修改原因/内容)
     * @author : guqy
     * <b>创建日期 :</b>
     * @date 2019/6/26 16:37
     */
    public static String repalaceFilter(String context) {
        // 注释
//        String contracts = "[{\"name\":\"杨一帆\",\"mobile\":\"17713949279\"},{\"name\":\"宝\",\"mobile\":\"13980448846\"},{\"name\":\"第一\",\"mobile\":\"15828492763\"},{\"name\":\"电信卡\",\"mobile\":\"18008094944\"},{\"name\":\"杨桃\",\"mobile\":\"13678100128\"},{\"name\":\"葩葩*****\u200D*️\u200D*\u200D***\u200D*️**\u200D*️*\u200D*️*\u200D*️*\u200D*️*\u200D*️*\u200D*️*\u200D*️\u200D**\u200D*\u200D**\u200D**\u200D*\u200D**\u200D*\u200D**\u200D**\u200D**\u200D*\u200D**\u200D*\u200D**\u200D*\u200D******\",\"mobile\":\"18224099403\"},{\"name\":\"何雨后\",\"mobile\":\"18030744520\"},{\"name\":\"朱莉\",\"mobile\":\"17828122085\"},{\"name\":\"老何\",\"mobile\":\"15902838976\"},{\"name\":\"第二\",\"mobile\":\"13550399220\"},{\"name\":\"丁静\",\"mobile\":\"18782759728\"},{\"name\":\"小姨\",\"mobile\":\"15881146073\"},{\"name\":\"老太\",\"mobile\":\"18244251967\"},{\"name\":\"三姨妈\",\"mobile\":\"18781964812\"},{\"name\":\"丽萍姐\",\"mobile\":\"13684063173\"},{\"name\":\"燕丽\",\"mobile\":\"13540106695\"}]";
        Matcher m = stringFilterRaplace.matcher(context);
        return m.replaceAll("");
    }

//    public static void main(String[] args) {
//        System.out.println(getFirstStr("刘举办"));
//    }
//

    public static String getStringNum(String payNO) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(payNO);
        return m.replaceAll("").trim();
    }


    //生成随机数字和字母,
    public static String getStringRandom() {

        String val = "";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < 15; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }


    public static Object removeByStatus(Object value, Integer status) {
        if (status == null || status == 0) {
            return "";
        }
        return value;
    }


    public static String getChannelName(String downLoadUrl, String name) {
        try {
            downLoadUrl = downLoadUrl + "?channelName=" + name;
        } catch (Exception e) {
            logger.error("downLoadUrl=" + downLoadUrl + ",name=" + name, e);
        }
        return downLoadUrl;
    }


    public static String removeFenHao(String port) {
        if (port.contains(":")) {
            port = port.replaceAll(":", "");
        }
        return port;
    }


    public static String removeDanYinHao(String value) {
        if (StringUtil.isBlank(value)) {
            return value;
        }
        if (value.contains("'")) {
            value = value.replaceAll("'", "");
        }
        return value;
    }


    public static boolean isDouble(String str) {
        return Pattern.compile("^[+-]?[\\d]+\\.[\\d]+$").matcher(str).find();
    }

    public static boolean isInt(String str) {
        return Pattern.compile("^[+-]?[\\d]+$").matcher(str).find();
    }

    public static boolean isNumber(String aString) {
        //      检查一个字符串是否全部是数字

        if (aString == null || aString.length() < 1) {
            return false;
        }

        Pattern numberPattern = Pattern.compile("^[\\d]+$"); //  "^[+-]?([\\d]+)|([\\d]+\\.[\\d]+)$"
        return numberPattern.matcher(aString).find();
    }


    public static String getEntity(Object o) {
        if (o == null) {
            return "";
        }
        if (o != null && o instanceof Date) {
            return DateUtil.formatDateToYYYYMMddHHmmss((Date) o);
        }
        return o.toString();
    }

/*
    public static void main(String[] args) {
        System.out.println(getUpsNo("sn0J8GUL544246210b4ov"));

    }*/

    public static String getClassMethod(String classMethod) {
        try {
            StringBuilder cm = new StringBuilder();
            if (StringUtil.isNotBlank(classMethod) && classMethod.length() > 0 && classMethod.contains(".")) {
                String classMethods[] = classMethod.split("\\.");
                if (classMethod.length() >= 2) {
                    cm.append(classMethods[classMethods.length - 2]).append(".").append(classMethods[classMethods.length - 1]);
                }
            }
            return cm.toString();
        } catch (Exception e) {
            logger.error("classMethod=" + classMethod, e);
        }
        return "";

    }

    //sn0UJ8GL544246210b4ov
    public static String getUpsNo(String repayNo) {
        try {
            int a = repayNo.indexOf("U");
            if (a > 0) {
                String result = repayNo.substring(0, a);
                return result;
            }
        } catch (Exception e) {
            logger.error("getUpsNo error ,repayNo=" + repayNo, e);
        }
        return "mqbhq0";
    }
}
