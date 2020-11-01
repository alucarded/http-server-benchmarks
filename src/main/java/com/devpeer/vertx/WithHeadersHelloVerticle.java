package com.devpeer.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;

import java.time.LocalDateTime;

public class WithHeadersHelloVerticle extends AbstractVerticle {
  private HttpServer server;

  public void start(Promise<Void> startPromise) {
    server = vertx.createHttpServer().requestHandler(req -> {
      // Same as Spring headers
//      HTTP/1.1 200
//      Content-Type: text/html;charset=UTF-8
//      Content-Length: 13
//      Date: Sun, 01 Nov 2020 00:11:04 GMT
//      Keep-Alive: timeout=60
//      Connection: keep-alive

      req.response()
          .putHeader("Content-Type", "text/html;charset=UTF-8")
          .putHeader("Content-Length", "13")
          .putHeader("Date", LocalDateTime.now().toString())
          .putHeader("Keep-Alive", "timeout=60")
          .putHeader("Connection", "keep-alive")
          .end("Hello Vert.x!");
    });

    server.listen(8080, res -> {
      if (res.succeeded()) {
        startPromise.complete();
      } else {
        startPromise.fail(res.cause());
      }
    });
  }

  public void stop(Promise<Void> stopPromise) {
    server.close(stopPromise);
  }
}
