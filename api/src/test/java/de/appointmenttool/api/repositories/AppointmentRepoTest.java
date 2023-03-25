package de.appointmenttool.api.repositories;

import de.appointmenttool.api.models.Appointment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
class AppointmentRepoTest {

  @Autowired
  private AppointmentRepo underTest;

  @AfterEach
  void tearDown() {
    this.underTest.deleteAll();
  }

  @Test
  void itShouldFindAllAppointmentsByPerson() {
    //given
    var factory = new PodamFactoryImpl();
    var appointment = factory.manufacturePojo(Appointment.class);
    appointment.setId(null);
    appointment.getPerson().setId(null);

    underTest.save(appointment);

    //when
    var expected = underTest.findAppointmentsByPerson(appointment.getPerson());

    //then
    assertThat(expected).isEqualTo(appointment);
  }
}