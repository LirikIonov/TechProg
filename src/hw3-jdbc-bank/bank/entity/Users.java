package bank.entity;

import java.util.Objects;
import java.util.UUID;

public class Users {
    private UUID id;
    private String login;
    private String password;
    private String address;
    private String phone;

    public Users(UUID id, String login, String password, String address, String phone) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.address = address;
        this.phone = phone;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return id.equals(users.id) &&
                login.equals(users.login) &&
                password.equals(users.password) &&
                Objects.equals(address, users.address) &&
                Objects.equals(phone, users.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, address, phone);
    }
}
