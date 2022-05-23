package me.adesh.asterisk.endpoint;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;
import me.adesh.asterisk.dto.EndpointRequest;
import me.adesh.asterisk.model.PjSipEndpoint;
import me.adesh.asterisk.repository.PjSipEndpointRepository;
import me.adesh.asterisk.service.PjsipEndpointService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PjsipEndpointServiceTest {

  @Mock
  PjSipEndpointRepository pjSipEndpointRepository;

  @InjectMocks
  PjsipEndpointService pjsipEndpointService;

  @Test
  void when_save_endpoint_it_should_return_endpoint() {
    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setId("123");
    endpointRequest.setAors("123");
    endpointRequest.setAuth("123");

    Mockito.when(pjSipEndpointRepository.save(Mockito.any(PjSipEndpoint.class)))
        .thenReturn(PjSipEndpoint.builder().id(endpointRequest.getId()).build());

    PjSipEndpoint pjSipEndpoint = pjsipEndpointService.save(endpointRequest);

    assertThat(pjSipEndpoint.getId()).isSameAs(endpointRequest.getId());
  }

  @Test
  void when_find_all_endpoints_it_should_return_list_of_endpoints() {
    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder().id("123").build();
    Mockito.when(pjSipEndpointRepository.findAll())
        .thenReturn(Collections.singletonList(pjSipEndpoint));

    List<PjSipEndpoint> pjSipEndpoints = pjsipEndpointService.findAll();

    assertThat(pjSipEndpoints.get(0).getId()).isSameAs(pjSipEndpoint.getId());
  }

  @Test
  void when_delete_by_id_its_delete_called() {
    String id = "123";
    pjsipEndpointService.deleteById(id);
    Mockito.verify(pjSipEndpointRepository).deleteById(Mockito.any());
  }

}
