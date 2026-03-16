package com.ericxie.travel_risk_api.model;

public enum RiskLevel {
    LEVEL_1_NORMAL,
    LEVEL_2_CAUTION,
    LEVEL_3_RECONSIDER,
    LEVEL_4_DO_NOT_TRAVEL;

    public static RiskLevel fromLevel(int level) {
        switch (level) {
            case 1: return LEVEL_1_NORMAL;
            case 2: return LEVEL_2_CAUTION;
            case 3: return LEVEL_3_RECONSIDER;
            case 4: return LEVEL_4_DO_NOT_TRAVEL;
        }
        return LEVEL_1_NORMAL;
    }
}