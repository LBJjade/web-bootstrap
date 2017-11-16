package com.becheer.donation.model.extension.member;

public class MemberIdCardExtension {

    private String before;

    private String after;

    private String birthdayFromIdCard;

    public String getbirthdayFromIdCard() {
        return birthdayFromIdCard;
    }

    public void setbirthdayFromIdCard(String birthdayFromIdCard) {
        this.birthdayFromIdCard = birthdayFromIdCard;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }
}
