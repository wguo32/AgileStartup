package com.datascilab.util.common.i18n;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datascilab.util.common.exception.BusinessException;

public class I18nUtils {

    private static final Logger log = LoggerFactory.getLogger(I18nUtils.class);

    private Locale cn = Locale.CHINA;// 中文, default

    private static class I18nUtilsHolder {

        private static final I18nUtils INSTANCE = new I18nUtils();
    }

    public static I18nUtils getInstance() {
        return I18nUtilsHolder.INSTANCE;
    }

    public String getMsg(I18nRes resource, String key) {
        ResourceBundle resBundle = ResourceBundle.getBundle(resource.getBundleLocation());
        return resBundle.getString(key);
    }

    public String getMsgZh(I18nRes resource, String key) {
        String msg = "";
        try {
            msg = new String(getMsg(resource, cn, key).getBytes("ISO-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.warn(this.getClass().getCanonicalName() + " unsupported encoding from ISO-8859-1 to utf-8");
        }

        return msg;
    }

    public String getMsg(I18nRes resource, Locale locale, String key) {
        ResourceBundle resBundle = ResourceBundle.getBundle(resource.getBundleLocation(), locale);
        return resBundle.getString(key);

    }

    public String getMsg(I18nRes resource, String key, String... args) {
        ResourceBundle resBundle = ResourceBundle.getBundle(resource.getBundleLocation());
        String pattern = resBundle.getString(key);
        MessageFormat format = new MessageFormat(pattern);
        String result = format.format(args);
        return result;

    }

    public String getMsgZh(I18nRes resource, String key, String... args) {

        return getMsg(resource, cn, key, args);
    }

    public String getMsg(I18nRes resource, Locale locale, String key, String... args) {

        ResourceBundle resBundle = ResourceBundle.getBundle(resource.getBundleLocation(), locale);
        String pattern = resBundle.getString(key);
        MessageFormat format = new MessageFormat(pattern, locale);
        String result = format.format(args);
        try {
            result = new String(result.getBytes("ISO-8859-1"), "utf-8");// avoid encoding caused unreadble
        } catch (UnsupportedEncodingException e) {
            log.warn(this.getClass().getCanonicalName() + " unsupported encoding from ISO-8859-1 to utf-8");
        }

        return result;

    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String msg = I18nUtils.getInstance().getMsgZh(I18nRes.BASE, "test", "ok");
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(msg);
        msg = I18nUtils.getInstance().getMsg(I18nRes.BASE, "test", "ok");
        System.out.println(msg);
        try {
            throw new BusinessException(Locale.CHINA, "member_center.transfer.error", "1", "2");
        } catch (BusinessException e) {
            log.warn(e.toString());
        }
    }
}
