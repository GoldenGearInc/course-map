package com.se34.coursemap.entity;

import java.util.ArrayList;

public class RecommendationResult {
    private ArrayList<RecommendationInfo> recommendationInfoArrayList;

    public RecommendationResult(){
        recommendationInfoArrayList = new ArrayList<>();
    }
    public RecommendationResult(ArrayList<RecommendationInfo> recommendationInfoArrayList) {
        this.recommendationInfoArrayList = recommendationInfoArrayList;
    }

    public ArrayList<RecommendationInfo> getRecommendationInfoArrayList() {
        return recommendationInfoArrayList;
    }
}
