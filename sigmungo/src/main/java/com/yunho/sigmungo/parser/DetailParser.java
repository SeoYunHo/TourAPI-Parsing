package com.yunho.sigmungo.parser;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.yunho.sigmungo.parser.support.HttpClientForParser;
import com.yunho.sigmungo.support.MySQL;

public class DetailParser implements Parser {

	private static void clearTables() {
		MySQL.executeUpdate("DELETE FROM accommodation_detail_info");
		MySQL.executeUpdate("DELETE FROM cultural_facility_detail_info");
		MySQL.executeUpdate("DELETE FROM festival_detail_info");
		MySQL.executeUpdate("DELETE FROM leports_detail_info");
		MySQL.executeUpdate("DELETE FROM restaurant_detail_info");
		MySQL.executeUpdate("DELETE FROM shopping_detail_info");
		MySQL.executeUpdate("DELETE FROM tour_course_detail_info");
		MySQL.executeUpdate("DELETE FROM tourrism_detail_info");
	}

	public void parse() {
		clearTables();

		ResultSet rs = MySQL.executeQuery("SELECT * FROM attractions_basic");
		Map<Integer, Integer> contentInfoMap = new HashMap<Integer, Integer>();
		Pattern p = Pattern.compile("\\d+");

		try {
			while (rs.next()) {
				contentInfoMap.put(rs.getInt("content_id"), rs.getInt("content_type_id"));
			}
			// To escape SQLException : Operation not allowed after ResultSet
			// closed
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int contentId : contentInfoMap.keySet()) {
			int contentTypeId = contentInfoMap.get(contentId);

			JSONObject item = HttpClientForParser
					.getItem(URL + "&contentId=" + contentId + "&contentTypeId=" + contentTypeId);
			if (item == null) {
				continue;
			} else if (contentTypeId == 39) {
				// 음식

				String creditCard = item.has("chkcreditcardfood") ? item.getString("chkcreditcardfood") : null;
				// 신용카드 가능 여부

				// String discount = item.has("discountinfofood") ?
				// item.getString("discountinfofood") : null;
				/*
				 * 할인정보 모두 null
				 */

				String repMenu = item.has("firstmenu") ? item.getString("firstmenu") : null;
				// 대표메뉴

				String infoCenter = item.has("infocenterfood") ? item.getString("infocenterfood") : null;
				// 문의 및 안내

				int kidsFacilityInt = item.has("kidsfacility") ? item.getInt("kidsfacility") : null;
				boolean kidsFacility = kidsFacilityInt == 1 ? true : false;
				// 어린이 놀이방

				String openDate = item.has("opendatefood") ? item.getString("opendatefood") : null;
				// 개업일

				String useTime = item.has("opentimefood") ? item.getString("opentimefood") : null;
				// 영업시간

				String packing = item.has("packing") ? item.getString("packing") : null;
				// 포장 가능 여부

				String parking = item.has("parkingfood") ? item.getString("parkingfood") : null;
				// 주차시설

				String reservation = item.has("reservationfood") ? item.getString("reservationfood") : null;
				// 예약안내

				String restDate = item.has("restdatefood") ? item.getString("restdatefood") : null;
				// 쉬는날

				String scale = item.has("scalefood") ? item.getString("scalefood") : null;
				// 규모

				String seat = item.has("seat") ? item.getString("seat") : null;
				// 좌석수

				String smoking = item.has("smoking") ? item.getString("smoking") : null;
				// 금연/흡연 여부

				String treatMenu = item.has("treatmenu") ? item.getString("treatmenu") : null;
				// 취급 메뉴

				MySQL.executeUpdate("INSERT INTO restaurant_detail_info VALUES(", contentId, ", '", creditCard, "', '",
						repMenu, "', '", infoCenter, "', ", kidsFacility, ", '", openDate, "', '", useTime, "', '",
						packing, "', '", parking, "', '", reservation, "', '", restDate, "', '", scale, "', '", seat,
						"', '", smoking, "', '", treatMenu, "')");
			}
		}
	}
}
