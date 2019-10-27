package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserRestController {
    @Autowired
    private UserRepository userRepository;

    // READ endpointi
    @GetMapping("/api/users")
    public  @ResponseBody Iterable<User> index() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    // CREATE endpointi
    @PostMapping("/api/users")
    public @ResponseBody User registerSubmit(
            @RequestParam String name, @RequestParam String email, @RequestParam String surname, @RequestParam String student_no) {
        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setSurname(surname);
        u.setStudent_no(student_no);
        userRepository.save(u);

        return u;
    }

    @DeleteMapping("/api/users/{id}/delete")
    public @ResponseBody Iterable<User> deleteUser(@PathVariable("id") Integer id){
        userRepository.deleteById(id);
        Iterable<User> users = userRepository.findAll();
        return users;
    }



    @PutMapping("/api/users/{id}/update")
    public @ResponseBody User updateUser(@PathVariable("id") Integer id, @RequestParam String student_no,@RequestParam String name,@RequestParam String surname,@RequestParam String email){
        User u = userRepository.findById(id).get();
        u.setStudent_no(student_no);
        u.setName(name);
        u.setSurname(surname);
        u.setEmail(email);
        userRepository.save(u);

        return u;
    }

}