package kz.iitu.lab7javaEE.JavaBean;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 2041275512219239990L;

    private int id;
    private String name;
    private String email;
    private String password;

    public User(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
