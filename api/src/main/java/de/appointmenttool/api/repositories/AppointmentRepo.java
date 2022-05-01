package de.appointmenttool.api.repositories;

import de.appointmenttool.api.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RepositoryRestResource(collectionResourceRel = "appointments", path = "appointments")
public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {
}
