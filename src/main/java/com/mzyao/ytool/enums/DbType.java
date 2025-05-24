package com.mzyao.ytool.enums;

import lombok.Getter;

@Getter
public enum DbType {

    MYSQL("mysql"), ORACLE("oracle"), SQLSERVER("sqlserver");

    private final String label;

    DbType(String label) {
        this.label = label;
    }
}
