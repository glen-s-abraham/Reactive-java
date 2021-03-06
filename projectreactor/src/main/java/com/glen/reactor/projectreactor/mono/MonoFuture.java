package com.glen.reactor.projectreactor.mono;

import java.util.concurrent.CompletableFuture;

import com.glen.reactor.projectreactor.utils.Utils;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFuture{

    public static void main(String[] args) {

        Mono<String> mono = Mono.fromFuture(getName());
        mono.subscribe(Utils.onNext(),Utils.onError(),Utils.onComplete());
        Utils.sleepSeconds(1);

    }

    private static CompletableFuture<String> getName(){
        return CompletableFuture.supplyAsync(() -> Utils.faker().name().fullName());
    }


}

