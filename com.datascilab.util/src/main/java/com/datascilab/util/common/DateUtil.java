package com.datascilab.util.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datascilab.util.common.i18n.I18nRes;
import com.datascilab.util.common.i18n.I18nUtils;
import com.google.common.base.Strings;

public class DateUtil {

    private Logger log = LoggerFactory.getLogger(DateUtil.class);

    private static class DateUtilHolder {

        private static final DateUtil INSTANCE = new DateUtil();
    }

    public static DateUtil getInstance() {
        return DateUtilHolder.INSTANCE;
    }

    public Date toDate_noSec(String dateString) {

        return toDate(dateString, "yyyy-MM-dd HH:mm");

    }

    public Date toDate_timeStamp(String dateString) {

        return toDate(dateString, "yyyy-MM-dd HH:mm:ss");

    }

    public Date toDate(String dateString, String pattern) {
        Date date = null;
        if (!Strings.isNullOrEmpty(dateString)) {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            try {
                date = sdf.parse(dateString);
            } catch (ParseException e) {
                log.warn(I18nUtils.getInstance().getMsgZh(I18nRes.EXCEPTION, "dateutil.parse.error"));
            }
        }
        return date;

    }

    public static void main(String[] args) {
        // wrong pattern
        Date date = DateUtil.getInstance().toDate("2014-03-03 18:19", "yyyy-MM-dd HH:mm:ss");
        System.out.println(date);
        // right pattern
        Date date1 = DateUtil.getInstance().toDate("2014-03-03 18:09:12", "yyyy-MM-dd HH:mm");
        System.out.println(date1);
    }

}
