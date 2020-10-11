package com.example.numbersix.controller;

import com.example.numbersix.entity.User;
import com.example.numbersix.export.ExportPDF;
import com.example.numbersix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

//    @Autowired
//    UserRepository userRepository;

    @GetMapping("/welcome")
    public String list() {
        return "welcome";
    }

    @GetMapping(value = "/exportpdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> userPDFReport(HttpServletResponse response) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
//        List<User> allUsers = restTemplate.getForObject("https://api.github.com/users?per_page=50", ArrayList.class);
        List<User> allUsers = restTemplate.getForObject("https://api.github.com/users?per_page=50", ArrayList.class);
//        System.out.println(allUsers);

//        List<User> allUsers = userRepository.findAll();


        ByteArrayInputStream bis = ExportPDF.userPDFReport(allUsers);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment;filename=users.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
