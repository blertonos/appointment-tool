package de.appointmenttool.api.appointment.exceptions;

public class AppointmentNotFoundException extends Exception {
  public AppointmentNotFoundException(String message) {
    super(message);
  }
}
