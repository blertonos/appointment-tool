package de.appointmenttool.api.appointment.services;

import de.appointmenttool.api.appointment.entities.AppointmentConfigNotFoundException;
import de.appointmenttool.api.appointment.repositories.AppointmentConfigRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppointmentConfigService {

  private final AppointmentConfigRepo appointmentConfigRepo;

  public int getIntegerValueByConfigName(String configName) throws AppointmentConfigNotFoundException {
    return appointmentConfigRepo
            .findByConfigName(configName)
            .orElseThrow(
                    () -> new AppointmentConfigNotFoundException(
                            "The config with name " + configName + " is not existing!"
                    )
            )
            .getConfigIntegerValue();
  }

  public String getStringValueByConfigName(String configName) throws AppointmentConfigNotFoundException {
    return appointmentConfigRepo
            .findByConfigName(configName)
            .orElseThrow(
                    () -> new AppointmentConfigNotFoundException(
                            "The config with name " + configName + " is not existing!"
                    )
            )
            .getConfigStringValue();
  }
}
