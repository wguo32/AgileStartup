package com.datascilab.util.common.i18n;

public enum I18nRes {
    BASE("base"), // 基础信息国际化
    EXCEPTION("exception"), // 异常信息国际化
    ACTION_USER("action_user"), // 用户行为日志
    ACTION_ADMIN("action_admin");// 管理员行为日志

    String fileName;

    I18nRes(String fileName) {
        this.fileName = fileName;
    }

    String getFileName() {
        return fileName;
    }

    String getBundleLocation() {
        return PATH + "." + getFileName();
    }

    private static final String PATH = "com.jinsec.jbank.common.i18n.resource";
}
