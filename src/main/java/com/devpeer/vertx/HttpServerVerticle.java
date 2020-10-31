package com.devpeer.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpServerVerticle extends AbstractVerticle {
  private HttpServer server;

  public void start(Promise<Void> startPromise) {
    server = vertx.createHttpServer().requestHandler(req -> {
      //log.info("Request handling {}", Thread.currentThread().getId());
      req.response()
          .putHeader("content-type", "text/plain")
          .end("Hello Vert.x!");
  });

    // Now bind the server:
    //log.info("Binding server {}", Thread.currentThread().getId());
    server.listen(8080, res -> {
      //log.info("Binding process finished {}", Thread.currentThread().getId());
      if (res.succeeded()) {
        startPromise.complete();
      } else {
        startPromise.fail(res.cause());
      }
    });
    //log.info("Binding call finished {}", Thread.currentThread().getId());
  }

  public void stop(Promise<Void> stopPromise) {
    server.close(stopPromise);
  }
}
