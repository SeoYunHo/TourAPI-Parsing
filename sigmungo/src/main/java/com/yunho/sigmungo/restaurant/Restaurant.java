package com.yunho.sigmungo.restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yunho.sigmungo.support.MySQL;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class Restaurant  implements Handler<RoutingContext>{

	@Override
	public void handle(RoutingContext ctx) {
		String areaCode=ctx.request().getFormAttribute("areaCode");
		
		try{
			ResultSet rs= MySQL.executeQuery("select * from restaurant where areaCode='?'",areaCode);
			while(rs.next()){
				
			}
		}catch(SQLException e){
			e.getMessage();
		}
	}

}
