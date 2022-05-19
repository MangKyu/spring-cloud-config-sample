package com.mangkyu.config.client;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConfigController {

    private final LocalConfig localConfig;

    @GetMapping("/local")
    public ResponseEntity<LocalConfig> localConfig() {
        System.out.println(localConfig);
        return ResponseEntity.ok(localConfig);
    }

}
