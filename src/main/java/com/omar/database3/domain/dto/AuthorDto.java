package com.omar.database3.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorDto {

  @SuppressWarnings("checkstyle:JavadocVariable")
  private Long id;

  @SuppressWarnings("checkstyle:JavadocVariable")
  private String name;

  @SuppressWarnings("checkstyle:JavadocVariable")
  private Integer age;

}
