package de.appointmenttool.api.dtos;

import lombok.Data;

@Data
public class PersonDTO {
  private String firstname;
  private String lastname;
  private String email;
  private Long postcode;
  private String city;
  private String street;
  private Integer streetNumber;
}
