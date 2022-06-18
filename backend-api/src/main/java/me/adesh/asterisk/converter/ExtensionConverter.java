package me.adesh.asterisk.converter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.adesh.asterisk.dto.ExtensionDto;
import me.adesh.asterisk.dto.PjSipAorDto;
import me.adesh.asterisk.model.Extension;
import me.adesh.asterisk.model.PjSipAor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Slf4j
public class ExtensionConverter {


  public ExtensionDto entityToDto(Extension entity) {
    ExtensionDto extensionDto = ExtensionDto.builder()
        .id(entity.getId())
        .context(entity.getContext())
        .extension(entity.getExtension())
        .priority(entity.getPriority())
        .app(entity.getApp())
        .appData(entity.getAppData())
        .build();
    return extensionDto;
  }

  public Extension dtoToEntity(ExtensionDto entity) {
    return Extension.builder().context(entity.getContext()).build();
  }

  public List<ExtensionDto> entityToDto(List<Extension> extensions) {
    return extensions.stream().map(extension -> entityToDto(extension)).collect(Collectors.toList());
  }

  public List<Extension> dtoToEntity(List<ExtensionDto> extensionDtos) {
    return extensionDtos.stream().map(extensionDto -> dtoToEntity(extensionDto)).collect(Collectors.toList());
  }
}
