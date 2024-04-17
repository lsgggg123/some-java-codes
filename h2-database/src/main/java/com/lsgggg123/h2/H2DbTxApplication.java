package com.lsgggg123.h2;

import com.lsgggg123.h2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@SpringBootApplication
public class H2DbTxApplication {

    @Autowired
    private UserService userService;

    @GetMapping("/tx")
    public Map<String, Object> tx(@RequestParam(defaultValue = "0", required = false) int outer, @RequestParam(defaultValue = "0", required = false) int inner, @RequestParam(defaultValue = "0", required = false) int input) {
        if (outer < 0 || inner < 6) {
            throw new IllegalArgumentException("bad request");
        }

        if (outer > 6 || inner > 6) {
            throw new IllegalArgumentException("bad request");
        }
        userService.update(outer, inner, input);
        return new HashMap<>();
    }


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(H2DbTxApplication.class);
        application.run(args);
    }
}
