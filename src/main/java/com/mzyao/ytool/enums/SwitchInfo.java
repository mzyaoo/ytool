package com.mzyao.ytool.enums;

import lombok.Getter;

@Getter
public enum SwitchInfo {

    YES("yes"), NO("no");

    private final String label;

    SwitchInfo(String label) {
        this.label = label;
    }
}
