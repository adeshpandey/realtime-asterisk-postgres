package me.adesh.asterisk.endpoint;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import javax.websocket.Endpoint;
import me.adesh.asterisk.controller.PjsipEndpointController;
import me.adesh.asterisk.converter.PjsipEndpointConverter;
import me.adesh.asterisk.dto.AorRequest;
import me.adesh.asterisk.dto.EndpointRequest;
import me.adesh.asterisk.dto.PjSipAorDto;
import me.adesh.asterisk.dto.PjsipEndpointDto;
import me.adesh.asterisk.model.PjSipAor;
import me.adesh.asterisk.model.PjSipEndpoint;
import me.adesh.asterisk.model.enums.YesNo;
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
    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setId("123");
    endpointRequest.setAors("123");
    endpointRequest.setAuth("123");

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder()
        .id(endpointRequest.getId())
        .auth(endpointRequest.getAuth())
        .aors(endpointRequest.getAors())
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
    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setId("123");
    endpointRequest.setAors("123");
    endpointRequest.setAuth("123");

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder()
        .id(endpointRequest.getId())
        .auth(endpointRequest.getAuth())
        .aors(endpointRequest.getAors())
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

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setId("123");
    endpointRequest.setAors("123");
    endpointRequest.setAuth("123");

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder()
        .id(endpointRequest.getId())
        .auth(endpointRequest.getAuth())
        .aors(endpointRequest.getAors())
        .build();

    PjsipEndpointDto pjsipEndpointDto = PjsipEndpointDto.builder().id(pjSipEndpoint.getId())
        .auth(pjSipEndpoint.getAuth())
        .aors(pjSipEndpoint.getAors())
        .build();

    Mockito.when(pjsipEndpointService.findAll()).thenReturn(Collections.singletonList(pjSipEndpoint));
    Mockito.when(pjsipEndpointConverter.entityToDto(Collections.singletonList(pjSipEndpoint)))
        .thenReturn(Collections.singletonList(pjsipEndpointDto));

    mockMvc.perform(
            get("/endpoints")
        )
        .andExpect(status().is4xxClientError());

  }

  @Test
  void it_should_return_list_of_aors() throws Exception {

    EndpointRequest endpointRequest = new EndpointRequest();
    endpointRequest.setId("123");
    endpointRequest.setAors("123");
    endpointRequest.setAuth("123");

    PjSipEndpoint pjSipEndpoint = PjSipEndpoint.builder()
        .id(endpointRequest.getId())
        .auth(endpointRequest.getAuth())
        .aors(endpointRequest.getAors())
        .build();

    PjsipEndpointDto pjsipEndpointDto = PjsipEndpointDto.builder().id(pjSipEndpoint.getId())
        .auth(pjSipEndpoint.getAuth())
        .aors(pjSipEndpoint.getAors())
        .build();


    Mockito.when(pjsipEndpointService.findAll()).thenReturn(Collections.singletonList(pjSipEndpoint));
    Mockito.when(pjsipEndpointConverter.entityToDto(Collections.singletonList(pjSipEndpoint)))
        .thenReturn(Collections.singletonList(pjsipEndpointDto));

    mockMvc.perform(
            get("/endpoints")
                .with(jwt())
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(endpointRequest.getId()));

  }
}
