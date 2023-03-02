/**
 * Sample url
 * http://openapi.seoul.go.kr:8088/sample/xml/TbPublicWifiInfo/1/5/
 * @return
 */
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenApi {
    static String ORIGIN ="http://openapi.seoul.go.kr:8088";
    static String SERVICE ="TbPublicWifiInfo";
    static String KEY="4362434564776c6135327572784859"; //privated key.
    static String TYPE="json";

    static String START="1";
    static String END="1000";

    public static String getResponse(){
        try {
            OkHttpClient client = new OkHttpClient();
            Request request= new Request.Builder()
                    .url(ORIGIN+"/"+KEY+"/"+TYPE+"/"+SERVICE+"/"+START+"/"+END)
                    .build();
            Response res= client.newCall(request).execute();

            String message= res.body().string();
            System.out.println(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "";
    }
    public static void main(String[] args){
        System.out.println(getResponse());
    }


}
