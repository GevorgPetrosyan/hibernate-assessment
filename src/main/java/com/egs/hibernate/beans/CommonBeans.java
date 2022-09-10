package com.egs.hibernate.beans;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBeans {
  @Bean
  public ModelMapper mapper() {
    return new ModelMapper();
  }
}
