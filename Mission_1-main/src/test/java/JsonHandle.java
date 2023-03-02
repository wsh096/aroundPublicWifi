import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.Arrays;


public class JsonHandle {

    public static JsonParser parser = new JsonParser();

    static String myLoc_Url="http://ip-api.com/json";
    public static double[] getMyLoc(){// 자신 lat lon 위도 경도
        double lat=0;
        double lon=0;

        try{
            String url=myLoc_Url;
            OkHttpClient client = new OkHttpClient();

            Request.Builder builder= new Request.Builder().url(url).get();
            Request request=builder.build();

            Response res = client.newCall(request).execute();
            if(res.isSuccessful()){
                ResponseBody body = res.body();
                if(body!=null){
                    String tmp= body.string();
                    System.out.println("Response:"+tmp);
                    JsonElement element = parser.parse(tmp); // json element 로 받아오기


                    lat=element.getAsJsonObject().get("lat").getAsDouble();
                    lon=element.getAsJsonObject().get("lon").getAsDouble();


                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new double[]{lat,lon};
    }

    public static void main(String[] args){
        System.out.println(Arrays.toString(getMyLoc()));  // test
    }

}
