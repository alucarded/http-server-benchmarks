package com.devpeer;

import com.devpeer.vertx.WithHeadersHelloVerticle;
import io.vertx.core.Vertx;

public class VertxMain {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    //vertx.deployVerticle(new HelloVerticle());
    vertx.deployVerticle(new WithHeadersHelloVerticle());
  }
}
