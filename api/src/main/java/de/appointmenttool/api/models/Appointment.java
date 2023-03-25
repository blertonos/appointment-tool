package de.appointmenttool.api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Appointment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", nullable = false)
  private UUID id;

  @Column(unique=true)
  private String appointmentId;

  @NonNull
  private LocalDateTime startDate;
  @NonNull
  private LocalDateTime endDate;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "person_id", referencedColumnName = "id")
  @JsonManagedReference
  @NonNull
  private Person person;

  public Appointment(@NonNull LocalDateTime startDate, @NonNull LocalDateTime endDate) {
    this.appointmentId = generatePublicId();
    this.startDate = startDate;
    this.endDate = endDate;
  }

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

  private String generatePublicId() {
    return "AT".concat("_").concat(RandomStringUtils.randomAlphabetic(10).toUpperCase());
  }
}
