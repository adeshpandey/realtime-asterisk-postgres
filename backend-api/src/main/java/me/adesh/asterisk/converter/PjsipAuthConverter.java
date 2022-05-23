package me.adesh.asterisk.converter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.dto.PjSipAuthDto;
import me.adesh.asterisk.model.PjSipAuth;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PjsipAuthConverter {

  public PjSipAuthDto entityToDto(PjSipAuth entity) {
    return PjSipAuthDto.builder()
        .authType(entity.getAuthType())
        .username(entity.getUsername())
        .password(entity.getPassword())
        .id(entity.getId())
        .build();
  }

  public PjSipAuth dtoToEntity(PjSipAuthDto entity) {
    return PjSipAuth.builder()
        .id(entity.getId())
        .authType(entity.getAuthType())
        .username(entity.getUsername())
        .password(entity.getPassword())
        .build();
  }

  public List<PjSipAuthDto> entityToDto(List<PjSipAuth> pjSipAuths) {
    return pjSipAuths.stream().map(pjSipAuth -> entityToDto(pjSipAuth))
        .collect(Collectors.toList());
  }

  public List<PjSipAuth> dtoToEntity(List<PjSipAuthDto> pjSipAuths) {
    return pjSipAuths.stream().map(pjSipAuth -> dtoToEntity(pjSipAuth))
        .collect(Collectors.toList());
  }
}
