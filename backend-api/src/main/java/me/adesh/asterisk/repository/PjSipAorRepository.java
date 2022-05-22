package me.adesh.asterisk.repository;

import me.adesh.asterisk.model.PjSipAor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PjSipAorRepository extends JpaRepository<PjSipAor, String> {

  Iterable<PjSipAor> findByContact(String contact);

}

