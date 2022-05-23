package me.adesh.asterisk.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.dto.AorRequest;
import me.adesh.asterisk.model.PjSipAor;
import me.adesh.asterisk.model.enums.YesNo;
import me.adesh.asterisk.repository.PjSipAorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PjsipAorService {

  private final PjSipAorRepository pjSipAorRepository;

  public List<PjSipAor> findAll() {
    return pjSipAorRepository.findAll();
  }

  public void delete(String id) {
    pjSipAorRepository.deleteById(id);
  }

  public void save(AorRequest aorRequest) {
    PjSipAor pjSipAor = PjSipAor.builder()
        .id(aorRequest.getId())
        .contact(aorRequest.getContact())
        .maxContacts(aorRequest.getMaxContacts())
        .authenticateQualify(YesNo.yes)
        .removeExisting(aorRequest.getRemoveExisting())
        .removeUnavailable(aorRequest.getRemoveUnavailable())
        .build();
    pjSipAorRepository.save(pjSipAor);
  }

  public void update(String id, AorRequest aorRequest) {
    if (pjSipAorRepository.existsById(id)) {
      PjSipAor pjSipAor = pjSipAorRepository.getById(id);
      pjSipAor.setContact(aorRequest.getContact());
      pjSipAor.setRemoveExisting(aorRequest.getRemoveExisting());
      pjSipAor.setRemoveUnavailable(aorRequest.getRemoveUnavailable());
      pjSipAor.setMaxContacts(aorRequest.getMaxContacts());
      pjSipAorRepository.save(pjSipAor);
    }
  }
}
