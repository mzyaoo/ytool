package com.mzyao.ytool.utils;

import java.util.HashMap;
import java.util.Map;

public class OracleToJavaType {

    private static final Map<String, String> TYPE_MAP = new HashMap<>();

    static {
        // 数值类型
        TYPE_MAP.put("number", "BigDecimal");
        TYPE_MAP.put("binary_float", "Float");
        TYPE_MAP.put("binary_double", "Double");

        // 字符类型
        TYPE_MAP.put("char", "String");
        TYPE_MAP.put("nchar", "String");
        TYPE_MAP.put("varchar2", "String");
        TYPE_MAP.put("nvarchar2", "String");
        TYPE_MAP.put("clob", "String");
        TYPE_MAP.put("nclob", "String");

        // 日期时间类型
        TYPE_MAP.put("date", "LocalDateTime"); // 可自行改为 LocalDate
        TYPE_MAP.put("timestamp", "LocalDateTime");
        TYPE_MAP.put("timestamp with time zone", "LocalDateTime");
        TYPE_MAP.put("timestamp with local time zone", "LocalDateTime");

        // 二进制
        TYPE_MAP.put("blob", "byte[]");
        TYPE_MAP.put("raw", "byte[]");
        TYPE_MAP.put("long raw", "byte[]");

        // 其他
        TYPE_MAP.put("long", "String");
        TYPE_MAP.put("xmltype", "String");
    }

    public static String convert(String oracleType, Integer precision, Integer scale) {
        if (oracleType == null) {
            return "Object";
        }

        oracleType = oracleType.toLowerCase();

        // 特殊处理 number 类型
        if ("number".equals(oracleType)) {
            if (scale != null && scale > 0) {
                return "BigDecimal";
            }
            if (precision == null) {
                return "BigDecimal";
            }
            if (precision <= 9) {
                return "Integer";
            } else if (precision <= 18) {
                return "Long";
            } else {
                return "BigDecimal";
            }
        }

        return TYPE_MAP.getOrDefault(oracleType, "Object");
    }
}
