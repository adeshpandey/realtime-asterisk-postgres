package me.adesh.asterisk.aor;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import me.adesh.asterisk.controller.PjSipAorController;
import me.adesh.asterisk.converter.PjsipAorConverter;
import me.adesh.asterisk.dto.AorRequest;
import me.adesh.asterisk.dto.PjSipAorDto;
import me.adesh.asterisk.model.PjSipAor;
import me.adesh.asterisk.model.enums.YesNo;
import me.adesh.asterisk.service.PjsipAorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PjSipAorController.class)
class PjSipAorControllerTest {

  @MockBean
  PjsipAorService pjsipAorService;

  @MockBean
  PjsipAorConverter pjsipAorConverter;

  @Autowired
  private MockMvc mockMvc;

  ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void it_should_return_create_aor_401_if_not_auth() throws Exception {

    AorRequest aorRequest = new AorRequest();
    aorRequest.setId("123");
    aorRequest.setMaxContacts(1);
    aorRequest.setRemoveExisting(YesNo.yes);
    aorRequest.setRemoveUnavailable(YesNo.yes);

    PjSipAor pjSipAor = PjSipAor.builder()
        .id(aorRequest.getId())
        .maxContacts(aorRequest.getMaxContacts())
        .removeExisting(aorRequest.getRemoveExisting())
        .removeUnavailable(aorRequest.getRemoveUnavailable())
        .build();

    Mockito.when(pjsipAorService.save(Mockito.any(AorRequest.class))).thenReturn(pjSipAor);

    mockMvc.perform(
            post("/aors")
                .content(objectMapper.writeValueAsBytes(aorRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().is4xxClientError());
  }

  @Test
  void it_should_return_created_aor() throws Exception {

    AorRequest aorRequest = new AorRequest();
    aorRequest.setId("123");
    aorRequest.setMaxContacts(1);
    aorRequest.setRemoveExisting(YesNo.yes);
    aorRequest.setRemoveUnavailable(YesNo.yes);

    PjSipAor pjSipAor = PjSipAor.builder()
        .id(aorRequest.getId())
        .maxContacts(aorRequest.getMaxContacts())
        .removeExisting(aorRequest.getRemoveExisting())
        .removeUnavailable(aorRequest.getRemoveUnavailable())
        .build();

    Mockito.when(pjsipAorService.save(Mockito.any(AorRequest.class))).thenReturn(pjSipAor);

    mockMvc.perform(
            post("/aors")
                .content(objectMapper.writeValueAsBytes(aorRequest)).with(jwt())
                .contentType(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(aorRequest.getId()));

  }

  @Test
  void it_should_return_list_of_aors_401_if_no_auth() throws Exception {

    AorRequest aorRequest = new AorRequest();
    aorRequest.setId("456");
    aorRequest.setMaxContacts(1);
    aorRequest.setRemoveExisting(YesNo.yes);
    aorRequest.setRemoveUnavailable(YesNo.yes);

    PjSipAor pjSipAor = PjSipAor.builder()
        .id(aorRequest.getId())
        .maxContacts(aorRequest.getMaxContacts())
        .removeExisting(aorRequest.getRemoveExisting())
        .removeUnavailable(aorRequest.getRemoveUnavailable())
        .build();

    PjSipAorDto pjSipAorDto = PjSipAorDto.builder().id(pjSipAor.getId())
        .maxContacts(pjSipAor.getMaxContacts())
        .removeExisting(pjSipAor.getRemoveExisting())
        .removeUnavailable(pjSipAor.getRemoveUnavailable()).build();

    Mockito.when(pjsipAorService.findAll()).thenReturn(Collections.singletonList(pjSipAor));
    Mockito.when(pjsipAorConverter.entityToDto(Collections.singletonList(pjSipAor)))
        .thenReturn(Collections.singletonList(pjSipAorDto));

    mockMvc.perform(
            get("/aors")
        )
        .andExpect(status().is4xxClientError());

  }

  @Test
  void it_should_return_list_of_aors() throws Exception {

    AorRequest aorRequest = new AorRequest();
    aorRequest.setId("456");
    aorRequest.setMaxContacts(1);
    aorRequest.setRemoveExisting(YesNo.yes);
    aorRequest.setRemoveUnavailable(YesNo.yes);

    PjSipAor pjSipAor = PjSipAor.builder()
        .id(aorRequest.getId())
        .maxContacts(aorRequest.getMaxContacts())
        .removeExisting(aorRequest.getRemoveExisting())
        .removeUnavailable(aorRequest.getRemoveUnavailable())
        .build();

    PjSipAorDto pjSipAorDto = PjSipAorDto.builder().id(pjSipAor.getId())
        .maxContacts(pjSipAor.getMaxContacts())
        .removeExisting(pjSipAor.getRemoveExisting())
        .removeUnavailable(pjSipAor.getRemoveUnavailable()).build();

    Mockito.when(pjsipAorService.findAll()).thenReturn(Collections.singletonList(pjSipAor));
    Mockito.when(pjsipAorConverter.entityToDto(Collections.singletonList(pjSipAor)))
        .thenReturn(Collections.singletonList(pjSipAorDto));

    mockMvc.perform(
            get("/aors")
                .with(jwt())
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(aorRequest.getId()));

  }
}
