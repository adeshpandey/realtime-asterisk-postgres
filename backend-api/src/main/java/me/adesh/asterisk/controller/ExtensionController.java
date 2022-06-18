package me.adesh.asterisk.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.adesh.asterisk.converter.ExtensionConverter;
import me.adesh.asterisk.dto.ExtensionDto;
import me.adesh.asterisk.dto.ExtensionRequest;
import me.adesh.asterisk.model.Extension;
import me.adesh.asterisk.service.ExtensionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExtensionController {

  private final ExtensionService extensionService;
  private final ExtensionConverter extensionConverter;

  @PostMapping(path = "/extensions")
  public ResponseEntity<ExtensionDto> create(@RequestBody ExtensionRequest endpointRequest) {
    Extension extension = extensionService.save(endpointRequest);
    ExtensionDto extensionDto = extensionConverter.entityToDto(extension);
    log.info("EXT: {}", extensionDto);
    return new ResponseEntity<>(extensionDto, HttpStatus.OK);
  }

  @PutMapping(path = "/extensions/{id}")
  public ResponseEntity<ExtensionDto> update(@PathVariable Integer id,
      @RequestBody ExtensionRequest extensionRequest) {
    Extension extension = extensionService.updateById(id, extensionRequest);
    if (extension == null) {
      return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
    ExtensionDto extensionDto = extensionConverter.entityToDto(extension);

    return new ResponseEntity<>(extensionDto, HttpStatus.OK);
  }

  @GetMapping(path = "/extensions")
  public ResponseEntity<List<ExtensionDto>> get() {
    List<Extension> extensions = extensionService.findAll();
    return new ResponseEntity<>(extensionConverter.entityToDto(extensions), HttpStatus.OK);
  }

  @DeleteMapping(path = "/extensions/{id}")
  ResponseEntity<String> delete(@PathVariable Integer id) {
    extensionService.deleteById(id);
    return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
  }
}
