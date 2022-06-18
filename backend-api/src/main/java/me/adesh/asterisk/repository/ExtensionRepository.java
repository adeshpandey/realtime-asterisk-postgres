package me.adesh.asterisk.repository;

import me.adesh.asterisk.model.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, Integer> {

}
