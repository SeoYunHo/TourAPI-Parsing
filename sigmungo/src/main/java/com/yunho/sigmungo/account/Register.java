package com.yunho.sigmungo.account;

import java.sql.SQLException;

import com.yunho.sigmungo.support.MySQL;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

public class Register implements Handler<RoutingContext> {
	@Override
	public void handle(RoutingContext ctx) {
		String id = ctx.request().getFormAttribute("id");
		String password = ctx.request().getFormAttribute("password");

		JsonObject jo = new JsonObject();

		System.out.println(id + " " + password);
		try {
			int result = MySQL.executeUpdate("insert into account values('" + id + "','" + password + "');");

			if (result == 1) {
				jo.put("success", true);
				ctx.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(200)
						.end(jo.toString());
				ctx.response().close();
			} else {
				jo.put("success", false);
				ctx.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
						.end(jo.toString());
				ctx.response().close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			jo.put("error", true);
		}
	}
}