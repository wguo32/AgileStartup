package com.datascilab.util.common;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

public class PaymentUtils {

    private Logger log = LoggerFactory.getLogger(PaymentUtils.class);

    private static final String MERCHANT_ID = "2120150707104659001";

    private static final String MERCHANT_KEY = "suiyii@1!";

    private static final String MERCHANT_URL = "http://www.suiyii.com/open/api/paymentResp/unspay.do";// 接受第三方支付
    
    public static final String PAY_REQUEST_URL = "https://www.unspay.com/unspay/page/linkbank/payRequest.do";

    public static final String TRANS_STATUS_QUERY_URL = "https://www.unspay.com/unspay/page/linkbank/netQueryTransStatus.action";

    private static class PaymentUtilsHolder {

        private static final PaymentUtils INSTANCE = new PaymentUtils();
    }

    public static PaymentUtils getInstance() {
        return PaymentUtilsHolder.INSTANCE;
    }

    public String getOrderId(String uid) {
        String orderId_time = new SimpleDateFormat("yyMMddHHmmss").format(new Date()).toString();
        String secureRandom;
        try {
            secureRandom = getSecureRandom();
        } catch (Exception e) {
            log.warn("Random generation failed for orderId");
            secureRandom = getUUID();
        }
        return "" + orderId_time + uid+"" + secureRandom;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getSecureRandom() throws NoSuchAlgorithmException {
        SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
        int rdm = prng.nextInt();
        if (rdm < 0) {
            rdm = Math.abs(rdm);
        }
        return "" + rdm;
    }

    public String calculateMacUrl(ChargeRecord cp) {

        Date date = cp.getTime();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        StringBuffer s = new StringBuffer();
        s.append("merchantId=").append(cp.getMerchantId());
        s.append("&merchantUrl=").append(cp.getMerchantUrl());
        s.append("&responseMode=").append(cp.getResponseMode());
        s.append("&orderId=").append(cp.getOrderId());
        s.append("&currencyType=").append(cp.getCurrencyType());
        s.append("&amount=").append(cp.getAmount());
        s.append("&assuredPay=").append(cp.getAssuredPay());
        s.append("&time=").append(time);
        s.append("&remark=").append(cp.getRemark());
        s.append("&merchantKey=").append(MERCHANT_KEY);
        // md5加密
        // String mac = JavaUtil.md5(s.toString());
        // new MD5().getMD5ofStr(s.toString());
        // System.out.println("_mac_=" + mac);
        return s.toString();
    }

    public String calculateMacUrl(ChargeResponse cr) {

        // String mac = cr.getMac();
        // boolean success = "0000".equals(cr.getReturnCode());
        // boolean paid = "0001".equals(cr.getReturnCode());
        // String returnCode = cr.getReturnCode();
        // String returnMessage = cr.getReturnMessage();

        StringBuffer s = new StringBuffer();
        s.append("merchantId=").append(cr.getMerchantId());
        s.append("&responseMode=").append(cr.getResponseMode());
        s.append("&orderId=").append(cr.getOrderId());
        s.append("&currencyType=").append(cr.getCurrencyType());
        s.append("&amount=").append(cr.getAmount());
        s.append("&returnCode=").append(cr.getReturnCode());
        s.append("&returnMessage=").append(cr.getReturnMessage());
        s.append("&merchantKey=").append(MERCHANT_KEY);
        // md5加密
        // String macFromClient = JavaUtil.md5(s.toString());
        // if (nowMac.equals(mac)) { // 若mac校验匹配
        //
        // } else { // 若mac校验不匹配
        // if (success || paid) {
        // success = false;
        // paid = false;
        // returnCode = "0401";
        // returnMessage = "mac值校验错误！";
        // }
        // }

        return s.toString();
    }

    public Boolean isSuccess(ChargeResponse cr) {

        return "0000".equals(cr.getReturnCode()) || "0001".equals(cr.getReturnCode());
    }

    public Boolean isMatch(ChargeResponse cr) {// mac值校验
        Boolean match = false;
        String macUrl = calculateMacUrl(cr);
        String macFromClient = JavaUtil.md5(macUrl);
        if (!Strings.isNullOrEmpty(macFromClient)) {
            match = macFromClient.equalsIgnoreCase(cr.getMac());
        }
        return match;
    }

    public Boolean isSuccessAndMatch(ChargeResponse cr) {// 交易成功mac值校验及
        return isSuccess(cr) ;//暂时不交易mac；&& isMatch(cr);
    }

    public ChargeRecord defaultChargePayment(BigDecimal amount, String bankCode, String uid) {
        ChargeRecord cp = new ChargeRecord();
        cp.setAmount(amount);
        cp.setBankCode(bankCode);
        cp.setMerchantId(MERCHANT_ID);// must
        cp.setMerchantUrl(MERCHANT_URL);// must
        cp.setResponseMode(ResponseMode.BOTH.getCode());// must
        cp.setOrderId(getOrderId(uid));// must
        cp.setCurrencyType("CNY");// hard-coded, must
        cp.setAssuredPay(false);// must
        cp.setTime(new Date());// must
        cp.setRemark("uid" + uid);// must
        cp.setMerchantKey(MERCHANT_KEY);// must
        String macUrl = calculateMacUrl(cp);
        String mac = JavaUtil.md5(macUrl).toUpperCase();
        cp.setMac(mac);
        cp.setMacUrl(macUrl);
        // default
        cp.setUid(uid);
        cp.setVersion("3.0.0");
        cp.setB2b(false);
        cp.setCommodity("CHARGE");
        cp.setOrderUrl("http://www.suiyii.com");
        cp.setCardAssured(0);// false
        return cp;

    }

    public static void main(String[] args) {
        System.out.println(PaymentUtils.getInstance().getOrderId(""));
    }
}
