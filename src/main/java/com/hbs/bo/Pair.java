package com.hbs.bo;

/**
 * Created by jyo on 07/07/2016.
 */
public class Pair {
    private String reviewer;
    private String reviewee;

    public Pair(String reviewer, String reviewee) {
        this.reviewer = reviewer;
        this.reviewee = reviewee;
    }

    public String getReviewee() {
        return reviewee;
    }

    public void setReviewee(String reviewee) {
        this.reviewee = reviewee;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
