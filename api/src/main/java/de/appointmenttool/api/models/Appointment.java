package de.appointmenttool.api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Appointment {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique=true)
  private Long appointmentId;

  private LocalDateTime startDate;
  private LocalDateTime endDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  @JsonManagedReference
  private Person person;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Appointment that = (Appointment) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
