package me.adesh.asterisk.converter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.dto.PjSipAorDto;
import me.adesh.asterisk.model.PjSipAor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PjsipAorConverter {


  public PjSipAorDto entityToDto(PjSipAor entity) {
    return PjSipAorDto.builder()
        .contact(entity.getContact())
        .removeExisting(entity.getRemoveExisting())
        .id(entity.getId())
        .build();
  }

  public PjSipAor dtoToEntity(PjSipAorDto entity) {
    return PjSipAor.builder().contact(entity.getContact()).build();
  }

  public List<PjSipAorDto> entityToDto(List<PjSipAor> pjSipAors) {
    return pjSipAors.stream().map(pjSipAor -> entityToDto(pjSipAor)).collect(Collectors.toList());
  }

  public List<PjSipAor> dtoToEntity(List<PjSipAorDto> pjSipAors) {
    return pjSipAors.stream().map(pjSipAor -> dtoToEntity(pjSipAor)).collect(Collectors.toList());
  }
}
