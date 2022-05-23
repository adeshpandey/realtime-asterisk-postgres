package me.adesh.asterisk.repository;

import me.adesh.asterisk.model.PjSipAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PjSipAuthRepository extends JpaRepository<PjSipAuth, String> {

  Iterable<PjSipAuth> findByRealm(String realm);

}

