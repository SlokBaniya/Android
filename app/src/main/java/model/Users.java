package model;

public class Users {
    private String fullname, contact, address, username, password;

    public Users(String fullname, String address, String contact, String username, String password) {
        this.fullname = fullname;
        this.address = address;
        this.contact = contact;
        this.username = username;
        this.password = password;
    }
    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
