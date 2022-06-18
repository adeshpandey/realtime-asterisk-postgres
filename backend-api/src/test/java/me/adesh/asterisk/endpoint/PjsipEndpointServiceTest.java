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
    String[] aors = {"123"};
    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setId("123");
    endpointRequest.setAors(aors);
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

  @Test
  void when_edit_by_id_its_updated() {
    String id = "123";
    String[] aors = {"123"};

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setAuth(id);
    endpointRequest.setId(id);
    endpointRequest.setAors(aors);

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint
        .builder()
        .id(id)
        .aors(id)
        .auth(id)
        .build();

    Mockito.when(pjSipEndpointRepository.existsById(Mockito.any())).thenReturn(true);
    Mockito.when(pjSipEndpointRepository.getById(Mockito.any())).thenReturn(pjSipEndpoint);
    Mockito.when(pjSipEndpointRepository.save(Mockito.any(PjSipEndpoint.class))).thenReturn(pjSipEndpoint);

    PjSipEndpoint pjSipEndpoint1 = pjsipEndpointService.updateById(id, endpointRequest);

    assertThat(pjSipEndpoint1.getAuth()).isSameAs(pjSipEndpoint.getAuth());
  }

  @Test
  void when_edit_by_id_but_id_is_invalid_not_updated() {
    String id = "123";
    String[] aors = {"123"};

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setAuth(id);
    endpointRequest.setId(id);
    endpointRequest.setAors(aors);

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint
        .builder()
        .id(id)
        .aors(id)
        .auth(id)
        .build();

    Mockito.when(pjSipEndpointRepository.existsById(Mockito.any())).thenReturn(false);
    Mockito.when(pjSipEndpointRepository.save(Mockito.any(PjSipEndpoint.class))).thenReturn(pjSipEndpoint);

    PjSipEndpoint pjSipEndpoint1 = pjsipEndpointService.updateById(id, endpointRequest);

    assertThat(pjSipEndpoint1).isSameAs(null);
  }
}
