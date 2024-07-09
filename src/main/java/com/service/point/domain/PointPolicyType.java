package com.service.point.domain;

public enum PointPolicyType {
    COMMON(0,"일반"),ROYAL(1,"로얄"),GOLD(2,"골드"),PLATINUM(3,"플래티넘"),REVIEW(4,"리뷰"),MEMBERSHIP(5,"회원가입"),REFUND(6,"환불");

    private final int code;
    private final String value;

    PointPolicyType(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static PointPolicyType fromCode(int code) {
        for (PointPolicyType type : PointPolicyType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid DiscountType code: " + code);
    }

    public static PointPolicyType fromValue(String value) {
        for (PointPolicyType type : PointPolicyType.values()) {
            if (type.value.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid DiscountType value: " + value);
    }
}
