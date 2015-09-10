package com.datascilab.util.common.exception;

import java.util.Locale;

import com.datascilab.util.common.i18n.I18nRes;
import com.datascilab.util.common.i18n.I18nUtils;

public class BusinessException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 2107050421173501280L;

    private String key;

    private String msg;

    public BusinessException() {
        super();
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BusinessException(String key, String... args) {
        this.key = key;
        msg = I18nUtils.getInstance().getMsg(I18nRes.EXCEPTION, Locale.US, key, args);
    }

    public BusinessException(Locale locale, String key, String... args) {
        this.key = key;
        msg = I18nUtils.getInstance().getMsg(I18nRes.EXCEPTION, locale, key, args);
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "BusinessException [key=" + key + ", msg=" + msg + "]";
    }

    public String getMsg() {
        return msg;
    }

}
