package com.sajjantawar.gateway;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/v1/gateway/orders")
public class OrderProxyController {
 private final RestClient client=RestClient.builder().baseUrl("http://localhost:8081").build();

 @PostMapping
 public ResponseEntity<String> create(@RequestBody String body){
  return client.post().uri("/api/v1/orders").contentType(MediaType.APPLICATION_JSON).body(body).retrieve().toEntity(String.class);
 }
}