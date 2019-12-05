package com.zihler.wiki.adapters.presentation.rest.presenter;

import org.springframework.http.ResponseEntity;

abstract class RestPresenter<T> {
    ResponseEntity<T> response;

    public ResponseEntity<T> getResponseEntity() {
        return response;
    }

    void setOkResponse(T response) {
        this.response = ResponseEntity.ok(response);
    }
}
