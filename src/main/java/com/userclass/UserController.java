package com.userclass;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    // Запрос информации о пользователе
    @RequestMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) {
        return service.getUserInfo(id);
    }

    // Изменение статуса пользователя
    @RequestMapping(path = "/setUserStatus/{id}/{status}", method = RequestMethod.GET)
    public String setUserStatus(@PathVariable Integer id, @PathVariable String status) {
        return service.setStatus(id, status);
    }

    //Добавление пользователя
    @RequestMapping(path = "/addUser/{name}/{email}/**", method = RequestMethod.GET)
    public Integer addUser(@PathVariable String name, @PathVariable String email, HttpServletRequest request) {
        return service.addUser(name, email, request.getRequestURI().split(request.getContextPath() + "/addUser/"+name+"/"+email+"/")[1]);
    }

    //Загрузка аватара пользователя
    @PostMapping("/uploadAvatar")
    public String UploadAvatar(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file";
        }
        try {
            byte[] bytes = file.getBytes();
            String FOLDER = "C://";
            Path path = Paths.get(FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            return "Avatar URI: " + path;
        } catch (IOException e) {
            return e.getMessage();
        }
    }
}
