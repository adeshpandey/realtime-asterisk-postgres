package me.adesh.asterisk.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.model.PjSipAor;
import me.adesh.asterisk.repository.PjSipAorRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PjsipAorService {
  private final PjSipAorRepository pjSipAorRepository;

  public List<PjSipAor> findAll(){
    return pjSipAorRepository.findAll();
  }

  public void delete(String id){
    pjSipAorRepository.deleteById(id);
  }
}
