package com.jakubchyla.englishsentences.sentenceController;

import com.jakubchyla.englishsentences.config.SecurityConfigProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/demo-controller-open")
public class DemoControllerOpen {


    private final SecurityConfigProperties securityConfigProperties;

    @GetMapping
    public ResponseEntity<String> sayHello() {
        String testvar = securityConfigProperties.getTestvar();
        return ResponseEntity.ok(testvar);
    }

}

