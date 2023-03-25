package de.appointmenttool.api.appointment.exceptions;

public class AppointmentTakenException extends Exception {
  public AppointmentTakenException(String message) {
    super(message);
  }
}
