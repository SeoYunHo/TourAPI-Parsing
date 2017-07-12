package com.yunho.sigmungo.account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.yunho.sigmungo.support.MySQL;

import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.Session;

public class Login implements Handler<RoutingContext>{

	@Override
	public void handle(RoutingContext ctx) {
		String id = ctx.request().getFormAttribute("id");
		String password = ctx.request().getFormAttribute("password");

		JsonObject jo = new JsonObject();

		Session session=ctx.session();
		try {
			ResultSet resultSet = MySQL.executeQuery(
					"select count(*) from account where id= '" + id + "' and password='" + password + "';");
			if (resultSet.next() && resultSet.getInt(1) >= 0) {
				String sessionKey;
				int count = 1;
				do {
					UUID key = UUID.randomUUID();
					sessionKey = key.toString();
					resultSet = MySQL
							.executeQuery("select count(*) from session where sessionKey='" + sessionKey + "';");
					if (resultSet.next())
						count = resultSet.getInt(1);
				} while (count != 0);
				int result = MySQL.executeUpdate(
						"insert into session values('" + sessionKey + "','" + id + "','" + password + "');");
				if (result == 1) {
					session.put("sessionKey", sessionKey);
					ctx.setSession(session);
					jo.put("success", true);
					ctx.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(201)
							.end(jo.toString());
					ctx.response().close();
				} else {
					jo.put("success", false);
					ctx.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
							.end(jo.toString());
					ctx.response().close();
				}
			} else {
				jo.put("success", false);
				ctx.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(400)
						.end(jo.toString());
				ctx.response().close();
			}
		} catch (SQLException e) {
			jo.put("success", false);
			ctx.response().putHeader("content-type", "application/json; charset=utf-8").setStatusCode(500)
					.end(jo.toString());
			ctx.response().close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
