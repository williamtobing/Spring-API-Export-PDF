package com.example.numbersix.controller;

import com.example.numbersix.entity.User;
import com.example.numbersix.export.ExportPDF;
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

    @GetMapping("/test")
    public String list() {
        return "Welcome!";
    }

    @GetMapping(value = "/export", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> userPDFReport(HttpServletResponse response) throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        List<User> allUsers = restTemplate.getForObject("https://api.github.com/users?per_page=50", ArrayList.class);

        ByteArrayInputStream bis = ExportPDF.userPDFReport(allUsers);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment;filename=users.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
