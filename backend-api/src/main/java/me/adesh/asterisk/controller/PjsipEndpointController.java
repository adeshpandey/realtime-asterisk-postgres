package me.adesh.asterisk.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.converter.PjsipEndpointConverter;
import me.adesh.asterisk.dto.EndpointRequest;
import me.adesh.asterisk.dto.PjsipEndpointDto;
import me.adesh.asterisk.model.PjSipEndpoint;
import me.adesh.asterisk.service.PjsipEndpointService;
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
public class PjsipEndpointController {

  private final PjsipEndpointService pjsipEndpointService;
  private final PjsipEndpointConverter pjsipEndpointConverter;

  @PostMapping(path = "/endpoints")
  public ResponseEntity<PjSipEndpoint> create(@RequestBody EndpointRequest endpointRequest) {
    PjSipEndpoint pjSipEndpoint = pjsipEndpointService.save(endpointRequest);
    return new ResponseEntity<>(pjSipEndpoint, HttpStatus.OK);
  }

  @PutMapping(path = "/endpoints/{id}")
  public ResponseEntity<PjSipEndpoint> update(@PathVariable String id,
      @RequestBody EndpointRequest endpointRequest) {
    PjSipEndpoint pjsipEndpoint = pjsipEndpointService.updateById(id, endpointRequest);
    if (pjsipEndpoint == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(pjsipEndpoint, HttpStatus.OK);
  }

  @GetMapping(path = "/endpoints")
  public ResponseEntity<List<PjsipEndpointDto>> get() {
    List<PjSipEndpoint> pjSipEndpoints = pjsipEndpointService.findAll();
    return new ResponseEntity<>(pjsipEndpointConverter.entityToDto(pjSipEndpoints), HttpStatus.OK);
  }

  @DeleteMapping(path = "/endpoints/{id}")
  ResponseEntity<String> delete(@PathVariable String id) {
    pjsipEndpointService.deleteById(id);
    return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
  }
}
