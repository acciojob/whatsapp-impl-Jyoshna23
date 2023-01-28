package com.driver;

public class ChangeAdmin {
    private User admin;
    private User user;
    private Group group;

    public ChangeAdmin(User admin, User user, Group group) {
        this.admin = admin;
        this.user = user;
        this.group = group;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
