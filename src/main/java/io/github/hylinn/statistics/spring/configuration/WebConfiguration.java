package io.github.hylinn.statistics.spring.configuration;

import io.github.hylinn.statistics.spring.controller.PlayerController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = {PlayerController.class})
public class WebConfiguration  {
}