package com.zihler.sample.application.usecases.helloworld.outbound_ports;

import com.zihler.sample.domain.values.HelloWorldDocument;

public interface HelloWorldPresenter {
    void present(HelloWorldDocument helloWorldDocument);
}
