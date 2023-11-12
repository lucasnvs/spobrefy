package com.spobrefy.model;

import com.spobrefy.dao.UpgradeRequestsDAO;
import com.spobrefy.dao.UsersDAO;
import com.spobrefy.model.users.User;
import com.spobrefy.shared.IAbleToSave;

public class UpgradeRequest implements IAbleToSave {

    private int idUpgradeRequest;
    private User sender;
    private String senderCPF;
    private String senderBirthDate;
    private UpgradeType type;
    private Boolean isAnswered;
    private Boolean answer;

    public UpgradeRequest(int idUpgrade, int idSender, UpgradeType type, Boolean isAnswered, Boolean answer,String senderCPF, String senderBirthDate) {
        this.sender = UsersDAO.getInstance().findById(idSender);
        this.type = type;
        this.isAnswered = isAnswered;
        this.answer = answer;
        this.senderCPF = senderCPF;
        this.senderBirthDate = senderBirthDate;
        this.idUpgradeRequest = idUpgrade;
    }
    public UpgradeRequest(User sender, UpgradeType type, String senderCPF, String senderBirthDate) {
        this.sender = sender;
        this.type = type;
        this.isAnswered = false;
        this.answer = false;
        this.senderCPF = senderCPF;
        this.senderBirthDate = senderBirthDate;
        this.idUpgradeRequest = UpgradeRequestsDAO.getInstance().getLastId() + 1;
    }

    @Override
    public String toCsvString() {
        String[] partes = { Integer.toString(getId()), String.valueOf(sender.getId()), String.valueOf(getType()), String.valueOf(getIsAnswered()), String.valueOf(getAnswer()), getSenderCPF(), getSenderBirthDate()};

        return String.join(";",partes);
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

    public String getSenderCPF() {
        return senderCPF;
    }
    public String getSenderBirthDate() {
        return senderBirthDate;
    }

    @Override
    public String toString() {
        return "UpgradeRequest{" +
                "idUpgradeRequest=" + idUpgradeRequest +
                ", sender=" + sender.getId() +
                ", senderCPF='" + senderCPF + '\'' +
                ", senderBirthDate='" + senderBirthDate + '\'' +
                ", type=" + type +
                ", isAnswered=" + isAnswered +
                ", answer=" + answer +
                '}';
    }
}
