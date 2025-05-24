package com.mzyao.ytool.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum JavaType {

    // 公共类型
    STRING("String"),
    INTEGER("Integer"),
    LONG("Long"),
    DOUBLE("Double"),
    FLOAT("Float"),
    BOOLEAN("Boolean"),
    BIG_DECIMAL("BigDecimal"),
    DATE("Date"),
    LOCAL_DATE("LocalDate"),
    LOCAL_DATE_TIME("LocalDateTime"),
    BYTE_ARRAY("byte[]"),

    // 不识别时默认
    OBJECT("Object");

    private final String type;

    JavaType(String type) {
        this.type = type;
    }

    public static List<String> getTypeList() {
        return Arrays.stream(values()).map(item -> item.type).collect(Collectors.toList());
    }

    /**
     * 根据数据库字段类型映射 Java 类型（支持 MySQL 和 Oracle）
     */
    public static JavaType fromDbType(String dbType) {
        if (dbType == null) return OBJECT;

        dbType = dbType.toLowerCase();

        // 通用映射规则
        if (dbType.contains("char") || dbType.contains("text") || dbType.contains("clob")) return STRING;
        if (dbType.matches("(?i)^int$|.*int.*")) return INTEGER;
        if (dbType.contains("bigint")) return LONG;
        if (dbType.contains("double")) return DOUBLE;
        if (dbType.contains("float")) return FLOAT;
        if (dbType.contains("boolean") || dbType.contains("bit")) return BOOLEAN;
        if (dbType.contains("decimal") || dbType.contains("number") || dbType.contains("numeric")) return BIG_DECIMAL;
        if (dbType.contains("date") || dbType.contains("timestamp")) return LOCAL_DATE_TIME;
        if (dbType.contains("blob") || dbType.contains("binary") || dbType.contains("raw")) return BYTE_ARRAY;

        return OBJECT;
    }
}
