package com.zihler.sample.adapters.repositories.in_memory;

import com.zihler.sample.application.usecases.helloworld.outbound_ports.HelloWorldRepository;

import java.util.Map;

public class InMemoryHelloWorldRepository implements HelloWorldRepository {
    private final Map<String, String> values;

    public InMemoryHelloWorldRepository() {
        this.values = Map.of(
                "default", "Hello World!"
        );
    }

    @Override
    public String fetchDefaultValue() {
        return this.values.get("default");
    }

}
