package com.zihler.sample.application.usecases.helloworld.inbound_ports;

import com.zihler.sample.application.usecases.helloworld.outbound_ports.HelloWorldPresenter;

public interface GetHelloWorld {
    void with(HelloWorldPresenter helloWorldPresenter);
}
