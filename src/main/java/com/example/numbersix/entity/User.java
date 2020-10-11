package com.example.numbersix.entity;

public class User {

    private int id;

    private String login;

    private String node_id;

    public User(int id, String login, String node_id) {
        this.id = id;
        this.login = login;
        this.node_id = node_id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", node_id='" + node_id + '\'' +
                '}';
    }
}
