package com.devpeer;

import com.devpeer.vertx.HttpServerVerticle;
import io.vertx.core.Vertx;

public class VertxMain {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HttpServerVerticle());
  }
}
