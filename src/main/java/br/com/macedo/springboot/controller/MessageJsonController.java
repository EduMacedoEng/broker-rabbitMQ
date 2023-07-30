package br.com.macedo.springboot.controller;

import br.com.macedo.springboot.dto.UserDTO;
import br.com.macedo.springboot.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
    private final RabbitMQJsonProducer jsonProducer;

    public MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.jsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody UserDTO userDTO) {
        jsonProducer.sendJsonMessage(userDTO);

        return ResponseEntity.ok("Json message sent to RabbitMQ ...");
    }
}
