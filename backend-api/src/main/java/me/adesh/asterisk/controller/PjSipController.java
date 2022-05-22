package me.adesh.asterisk.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PjSipController {

  @GetMapping("/pjsip")
  public List<String> getUsers(){
    return List.of("adesh", "faheem");
  }
}
