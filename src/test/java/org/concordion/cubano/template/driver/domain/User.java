package org.concordion.cubano.template.driver.domain;

public class User {
    private Role role;
    private String username;
    private String fullName;
    private String password = null;

    User(Role role, String username, String fullName) {
        this.role = role;
        this.username = username;
        this.fullName = fullName;
    }

    public Role getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        if (password == null) {
            // introduce another form of retrieving the password (?)
            return "";
        } else {
            return password;
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}