package com.yunho.sigmungo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void init(Vertx vertx, Context context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void start() {
		HttpServer server = vertx.createHttpServer();

		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
		router.route(HttpMethod.POST,"/account/register").handler(null);
		router.route(HttpMethod.POST,"/account/login").handler(null);
		router.route(HttpMethod.POST,"/account/logout").handler(null);
		router.route(HttpMethod.GET,"/restaurant/:areaCode").handler(null);
		router.route(HttpMethod.GET,"/post").handler(null);
		router.route(HttpMethod.POST,"/post").handler(null);
		router.route(HttpMethod.DELETE,"/post").handler(null);
		router.route(HttpMethod.PUT,"/post").handler(null);
		router.route(HttpMethod.POST,"/post").handler(null);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

}
