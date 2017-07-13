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
			ResultSet rs= MySQL.executeQuery("select * from restaurant where areaCode='"+areaCode+"');");
			while(rs.next()){
				String title=rs.getString("title");
				String addr=rs.getString("addr");
				String chkcreditcardfood=rs.getString("chkcreditcardfood");
				String smoking=rs.getString("smoking");
				String restdatefood=rs.getString("restdatefood");
				String reservationfood=rs.getString("reservationfood");
				String parkingfood=rs.getString("parkingfood");
				String opentimefood=rs.getString("opentimefood");
				String kidsfacility=rs.getString("kidsfacility");
				String infocenterfood=rs.getString("infocenterfood");
			}
		}catch(SQLException e){
			e.getMessage();
		}
	}

}
