package de.appointmenttool.api.repositories;

import de.appointmenttool.api.models.AppointmentConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "appointment-configs", path = "appointment-configs")
public interface AppointmentConfigRepo extends JpaRepository<AppointmentConfig, UUID> {
  AppointmentConfig findByName(String name);
}
