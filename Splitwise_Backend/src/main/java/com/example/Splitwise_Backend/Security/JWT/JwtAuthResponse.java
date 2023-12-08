package com.example.Splitwise_Backend.Security.JWT;

public class JwtAuthResponse {
    public String accessToken;
    public String message;
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public JwtAuthResponse(String accessToken, String message) {
        super();
        this.accessToken = accessToken;
        this.message = message;
    }
    public JwtAuthResponse() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "JwtAuthResponse [accessToken=" + accessToken + ", message=" + message + "]";
    }
}
