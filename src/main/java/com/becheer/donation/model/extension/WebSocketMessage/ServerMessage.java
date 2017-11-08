package com.becheer.donation.model.extension.WebSocketMessage;

public class ServerMessage {

    private String responseMessage;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ServerMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
