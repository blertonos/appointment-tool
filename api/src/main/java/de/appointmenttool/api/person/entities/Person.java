package de.appointmenttool.api.person.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import de.appointmenttool.api.appointment.entities.Appointment;
import de.appointmenttool.api.person.dtos.PersonDTO;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;
  @NonNull
  private String firstname;
  @NonNull
  private String lastname;
  @NonNull
  private String email;
  @NonNull
  private Long postcode;
  @NonNull
  private String city;
  @NonNull
  private String street;
  @NonNull
  private Integer streetNumber;

  @OneToOne(mappedBy = "person")
  @JsonBackReference
  private Appointment appointment;

  public Person(PersonDTO personDTO) {
    this.firstname = personDTO.getFirstname();
    this.lastname = personDTO.getLastname();
    this.email = personDTO.getEmail();
    this.postcode = personDTO.getPostcode();
    this.city = personDTO.getCity();
    this.street = personDTO.getStreet();
    this.streetNumber = personDTO.getStreetNumber();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Person person = (Person) o;
    return id != null && Objects.equals(id, person.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
