package com.spobrefy.model;

import com.spobrefy.model.users.User;

public class UpgradeRequest {

    private int idUpgradeRequest;
    private static int count = 0;
    private User sender;
    private UpgradeType type;
    private Boolean isAnswered;
    private Boolean answer;

    public UpgradeRequest(User sender, UpgradeType type) {
        this.sender = sender;
        this.type = type;
        this.isAnswered = false;
        this.answer = false;
        this.idUpgradeRequest = count++;
    }

    public int getId() {
        return idUpgradeRequest;
    }

    public void setId(int idUpgradeRequest) {
        this.idUpgradeRequest = idUpgradeRequest;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public UpgradeType getType() {
        return type;
    }

    public void setType(UpgradeType type) {
        this.type = type;
    }

    public Boolean getIsAnswered() {
        return isAnswered;
    }

    public void setIsAnswered(Boolean answered) {
        isAnswered = answered;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }
}
