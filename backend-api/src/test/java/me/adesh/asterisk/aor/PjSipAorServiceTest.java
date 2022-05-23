package me.adesh.asterisk.aor;

import static org.assertj.core.api.Assertions.assertThat;

import me.adesh.asterisk.dto.AorRequest;
import me.adesh.asterisk.model.PjSipAor;
import me.adesh.asterisk.model.enums.YesNo;
import me.adesh.asterisk.repository.PjSipAorRepository;
import me.adesh.asterisk.service.PjsipAorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PjSipAorServiceTest {

  @Mock
  PjSipAorRepository pjSipAorRepository;

  @InjectMocks
  PjsipAorService pjsipAorService;

  @Test
  void when_save_aor_it_should_return_aor() {
    AorRequest aorRequest = new AorRequest();
    aorRequest.setId("123");
    aorRequest.setMaxContacts(1);
    aorRequest.setRemoveExisting(YesNo.yes);
    aorRequest.setRemoveUnavailable(YesNo.yes);

    Mockito.when(pjSipAorRepository.save(Mockito.any(PjSipAor.class))).thenReturn(PjSipAor.builder().id(aorRequest.getId()).build());

    PjSipAor pjSipAor = pjsipAorService.save(aorRequest);

    assertThat(pjSipAor.getId()).isSameAs(aorRequest.getId());

  }
}
