package me.adesh.asterisk.extension;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import me.adesh.asterisk.controller.ExtensionController;
import me.adesh.asterisk.converter.ExtensionConverter;
import me.adesh.asterisk.dto.ExtensionDto;
import me.adesh.asterisk.dto.ExtensionRequest;
import me.adesh.asterisk.model.Extension;
import me.adesh.asterisk.service.ExtensionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ExtensionController.class)
public class ExtensionControllerTest {

  @MockBean
  ExtensionService extensionService;

  @MockBean
  ExtensionConverter extensionConverter;

  @Autowired
  private MockMvc mockMvc;

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void it_should_return_create_extension_401_if_not_auth() throws Exception {
    ExtensionRequest endpointRequest = new ExtensionRequest();
    endpointRequest.setContext("hello-world");
    endpointRequest.setExtension("123");
    endpointRequest.setPriority(1);
    endpointRequest.setApp("Playback");
    endpointRequest.setAppData("demo-thanks");

    Extension pjSipEndpoint = Extension.builder()
        .context(endpointRequest.getContext())
        .extension(endpointRequest.getExtension())
        .priority(endpointRequest.getPriority())
        .app(endpointRequest.getApp())
        .appData(endpointRequest.getAppData())
        .build();

    Mockito.when(extensionService.save(Mockito.any(ExtensionRequest.class)))
        .thenReturn(pjSipEndpoint);

    mockMvc.perform(
            post("/extensions")
                .content(objectMapper.writeValueAsBytes(endpointRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is4xxClientError());
  }

  @Test
  void it_should_return_created_extension() throws Exception {
    ExtensionRequest extensionRequest = new ExtensionRequest();
    extensionRequest.setContext("hello-world");
    extensionRequest.setExtension("123");
    extensionRequest.setPriority(1);
    extensionRequest.setApp("Playback");
    extensionRequest.setAppData("demo-thanks");

    Extension extension = Extension.builder()
        .id(1)
        .context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    ExtensionDto extensionDto = ExtensionDto.builder()
        .id(1)
        .context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    Mockito.when(extensionService.save(Mockito.any(ExtensionRequest.class)))
        .thenReturn(extension);

    Mockito.when(extensionConverter.entityToDto(extension))
        .thenReturn(extensionDto);

    mockMvc.perform(
            post("/extensions")
                .content(objectMapper.writeValueAsBytes(extensionRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .with(jwt())
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.context").value(extensionRequest.getContext()));
  }

  @Test
  void it_should_return_list_of_extension_401_if_no_auth() throws Exception {

    ExtensionRequest extensionRequest = new ExtensionRequest();
    extensionRequest.setContext("hell0-world");
    extensionRequest.setExtension("123");
    extensionRequest.setPriority(1);
    extensionRequest.setApp("Playback");
    extensionRequest.setAppData("demo-thanks");

    Extension extension = Extension.builder()
        .context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    ExtensionDto pjsipEndpointDto = ExtensionDto.builder().context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    Mockito.when(extensionService.findAll())
        .thenReturn(Collections.singletonList(extension));
    Mockito.when(extensionConverter.entityToDto(Collections.singletonList(extension)))
        .thenReturn(Collections.singletonList(pjsipEndpointDto));

    mockMvc.perform(
            get("/extensions")
        )
        .andExpect(status().is4xxClientError());

  }

  @Test
  void it_should_return_list_of_extension() throws Exception {

    ExtensionRequest extensionRequest = new ExtensionRequest();
    extensionRequest.setContext("hell0-world");
    extensionRequest.setExtension("123");
    extensionRequest.setPriority(1);
    extensionRequest.setApp("Playback");
    extensionRequest.setAppData("demo-thanks");

    Extension extension = Extension.builder()
        .context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    ExtensionDto pjsipEndpointDto = ExtensionDto.builder().context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    Mockito.when(extensionService.findAll())
        .thenReturn(Collections.singletonList(extension));
    Mockito.when(extensionConverter.entityToDto(Collections.singletonList(extension)))
        .thenReturn(Collections.singletonList(pjsipEndpointDto));

    mockMvc.perform(
            get("/extensions")
                .with(jwt())
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].context").value(extensionRequest.getContext()));

  }

  @Test
  void when_delete_extension_it_should_return_401_if_not_auth() throws Exception {
    int id = 123;
    Mockito.when(extensionService.deleteById(id))
        .thenReturn("SUCCESS");

    mockMvc.perform(
            delete("/extensions/" + id)
        )
        .andExpect(status().is4xxClientError());
  }

  @Test
  void when_delete_existing_extension_it_should_return_success() throws Exception {
    int id = 123;
    Mockito.when(extensionService.deleteById(id))
        .thenReturn("SUCCESS");

    mockMvc.perform(
            delete("/extensions/" + id)
                .with(jwt())
        )
        .andExpect(status().isOk());
  }

  @Test
  void when_update_without_auth_it_should_return_401() throws Exception {
    int id = 123;
    ExtensionRequest extensionRequest = new ExtensionRequest();
    extensionRequest.setContext("hell0-world");
    extensionRequest.setExtension("123");
    extensionRequest.setPriority(1);
    extensionRequest.setApp("Playback");
    extensionRequest.setAppData("demo-thanks");

    Extension extension = Extension.builder()
        .context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    ExtensionDto pjsipEndpointDto = ExtensionDto.builder().context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    Mockito.when(extensionService.updateById(id, extensionRequest))
        .thenReturn(null);

    mockMvc.perform(
            put("/extensions/" + id)
                .content(objectMapper.writeValueAsBytes(extensionRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is4xxClientError());
  }

  @Test
  void when_update_with_auth_but_invalid_id_it_should_return_400() throws Exception {
    int id = 123;
    ExtensionRequest extensionRequest = new ExtensionRequest();
    extensionRequest.setContext("hell0-world");
    extensionRequest.setExtension("123");
    extensionRequest.setPriority(1);
    extensionRequest.setApp("Playback");
    extensionRequest.setAppData("demo-thanks");

    Extension extension = Extension.builder()
        .context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    ExtensionDto pjsipEndpointDto = ExtensionDto.builder().context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    Mockito.when(extensionService.updateById(id, extensionRequest))
        .thenReturn(null);

    mockMvc.perform(
            put("/extensions/" + id)
                .content(objectMapper.writeValueAsBytes(extensionRequest))
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)

        )
        .andExpect(status().isBadRequest());
  }

  @Test
  void when_update_with_auth_but_valid_id_it_should_return_success() throws Exception {
    int id = 1;
    ExtensionRequest extensionRequest = new ExtensionRequest();
    extensionRequest.setContext("hell0-world");
    extensionRequest.setExtension("123");
    extensionRequest.setPriority(1);
    extensionRequest.setApp("Playback");
    extensionRequest.setAppData("demo-thanks");

    Extension extension = Extension.builder()
        .id(id)
        .context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    ExtensionDto extensionDto = ExtensionDto.builder()
        .id(extension.getId())
        .context(extensionRequest.getContext())
        .extension(extensionRequest.getExtension())
        .priority(extensionRequest.getPriority())
        .app(extensionRequest.getApp())
        .appData(extensionRequest.getAppData())
        .build();

    Mockito.when(extensionService.updateById(Mockito.any(), Mockito.any()))
        .thenReturn(extension);

    Mockito.when(extensionConverter.entityToDto(extension))
        .thenReturn(extensionDto);

    mockMvc.perform(
            put("/extensions/" + id)
                .content(objectMapper.writeValueAsBytes(extensionRequest))
                .with(jwt())
                .contentType(MediaType.APPLICATION_JSON)

        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id));
  }

}
