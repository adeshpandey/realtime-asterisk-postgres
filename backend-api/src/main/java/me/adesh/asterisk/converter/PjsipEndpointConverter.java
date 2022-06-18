package me.adesh.asterisk.converter;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.dto.PjsipEndpointDto;
import me.adesh.asterisk.model.PjSipEndpoint;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PjsipEndpointConverter {

  public PjsipEndpointDto entityToDto(PjSipEndpoint entity) {
    return PjsipEndpointDto.builder()
        .auth(entity.getAuth())
        .context(entity.getContext())
        .aors(entity.getAors())
        .id(entity.getId())
        .build();
  }

  public PjSipEndpoint dtoToEntity(PjsipEndpointDto entity) {
    return PjSipEndpoint.builder()
        .auth(entity.getAuth())
        .aors(String.join(",", entity.getAors()))
        .id(entity.getId())
        .build();
  }

  public List<PjsipEndpointDto> entityToDto(List<PjSipEndpoint> pjSipEndpoints) {
    return pjSipEndpoints.stream().map(pjSipEndpoint -> entityToDto(pjSipEndpoint))
        .collect(Collectors.toList());
  }

  public List<PjSipEndpoint> dtoToEntity(List<PjsipEndpointDto> pjsipEndpointDtos) {
    return pjsipEndpointDtos.stream().map(pjsipEndpointDto -> dtoToEntity(pjsipEndpointDto))
        .collect(Collectors.toList());
  }
}