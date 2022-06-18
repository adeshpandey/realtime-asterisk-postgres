package me.adesh.asterisk.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.dto.EndpointRequest;
import me.adesh.asterisk.dto.ExtensionRequest;
import me.adesh.asterisk.model.Extension;
import me.adesh.asterisk.model.PjSipEndpoint;
import me.adesh.asterisk.repository.ExtensionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExtensionService {

  private final ExtensionRepository extensionRepository;

  public Extension save(ExtensionRequest extensionRequest) {
    Extension extension = Extension.builder()
        .extension(extensionRequest.getExtension())
            .context(extensionRequest.getContext())
                .priority(extensionRequest.getPriority())
                    .app(extensionRequest.getApp())
                        .appData(extensionRequest.getAppData())
                            .build();
    return extensionRepository.save(extension);
  }

  public List<Extension> findAll() {
    return extensionRepository.findAll();
  }

  public String deleteById(int id) {
    extensionRepository.deleteById(id);
    return "SUCCESS";
  }

  public Extension updateById(Integer id, ExtensionRequest extensionRequest) {
    if (extensionRepository.existsById(id)) {
      Extension extension = extensionRepository.getById(id);
      extension.setContext(extensionRequest.getContext());
      extension.setExtension(extensionRequest.getExtension());
      extension.setPriority(extensionRequest.getPriority());
      extension.setApp(extensionRequest.getApp());
      extension.setAppData(extensionRequest.getAppData());

      return extensionRepository.save(extension);
    }
    return null;
  }
}
