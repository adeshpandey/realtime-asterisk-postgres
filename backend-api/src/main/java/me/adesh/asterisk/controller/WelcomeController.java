package me.adesh.asterisk.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class WelcomeController {

  @GetMapping("/welcome")
  public String welcome() {
    return "Hi";
  }
}
