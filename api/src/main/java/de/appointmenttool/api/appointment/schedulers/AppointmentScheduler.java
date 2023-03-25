package de.appointmenttool.api.appointment.schedulers;

import de.appointmenttool.api.appointment.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentScheduler {

  private final AppointmentService appointmentService;

  @Scheduled(cron = "0 0 6 * * MON-FRI")
  private void createAppointments() {
    appointmentService.startAppointmentGeneration(LocalDateTime.now());
  }
}
