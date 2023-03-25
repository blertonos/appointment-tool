package de.appointmenttool.api.appointment.controllers;


import de.appointmenttool.api.appointment.exceptions.AppointmentTakenException;
import de.appointmenttool.api.person.dtos.PersonDTO;
import de.appointmenttool.api.appointment.exceptions.AppointmentNotFoundException;
import de.appointmenttool.api.appointment.entities.Appointment;
import de.appointmenttool.api.appointment.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/v1/appointments")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentController {

  private final AppointmentService appointmentService;

  private static final String DATE_PATTERN = "dd.MM.yyyy";

  @PostMapping
  public List<Appointment> createAppointments(@RequestParam String appointmentDate) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
    LocalDate givenDate = LocalDate.parse(appointmentDate, formatter);
    return appointmentService.startAppointmentGeneration(givenDate.atStartOfDay());
  }

  @PutMapping("{appointmentId}/assign-person")
  public ResponseEntity<String> bookAppointmentForPerson(@PathVariable String appointmentId, @RequestBody PersonDTO person) throws AppointmentNotFoundException, AppointmentTakenException {
    appointmentService.bookAppointmentForPerson(appointmentId, person);
    //TODO: Create Response Object and return it here (Status, Message, Body)
    return ResponseEntity.ok("Success");
  }

}
