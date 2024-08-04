package co.edu.uptcSoft.model;

import com.google.gson.annotations.Expose;

public class Administrator {
    @Expose private String email ;
    @Expose private String password;

    public  Administrator() {
    }

    public Administrator(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
