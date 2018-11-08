package com.dido.accessingdatamysql;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo") // This means URL's start with /demo (after Application path)
public class MainController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping(path = "/add") // Map ONLY GET Requests
  public @ResponseBody
  String addNewUser(@RequestParam String name
      , @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved " + name;
  }

  @GetMapping(path = "/all")
  public @ResponseBody
  Iterable<User> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }

  @GetMapping(path = "/delete/{id}")
  public @ResponseBody
  String deleteUser(@PathVariable(value = "id") Integer id) {
    User user = userRepository.findById(id).orElse(null);
    if (user != null) {
      userRepository.deleteById(id);
      return "deleted User: " + user.getName();
    } else {
      return "There is no user with the id: " + id;
    }

  }
}
