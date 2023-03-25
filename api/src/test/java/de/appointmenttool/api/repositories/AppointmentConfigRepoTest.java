package de.appointmenttool.api.repositories;

import de.appointmenttool.api.models.AppointmentConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AppointmentConfigRepoTest {

  @Autowired
  private AppointmentConfigRepo underTest;

  @AfterEach
  void tearDown() {
    underTest.deleteAll();
  }

  @Test
  void findByConfigName() {
    //given
    var factory = new PodamFactoryImpl();
    var appointmentConfig = factory.manufacturePojo(AppointmentConfig.class);
    appointmentConfig.setId(null);
    underTest.save(appointmentConfig);

    //when
    var expected = underTest.findByConfigName(appointmentConfig.getConfigName());

    //then
    assertThat(expected).isEqualTo(appointmentConfig);
  }
}