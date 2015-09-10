package com.datascilab.util.common;

import java.math.BigDecimal;
import java.util.Locale;

import com.datascilab.util.common.exception.BusinessException;

public class FinanceUtils {

    public static BigDecimal doubleToDecimal_ROUND_HALF_UP(Double value, int scale) {
        return new BigDecimal(value).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal toDecimal(String decimalString) throws BusinessException {
        BigDecimal bd = null;
        try {
            bd = new BigDecimal(decimalString);
            // 四舍五入
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        } catch (NumberFormatException e) {
            throw new BusinessException(Locale.CHINA, "financeutil.toDecimal.error", decimalString);
        }

        return bd;

    }

    public static String toString(BigDecimal decimal) {
        // 四舍五入
        BigDecimal bd = decimal.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.toString();
    }

    // test
    public static void main(String[] args) {
        try {
            System.out.println(FinanceUtils.toDecimal("156563.374446"));
            System.out.println(FinanceUtils.toDecimal("156563.37h4446"));
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMsg());
        }
    }
}
