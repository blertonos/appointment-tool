package de.appointmenttool.api.repositories;

import de.appointmenttool.api.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface PersonRepo extends JpaRepository<Person, UUID> {
}
