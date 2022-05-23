package me.adesh.asterisk.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.dto.EndpointRequest;
import me.adesh.asterisk.model.PjSipEndpoint;
import me.adesh.asterisk.repository.PjSipEndpointRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PjsipEndpointService {
  private final PjSipEndpointRepository pjSipEndpointRepository;

  public PjSipEndpoint save(EndpointRequest endpointRequest){
    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder()
            .id(endpointRequest.getId())
                .aors(endpointRequest.getAors())
                    .auth(endpointRequest.getAuth())
                        .build();
    return pjSipEndpointRepository.save(pjSipEndpoint);
  }

  public List<PjSipEndpoint> findAll() {
    return pjSipEndpointRepository.findAll();
  }
}
