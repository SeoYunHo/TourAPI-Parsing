package com.yunho.sigmungo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONArray;

import com.yunho.sigmungo.support.HttpClient;
import com.yunho.sigmungo.support.MySQL;
import com.yunho.sigmungo.support.Response;

public class Parser {
	private static String url = "http://api.visitkorea.or.kr/openapi/service";
	public static void main(String[] args) {
		HttpClient client = new HttpClient("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList");
		
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("serviceKey", "OtDjVY8xop7dH5PGSkYifdyF7VDMuR0Y0kULkqi%2BeMpfI3KY2b4Quihd9EUwxHcpV6eyWZXS1FCmJu%2B1GFE68A%3D%3D");
		params.put("MobileOS", "AND");
		params.put("MobileApp", "DPDP");
		params.put("_type", "json");
		params.put("contentTypeIdcontentTypeId", "39");
		params.put("areaCode", "3");
		
		System.out.println(params.get("serviceKey"));
		
		Response resp = client.get("serviceKey=OtDjVY8xop7dH5PGSkYifdyF7VDMuR0Y0kULkqi%2BeMpfI3KY2b4Quihd9EUwxHcpV6eyWZXS1FCmJu%2B1GFE68A%3D%3D&MobileOS=AND&MobileApp=DPDP&_type=json&contentTypeId=39&areaCode=3s", null, null);
		String json=resp.getResponseBody();
		System.out.println(json);
		JSONArray items=new JSONArray(json);
		
		
		for(int i=0;i<items.length();i++){
			int content_id=(Integer) items.getJSONObject(i).get("contentid");
			MySQL.executeUpdate("INSERT INTO content_id values(?);", content_id);
		}
		
		ResultSet rs=MySQL.executeQuery("select * from content_id;", null);
		try {
			while(rs.next()){
				int content_id=rs.getInt(0);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//			if (responseBody.getInt("totalCount") != 0) {
//				JSONObject items = responseBody.getJSONObject("items");
//				JSONObject item = items.getJSONObject("item");
//
//				String creditCard = item.has("chkcreditcardfood") ? item.getString("chkcreditcardfood") : null;
//				// 신용카드 가능 여부
//
//				// String discount = item.has("discountinfofood") ?
//				// item.getString("discountinfofood") : null;
//				/*
//				 * 할인정보 모두 null
//				 */
//				System.out.println("asdfsaddf");
//
//				String repMenu = item.has("firstmenu") ? item.getString("firstmenu") : null;
//				// 대표메뉴
//
//				String infoCenter = item.has("infocenterfood") ? item.getString("infocenterfood") : null;
//				// 문의 및 안내
//
//				int kidsFacilityInt = item.has("kidsfacility") ? item.getInt("kidsfacility") : null;
//				boolean kidsFacility = kidsFacilityInt == 1 ? true : false;
//				// 어린이 놀이방
//
//				String openDate = item.has("opendatefood") ? item.getString("opendatefood") : null;
//				// 개업일
//
//				String useTime = item.has("opentimefood") ? item.getString("opentimefood") : null;
//				// 영업시간
//
//				String packing = item.has("packing") ? item.getString("packing") : null;
//				// 포장 가능 여부
//
//				String parking = item.has("parkingfood") ? item.getString("parkingfood") : null;
//				// 주차시설
//
//				String reservation = item.has("reservationfood") ? item.getString("reservationfood") : null;
//				// 예약안내
//
//				String restDate = item.has("restdatefood") ? item.getString("restdatefood") : null;
//				// 쉬는날
//
//				String scale = item.has("scalefood") ? item.getString("scalefood") : null;
//				// 규모
//
//				String seat = item.has("seat") ? item.getString("seat") : null;
//				// 좌석수
//
//				String smoking = item.has("smoking") ? item.getString("smoking") : null;
//				// 금연/흡연 여부
//
//				String treatMenu = item.has("treatmenu") ? item.getString("treatmenu") : null;
//				// 취급 메뉴
//				System.out.println(treatMenu);
//			} else {
//				System.out.println("asdffds");
//			}
//		} catch (Exception e) {
//			e.getMessage();
//		}

	}
}
