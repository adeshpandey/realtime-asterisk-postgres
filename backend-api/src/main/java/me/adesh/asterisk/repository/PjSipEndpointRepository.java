package me.adesh.asterisk.repository;

import me.adesh.asterisk.model.PjSipEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PjSipEndpointRepository extends JpaRepository<PjSipEndpoint, String> {

}
