package com.zihler.sample.adapters.presentation.controller;

import com.zihler.sample.adapters.presentation.rest.RestHelloWorldPresenter;
import com.zihler.sample.adapters.repositories.in_memory.InMemoryHelloWorldRepository;
import com.zihler.sample.application.usecases.helloworld.GetHelloWorldUseCase;
import com.zihler.sample.application.usecases.helloworld.inbound_ports.GetHelloWorld;
import com.zihler.sample.application.usecases.helloworld.outbound_ports.HelloWorldRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController("/sample")
public class HelloWorldResource {

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> helloWorld() {
        HelloWorldRepository helloWorldRepository = new InMemoryHelloWorldRepository();
        GetHelloWorld getHelloWorld = new GetHelloWorldUseCase(helloWorldRepository);
        RestHelloWorldPresenter helloWorldPresenter = new RestHelloWorldPresenter();
        getHelloWorld.with(helloWorldPresenter);
        return helloWorldPresenter.presentation();
    }
}
