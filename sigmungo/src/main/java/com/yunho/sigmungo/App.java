package com.yunho.sigmungo;

import io.vertx.core.Vertx;

public class App {
	public static void main(String[] args) {
		Vertx vertx=Vertx.vertx();
		vertx.deployVerticle(new MainVerticle(), StringAsyncResult -> {
			System.out.println("MainVerticle deployment complete");
		});
	}
}
