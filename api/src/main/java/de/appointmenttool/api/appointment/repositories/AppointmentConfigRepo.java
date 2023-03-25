package de.appointmenttool.api.appointment.repositories;

import de.appointmenttool.api.appointment.entities.AppointmentConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;
import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "appointment-configs", path = "appointment-configs")
public interface AppointmentConfigRepo extends JpaRepository<AppointmentConfig, UUID> {
  Optional<AppointmentConfig> findByConfigName(String name);
}
