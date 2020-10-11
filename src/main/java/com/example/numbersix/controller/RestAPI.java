package com.example.numbersix.controller;

import com.example.numbersix.entity.User;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestAPI {

    public static void callRest() {
        RestTemplate restTemplate = new RestTemplate();
        List<User> user = restTemplate.getForObject("https://api.github.com/users?per_page=50", ArrayList.class);
        System.out.println(user);
        System.out.println(user.size());
    }

    public static void main(String[] args) {
        callRest();
    }

}
