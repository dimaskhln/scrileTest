package com.userclass;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repo;

    @GetMapping("/users")
    public List<User> listAll() {
        return repo.findAll();
    }

    public Integer addUser(String name, String email, String avatarURI) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setAvatarURI(avatarURI);
        user.setStatus("Offline");
        repo.save(user);
        return user.getId();
    }

    public User getUserInfo(Integer id) {
        return repo.findById(id).get();
    }

    public String setStatus(Integer id, String status) {
        String result = "ID: " + id.toString();
        User user = repo.findById(id).get();
        result += "; old status: " + user.getStatus();
        user.setStatus(status);
        result += "; new status: " + user.getStatus();
        return result;
    }
}
