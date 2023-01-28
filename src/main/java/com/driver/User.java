package com.driver;

public class User {
    private String name;
    private String mobile;

    public User(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setMobile(){
        this.mobile = mobile;
    }

    public String getMobile(){
        return mobile;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        User user = (User)obj;

        return this.getName().equals(user.getName()) && this.getMobile().equals(user.getMobile());
    }
}
