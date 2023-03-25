package de.appointmenttool.api.person.repositories;

import de.appointmenttool.api.person.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.UUID;

public interface PersonRepo extends JpaRepository<Person, UUID> {
}
