package me.adesh.asterisk.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.converter.PjsipAuthConverter;
import me.adesh.asterisk.dto.AuthRequest;
import me.adesh.asterisk.dto.PjSipAuthDto;
import me.adesh.asterisk.model.PjSipAuth;
import me.adesh.asterisk.service.PjsipAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PjSipAuthController {

  private final PjsipAuthService pjsipAuthService;
  private final PjsipAuthConverter pjsipAuthConverter;

  @PostMapping(path = "/auths")
  public ResponseEntity<String> create(@RequestBody AuthRequest authRequest) {
    pjsipAuthService.save(authRequest);
    return new ResponseEntity<>(authRequest.getUsername(), HttpStatus.OK);
  }

  @PutMapping(path = "/auths/{id}")
  public ResponseEntity<String> update(@PathVariable String id,
      @RequestBody AuthRequest authRequest) {
    pjsipAuthService.update(id, authRequest);
    return new ResponseEntity<>(authRequest.getUsername(), HttpStatus.OK);
  }

  @GetMapping(path = "/auths")
  public ResponseEntity<List<PjSipAuthDto>> get() {
    List<PjSipAuth> pjSipAuths = pjsipAuthService.findAll();
    return new ResponseEntity<>(pjsipAuthConverter.entityToDto(pjSipAuths), HttpStatus.OK);
  }

  @DeleteMapping(path = "/auths/{id}")
  ResponseEntity<String> deleteAor(@PathVariable String id) {
    pjsipAuthService.delete(id);
    return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
  }
}
