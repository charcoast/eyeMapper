package br.com.augusto.example;

import br.com.augusto.annotation.JsonIgnore;
import br.com.augusto.annotation.JsonIgnoreErrors;

@JsonIgnoreErrors
public class User {

    private long userId;
    private String userName;

    @JsonIgnore
    private String userPassword;
    private String userEmail;

    private boolean teste = true;

    public User() {
    }

    public User(long userId, String userName, String userPassword, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public boolean isTeste() {
        return teste;
    }

    public void setTeste(boolean teste) {
        this.teste = teste;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
