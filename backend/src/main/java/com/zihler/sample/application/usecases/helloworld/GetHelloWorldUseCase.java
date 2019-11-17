package com.zihler.sample.application.usecases.helloworld;

import com.zihler.sample.application.usecases.helloworld.inbound_ports.GetHelloWorld;
import com.zihler.sample.application.usecases.helloworld.outbound_ports.HelloWorldPresenter;
import com.zihler.sample.application.usecases.helloworld.outbound_ports.HelloWorldRepository;
import com.zihler.sample.domain.values.HelloWorld;

public class GetHelloWorldUseCase implements GetHelloWorld {
    private HelloWorldRepository helloWorldRepository;

    public GetHelloWorldUseCase(HelloWorldRepository helloWorldRepository) {
        this.helloWorldRepository = helloWorldRepository;
    }

    @Override
    public void with(HelloWorldPresenter helloWorldPresenter) {
        String helloWorldMessage = helloWorldRepository.fetchDefaultValue();
        HelloWorld helloWorld = HelloWorld.from(helloWorldMessage);
        helloWorldPresenter.present(helloWorld.asDocument());
    }
}
