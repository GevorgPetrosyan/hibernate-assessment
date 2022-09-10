package com.egs.hibernate.validation;

import com.egs.hibernate.validation.annotations.ValidPropertiesForSort;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@RequiredArgsConstructor
public class ValidPropertiesConstraint implements ConstraintValidator<ValidPropertiesForSort, List<String>> {
  private static final List<String> ACCESSED_PROPERTIES = List.of("username", "firstName", "lastName", "birthdate");

  @Override
  public boolean isValid(List<String> propertiesForSort, ConstraintValidatorContext constraintValidatorContext) {
    return ACCESSED_PROPERTIES.containsAll(propertiesForSort);
  }
}