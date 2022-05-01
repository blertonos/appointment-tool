package de.appointmenttool.api.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Person {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;
  private String firstname;
  private String lastname;
  private String email;
  private Long postcode;
  private String city;
  private String street;
  private Integer streetNumber;

  @OneToOne(mappedBy = "person")
  @JsonBackReference
  private Appointment appointment;

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
