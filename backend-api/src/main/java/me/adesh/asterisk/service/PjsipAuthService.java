package me.adesh.asterisk.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import me.adesh.asterisk.dto.AuthRequest;
import me.adesh.asterisk.model.PjSipAuth;
import me.adesh.asterisk.repository.PjSipAuthRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PjsipAuthService {

  private final PjSipAuthRepository pjSipAuthRepository;

  public List<PjSipAuth> findAll() {
    return pjSipAuthRepository.findAll();
  }

  public void delete(String id) {
    pjSipAuthRepository.deleteById(id);
  }

  public void save(AuthRequest authRequest) {
    PjSipAuth pjSipAuth = PjSipAuth.builder()
        .id(authRequest.getId())
        .authType(authRequest.getAuthType())
        .username(authRequest.getUsername())
        .password(authRequest.getPassword())
        .build();
    pjSipAuthRepository.save(pjSipAuth);
  }

  public void update(String id, AuthRequest authRequest) {
    if (pjSipAuthRepository.existsById(id)) {
      PjSipAuth pjSipAuth = pjSipAuthRepository.getById(id);
      pjSipAuth.setAuthType(authRequest.getAuthType());
      pjSipAuth.setUsername(authRequest.getUsername());
      pjSipAuth.setPassword(authRequest.getPassword());
      pjSipAuthRepository.save(pjSipAuth);
    }
  }
}
