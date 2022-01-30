package leesh.study.hellorest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public ResponseEntity<Object> hello() {
        return ResponseEntity.ok("hello, REST!");
    }

}