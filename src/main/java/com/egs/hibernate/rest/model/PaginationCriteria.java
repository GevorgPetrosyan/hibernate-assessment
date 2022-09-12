package com.egs.hibernate.rest.model;

import com.egs.hibernate.validation.annotations.ValidPropertiesForSort;
import lombok.Data;
import org.springframework.data.domain.Sort.Direction;

import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Data
public class PaginationCriteria {
  @Min(value = 0, message = "Please input page that bigger or equal 0")
  private int page;
  @Min(value = 1, message = "Please input size that bigger or equal 1")
  private int size;
  @ValidPropertiesForSort(message = "Please input valid propertyForSort")
  private List<String> propertiesForSort = List.of("username");
  private Direction sortDirection = ASC;
}
