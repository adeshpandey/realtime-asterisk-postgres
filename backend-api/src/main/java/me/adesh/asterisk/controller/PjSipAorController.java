package me.adesh.asterisk.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.converter.PjsipAorConverter;
import me.adesh.asterisk.dto.PjSipAorDto;
import me.adesh.asterisk.model.PjSipAor;
import me.adesh.asterisk.service.PjsipAorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PjSipAorController {

  private final PjsipAorService pjsipAorService;
  private final PjsipAorConverter pjsipAorConverter;

  @GetMapping(path = "/aors")
  public ResponseEntity<List<PjSipAorDto>> pjsipAors(){
    List<PjSipAor> pjSipAors = pjsipAorService.findAll();
    return new ResponseEntity<>(pjsipAorConverter.entityToDto(pjSipAors), HttpStatus.OK);
  }

  @DeleteMapping(path="/aors/{id}")
  ResponseEntity<String> deleteAor(@PathVariable String id) {
    pjsipAorService.delete(id);
    return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
  }
}
