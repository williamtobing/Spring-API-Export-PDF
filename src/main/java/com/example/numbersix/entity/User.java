package com.example.numbersix.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private int id;

    private String login;

    private String node_id;

//    public User(@JsonProperty("id") int id,
//                @JsonProperty("lofin") String login,
//                @JsonProperty("node_id") String node_id) {
    public User(int id, String login, String node_id) {
        this.id = id;
        this.login = login;
        this.node_id = node_id;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setNode_id(String node_id) {
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
