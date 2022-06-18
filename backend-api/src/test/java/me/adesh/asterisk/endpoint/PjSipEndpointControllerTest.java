package me.adesh.asterisk.endpoint;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import me.adesh.asterisk.controller.PjsipEndpointController;
import me.adesh.asterisk.converter.PjsipEndpointConverter;
import me.adesh.asterisk.dto.EndpointRequest;
import me.adesh.asterisk.dto.PjsipEndpointDto;
import me.adesh.asterisk.model.PjSipEndpoint;
import me.adesh.asterisk.service.PjsipEndpointService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PjsipEndpointController.class)
public class PjSipEndpointControllerTest {

  @MockBean
  PjsipEndpointService pjsipEndpointService;

  @MockBean
  PjsipEndpointConverter pjsipEndpointConverter;

  @Autowired
  private MockMvc mockMvc;

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void it_should_return_create_endpoint_401_if_not_auth() throws Exception {
    String[] aors = {"123"};

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setId("123");
    endpointRequest.setAors(aors);
    endpointRequest.setAuth("123");

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder()
        .id(endpointRequest.getId())
        .auth(endpointRequest.getAuth())
        .aors(String.join(",", endpointRequest.getAors()))
        .build();

    Mockito.when(pjsipEndpointService.save(Mockito.any(EndpointRequest.class)))
        .thenReturn(pjSipEndpoint);

    mockMvc.perform(
            post("/endpoints")
                .content(objectMapper.writeValueAsBytes(endpointRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is4xxClientError());
  }

  @Test
  void it_should_return_created_aor() throws Exception {
    String[] aors = {"123"};

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setId("123");
    endpointRequest.setAors(aors);
    endpointRequest.setAuth("123");

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder()
        .id(endpointRequest.getId())
        .auth(endpointRequest.getAuth())
        .aors(String.join(",", endpointRequest.getAors()))
        .build();

    Mockito.when(pjsipEndpointService.save(Mockito.any(EndpointRequest.class)))
        .thenReturn(pjSipEndpoint);

    mockMvc.perform(
            post("/endpoints")
                .content(objectMapper.writeValueAsBytes(endpointRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .with(jwt())
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(endpointRequest.getId()));
  }

  @Test
  void it_should_return_list_of_endpoints_401_if_no_auth() throws Exception {

    String[] aors = {"123"};

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setId("123");
    endpointRequest.setAors(aors);
    endpointRequest.setAuth("123");

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder()
        .id(endpointRequest.getId())
        .auth(endpointRequest.getAuth())
        .aors(String.join(",", endpointRequest.getAors()))
        .build();

    PjsipEndpointDto pjsipEndpointDto = PjsipEndpointDto.builder().id(pjSipEndpoint.getId())
        .auth(pjSipEndpoint.getAuth())
        .aors(pjSipEndpoint.getAors())
        .build();

    Mockito.when(pjsipEndpointService.findAll())
        .thenReturn(Collections.singletonList(pjSipEndpoint));
    Mockito.when(pjsipEndpointConverter.entityToDto(Collections.singletonList(pjSipEndpoint)))
        .thenReturn(Collections.singletonList(pjsipEndpointDto));

    mockMvc.perform(
            get("/endpoints")
        )
        .andExpect(status().is4xxClientError());

  }

  @Test
  void it_should_return_list_of_aors() throws Exception {
    String[] aors = {"123"};

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setId("123");
    endpointRequest.setAors(aors);
    endpointRequest.setAuth("123");

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder()
        .id(endpointRequest.getId())
        .auth(endpointRequest.getAuth())
        .aors(String.join(",", endpointRequest.getAors()))
        .build();

    PjsipEndpointDto pjsipEndpointDto = PjsipEndpointDto.builder().id(pjSipEndpoint.getId())
        .auth(pjSipEndpoint.getAuth())
        .aors(pjSipEndpoint.getAors())
        .build();

    Mockito.when(pjsipEndpointService.findAll())
        .thenReturn(Collections.singletonList(pjSipEndpoint));
    Mockito.when(pjsipEndpointConverter.entityToDto(Collections.singletonList(pjSipEndpoint)))
        .thenReturn(Collections.singletonList(pjsipEndpointDto));

    mockMvc.perform(
            get("/endpoints")
                .with(jwt())
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(endpointRequest.getId()));

  }

  @Test
  void when_delete_endpoint_it_should_return_401_if_not_auth() throws Exception {
    String id = "123";
    Mockito.when(pjsipEndpointService.deleteById(id))
        .thenReturn("SUCCESS");

    mockMvc.perform(
            delete("/endpoints/" + id)
        )
        .andExpect(status().is4xxClientError());
  }

  @Test
  void when_delete_existing_endpoint_it_should_return_success() throws Exception {
    String id = "123";
    Mockito.when(pjsipEndpointService.deleteById(id))
        .thenReturn("SUCCESS");

    mockMvc.perform(
            delete("/endpoints/" + id)
                .with(jwt())
        )
        .andExpect(status().isOk());
  }

  @Test
  void when_update_without_auth_it_should_return_401() throws Exception {
    String id = "123";
    String[] aors = {"123"};

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setAors(aors);
    endpointRequest.setAuth("123");

    Mockito.when(pjsipEndpointService.updateById(id, endpointRequest))
        .thenReturn(null);

    mockMvc.perform(
            put("/endpoints/" + id)
                .content(objectMapper.writeValueAsBytes(endpointRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is4xxClientError());
  }

  @Test
  void when_update_with_auth_but_invalid_id_it_should_return_400() throws Exception {
    String id = "123";
    String[] aors = {"123"};

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setAors(aors);
    endpointRequest.setAuth("123");

    Mockito.when(pjsipEndpointService.updateById(id, endpointRequest))
        .thenReturn(null);

    mockMvc.perform(
            put("/endpoints/" + id)
                .content(objectMapper.writeValueAsBytes(endpointRequest))
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)

        )
        .andExpect(status().isBadRequest());
  }

  @Test
  void when_update_with_auth_but_valid_id_it_should_return_success() throws Exception {
    String id = "123";
    String[] aors = {"123"};

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setAors(aors);
    endpointRequest.setAuth("123");

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder()
        .id(id)
        .aors(id)
        .auth(id)
        .build();

    Mockito.when(pjsipEndpointService.updateById(Mockito.any(), Mockito.any()))
        .thenReturn(pjSipEndpoint);

    mockMvc.perform(
            put("/endpoints/" + id)
                .content(objectMapper.writeValueAsBytes(endpointRequest))
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)

        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id));
  }

}
