import io.vertx.core.Vertx;
import io.vertx.ext.web.client.WebClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.devpeer.vertx.HttpServerVerticle;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class HttpServerVerticleTest {

  public static Vertx vertx;

  @BeforeAll
  static void setup() throws InterruptedException, ExecutionException, TimeoutException {
    vertx = Vertx.vertx();
    CompletableFuture<String> completableFuture = new CompletableFuture<>();
    vertx.deployVerticle(new HttpServerVerticle(), ar -> completableFuture.complete(ar.result()));
    completableFuture.get(1, TimeUnit.MINUTES);
  }

  @Test
  public void testServer() throws InterruptedException {
    WebClient webClient = WebClient.create(vertx);

    while(true) {
      log.info("Executing");
      vertx.executeBlocking(promise -> {
        log.info("Doing HTTP GET");
        webClient.get(8080, "localhost", "/")
            .send(ar -> promise.complete(ar.result() != null ? ar.result().body() : null));
      }, false, res -> log.info("Got response: {}", res));
    }
  }

  @AfterAll
  static void tearDown() {
    vertx.close();
  }
}
