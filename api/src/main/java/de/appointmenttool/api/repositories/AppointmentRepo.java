package de.appointmenttool.api.repositories;

import de.appointmenttool.api.models.Appointment;
import de.appointmenttool.api.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RepositoryRestResource(collectionResourceRel = "appointments", path = "appointments")
public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {

  @Query("SELECT a FROM Appointment a WHERE a.person = :person")
  Appointment findAppointmentsByPerson(@Param("person")Person person);

  @Query("SELECT a FROM Appointment a WHERE a.appointmentId = :appointmentId")
  Optional<Appointment> findAppointmentById(@Param("appointmentId") String appointmentId);
}
