package com.mzyao.ytool.enums;

import lombok.Getter;

@Getter
public enum DalInfo {

    Mybatis("MyBatis"), MybatisPlus("MyBatisPlus"), JavaPersistenceApi("jpa");

    private final String label;

    DalInfo(String label) {
        this.label = label;
    }

}
