package com.zihler.sample.adapters.presentation.rest;

import com.zihler.sample.application.usecases.helloworld.outbound_ports.HelloWorldPresenter;
import com.zihler.sample.domain.values.HelloWorldDocument;
import org.springframework.http.ResponseEntity;

public class RestHelloWorldPresenter implements HelloWorldPresenter {
    private ResponseEntity<String> response;

    public RestHelloWorldPresenter() {
    }


    public ResponseEntity<String> presentation() {
        return response;
    }

    @Override
    public void present(HelloWorldDocument helloWorldDocument) {
        response = ResponseEntity.ok(helloWorldDocument.message());
    }
}
