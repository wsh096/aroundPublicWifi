package com.module;

import com.db.dao.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.TestOnly;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.db.dao.WifiDao;
import com.db.dto.WifiDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;





public class openApi {
	
	private static String openUrl ="http://openapi.seoul.go.kr:8088";
	private static String key = "4368725a6377736839305a564f4969";
	private static String filetype = "json";
	private static String service = "TbPublicWifiInfo";
	private static String datacutter = "row";
	
	
	public static int maxnum = getMaxnum(); //최대값을 바로 받아 줄 변수
	
	JdbcTemplate jt = new JdbcTemplate();
	WifiDao wifi = new WifiDao(jt);
	
	List<WifiDto> result;
	
	public void apicall() {
		int repeat = maxnum/1000;	
		System.out.println(maxnum);
		System.out.println(repeat);
		for(int i = 0; i < repeat; i++) {
		int s = i * 1000 + 1;
		int e = i* 1000 + 1000;
		result = getWifiInfoData(s, e);
		int statecheck = wifi.insertAll(result);
		if(statecheck==0) {System.out.println("에러입니다.");} else System.out.println("정상처리되었습니다");
		if(i==repeat-1) {
			result = getWifiInfoData(s + 1000, maxnum);
			wifi.insertAll(result);
			}
		}
	}
	
	public List<WifiDto> getWifiInfoData(int start, int end){
		List<WifiDto> result = new ArrayList<>();
		
		String uri = openUrl +"/"+ key +"/"+ filetype +"/"+ service +"/"+ start +"/"+ end;

		        try {
		            OkHttpClient client = new OkHttpClient();
		            Request request= new Request.Builder().url(uri).build();
		            Response response= client.newCall(request).execute();
		            String messageBodyRequest= response.body().string();

		            JSONObject jsonObj = (JSONObject)new JSONParser().parse(messageBodyRequest);//json으로 변환
		            JSONObject parse = (JSONObject)jsonObj.get(service);
					JSONArray data = (JSONArray)parse.get(datacutter);
					
					for(Object object : data) {
						JSONObject obj = (JSONObject) object;
						WifiDto wifi = new WifiDto();
						
						wifi.setMerNo((String)obj.get("X_SWIFI_MGR_NO"));
						wifi.setWifiName((String)obj.get("X_SWIFI_MAIN_NM"));
						wifi.setGu((String)obj.get("X_SWIFI_WRDOFC"));
						wifi.setArr1((String)obj.get("X_SWIFI_ADRES1"));
						wifi.setArr2((String)obj.get("X_SWIFI_ADRES2"));
						wifi.setFloor((String)obj.get("X_SWIFI_INSTL_FLOOR"));
						wifi.setInstallAgency((String)obj.get("X_SWIFI_INSTL_MBY"));
						wifi.setInstallType((String)obj.get("X_SWIFI_INSTL_TY"));
						wifi.setService((String)obj.get("X_SWIFI_SVC_SE"));
						wifi.setMangType((String)obj.get("X_SWIFI_CMCWR"));
						wifi.setInstallYear((String)obj.get("X_SWIFI_CNSTC_YEAR"));
						wifi.setWhere((String)obj.get("X_SWIFI_INOUT_DOOR"));
						wifi.setWifiState((String)obj.get("X_SWIFI_REMARS3"));
						wifi.setLat((String)obj.get("LNT"));
						wifi.setLnt((String)obj.get("LAT"));
						wifi.setInstallDate((String)obj.get("X_SWIFI_ADRES1"));
						result.add(wifi);
						
					}
		            
		        
		            
		            return result;
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        return null;
		    }
		
	

	public static int getMaxnum() {
		String uri = openUrl +"/"+ key +"/"+ filetype +"/"+ service +"/"+1+"/"+1;
		
		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder().url(uri).build();
			Response response = client.newCall(request).execute();
			String messageBodyRequest = response.body().string();
					
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(messageBodyRequest);
			JsonObject rootObject = jsonElement.getAsJsonObject();
			JsonObject childObject = rootObject.get("TbPublicWifiInfo").getAsJsonObject(); 
			
			int max = childObject.get("list_total_count").getAsInt(); 
			
			return max;
		 	} catch(Exception e) {
		 		e.printStackTrace();
		 	}
       return -1;
    }
	@TestOnly
	public static void main(String[] args) {
		JdbcTemplate jt = new JdbcTemplate();
		
		openApi oa = new openApi();
		oa.apicall();
		new WifiDao(jt).show();
	
	}
}

