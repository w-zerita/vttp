package vttp.ssf.calculator.controllers;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@RestController
@RequestMapping(path = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
public class CalculatorController {

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postCalculate(@RequestBody String payload, 
        @RequestHeader(value="User-Agent") String userAgent) {

        System.out.printf(">>> user-agent: %s\n", userAgent);
        System.out.printf(">>> payload: %s\n", payload);
        
        JsonObject body;

        // throws a checked exception -> needs to be handled
        try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {
            JsonReader reader = Json.createReader(is);
            body = reader.readObject();
        } catch (Exception e) {
            body = Json.createObjectBuilder()
                .add("error", e.getMessage())
                .build();
            return ResponseEntity.internalServerError().body(body.toString());
        }

        int x = body.getInt("oper1");
        int y = body.getInt("oper2");
        String ops = body.getString("ops"); // operation

        int result = 0;

        switch(ops) {
            case "plus":
                result = x + y;
                break;
            case "minus":
                result = x - y;
                break;
            case "divide":
                result = x / y;
                break;
            case "multiply":
                result = x * y;
                break;
            default: // throws an unchecked exception -> unhandled
                throw new IllegalArgumentException("Invalid operator %s\n".formatted(ops)); // for testing
            //     JsonObject result = Json.createObjectBuilder()
            //         .add("error", "Incorrect operator: %s".formatted(ops))
            //         .build();
            //     return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            //         .body(result.toString());
        }

        JsonObject calculateResult = Json.createObjectBuilder()
            .add("result", result)
            .add("timestamp", (new Date().toString())) // System.currentTimeMillis()
            .add("userAgent", userAgent)
            .build();

        return ResponseEntity.ok(calculateResult.toString());
    }
}
