package pl.kagmajn.writer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private static boolean healthy = false;

    public static boolean isHealthy() {
        return healthy;
    }

    @GetMapping(value = "/ops/health", produces = "application/json")
    public ResponseEntity<String> getHealth() {
        if(isHealthy()) {
            return new ResponseEntity<>(getStatusJson("OK"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(getStatusJson("DOWN"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String getStatusJson(final String status){
        return "{\"status\": \"" + status + "\"}";
    }

    public static void setHealthy(final boolean isHealthy) {
        HealthController.healthy = isHealthy;
    }
}