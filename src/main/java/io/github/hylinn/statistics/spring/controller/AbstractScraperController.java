package io.github.hylinn.statistics.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AbstractScraperController {

    protected ResponseEntity<Boolean> success() {
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }
}
