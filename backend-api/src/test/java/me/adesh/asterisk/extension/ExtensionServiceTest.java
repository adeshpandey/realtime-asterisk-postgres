package me.adesh.asterisk.extension;

import static org.assertj.core.api.Assertions.assertThat;

import me.adesh.asterisk.dto.ExtensionRequest;
import me.adesh.asterisk.model.Extension;
import me.adesh.asterisk.repository.ExtensionRepository;
import me.adesh.asterisk.service.ExtensionService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExtensionServiceTest {

  @Mock
  ExtensionRepository extensionRepository;

  @InjectMocks
  ExtensionService extensionService;

  @Test
  void when_save_extension_it_should_return_extension() {
    ExtensionRequest extensionRequest = new ExtensionRequest();
    extensionRequest.setContext("hello-world");
    extensionRequest.setExtension("123");
    extensionRequest.setPriority(1);

    Mockito.when(extensionRepository.save(Mockito.any(Extension.class)))
        .thenReturn(Extension.builder().context(extensionRequest.getContext()).build());

    Extension extension = extensionService.save(extensionRequest);

    assertThat(extension.getContext()).isSameAs(extensionRequest.getContext());
  }

}
