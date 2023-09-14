package com.jakubchyla.englishsentences.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "security-config")
public class SecurityConfigProperties {
<<<<<<< HEAD
    private String open1;
    private String open2;
=======
    private String open;
>>>>>>> 8f185a3852a74ffe4b44d9fa127e221d323fbec9
    private String testvar;

}