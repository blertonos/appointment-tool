package de.appointmenttool.api.services;

import de.appointmenttool.api.dtos.PersonDTO;
import de.appointmenttool.api.exceptions.AppointmentConfigNotFoundException;
import de.appointmenttool.api.exceptions.AppointmentNotFoundException;
import de.appointmenttool.api.models.Appointment;
import de.appointmenttool.api.models.Person;
import de.appointmenttool.api.repositories.AppointmentRepo;
import de.appointmenttool.api.repositories.PersonRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class AppointmentService {

  private final AppointmentConfigService configService;
  private final AppointmentRepo appointmentRepo;
  private final PersonRepo personRepo;

  private static final String APPOINTMENT_INTERVAL_CONFIG = "APPOINTMENT_INTERVAL_IN_MINUTES";
  private static final String APPOINTMENT_START_TIME_MINUTES_CONFIG =  "APPOINTMENT_START_TIME_MINUTES";
  private static final String APPOINTMENT_START_TIME_HOURS_CONFIG =  "APPOINTMENT_START_TIME_HOURS";
  private static final String APPOINTMENT_END_TIME_MINUTES_CONFIG =  "APPOINTMENT_END_TIME_MINUTES";
  private static final String APPOINTMENT_END_TIME_HOURS_CONFIG =  "APPOINTMENT_END_TIME_HOURS";

  public Appointment getAppointmentById(String appointmentId) throws AppointmentNotFoundException {
    //TODO: Create RestControllerAdvice to give meaningful message to the user
    return appointmentRepo
            .findAppointmentById(appointmentId)
            .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found!"));
  }

  public List<Appointment> startAppointmentGeneration(LocalDateTime generationDate) {
    try {
      log.info("Appointment generation started for date: {}", generationDate);
      var appointmentIntervalInMinutes = configService.getIntegerValueByConfigName(APPOINTMENT_INTERVAL_CONFIG);
      var startTimeHours = configService.getIntegerValueByConfigName(APPOINTMENT_START_TIME_HOURS_CONFIG);
      var startTimeMinutes = configService.getIntegerValueByConfigName(APPOINTMENT_START_TIME_MINUTES_CONFIG);
      var endTimeHours = configService.getIntegerValueByConfigName(APPOINTMENT_END_TIME_HOURS_CONFIG);
      var endTimeMinutes = configService.getIntegerValueByConfigName(APPOINTMENT_END_TIME_MINUTES_CONFIG);

      var startDate = generationDate
              .withHour(startTimeHours)
              .withMinute(startTimeMinutes)
              .withSecond(0)
              .withNano(0);
      var endDate = generationDate
              .withHour(endTimeHours)
              .withMinute(endTimeMinutes)
              .withSecond(0)
              .withNano(0);

      return appointmentRepo
              .saveAll(generateAppointments(startDate, endDate, appointmentIntervalInMinutes));
    } catch (AppointmentConfigNotFoundException e) {
      log.debug(e);
      log.info("Error during appointment generation: {}",
              Objects.requireNonNull(NestedExceptionUtils.getRootCause(e)).getMessage());
      return Collections.emptyList();
    }
  }

  public void bookAppointmentForPerson(String appointmentId, PersonDTO personDTO) throws AppointmentNotFoundException {
    var person = personRepo.save(new Person(personDTO));
    var appointment = getAppointmentById(appointmentId);
    appointment.setPerson(person);
    appointmentRepo.save(appointment);
  }

  private List<Appointment> generateAppointments(LocalDateTime startDate, LocalDateTime endDate, int appointmentIntervalInMinutes) {
    var appointments = new ArrayList<Appointment>();
    var dateInIteration = startDate;
    while (dateInIteration.isBefore(endDate) || dateInIteration.isEqual(endDate)) {
      appointments.add(
              new Appointment(dateInIteration, dateInIteration.plusMinutes(appointmentIntervalInMinutes))
      );
      dateInIteration = dateInIteration.plusMinutes(appointmentIntervalInMinutes);
    }
    return appointments;
  }

}
