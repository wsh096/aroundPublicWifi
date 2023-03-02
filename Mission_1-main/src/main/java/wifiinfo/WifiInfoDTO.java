package wifiinfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WifiInfoDTO {

    private String distance;
    private String X_SWIFI_MGR_NO ;
    private String X_SWIFI_MAIN_NM;
    private  String X_SWIFI_WRDOFC;
    private  String X_SWIFI_ADRES1;
    private  String X_SWIFI_ADRES2;
    private String X_SWIFI_INSTL_FLOOR;
    private  String X_SWIFI_INSTL_MBY;
    private  String X_SWIFI_INSTL_TY;
    private  String X_SWIFI_SVC_SE;
    private  String X_SWIFI_CMCWR;
    private String X_SWIFI_CNSTC_YEAR;
    private   String X_SWIFI_INOUT_DOOR;
    private  String X_SWIFI_REMARS3;
    private  String LNT;
    private  String LAT;
    private  String WORK_DTTM ;

    public WifiInfoDTO(String x_SWIFI_MGR_NO, String x_SWIFI_MAIN_NM, String x_SWIFI_WRDOFC, String x_SWIFI_ADRES1, String x_SWIFI_ADRES2,String x_SWIFI_INSTL_FLOOR, String x_SWIFI_INSTL_MBY, String x_SWIFI_INSTL_TY, String x_SWIFI_SVC_SE, String x_SWIFI_CMCWR, String x_SWIFI_CNSTC_YEAR, String x_SWIFI_INOUT_DOOR, String x_SWIFI_REMARS3, String LNT, String LAT, String WORK_DTTM) {
        this.distance=null;
        this.X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
        this.X_SWIFI_MAIN_NM = x_SWIFI_MAIN_NM;
        this.X_SWIFI_WRDOFC = x_SWIFI_WRDOFC;
        this.X_SWIFI_ADRES1 = x_SWIFI_ADRES1;
        this.X_SWIFI_ADRES2 = x_SWIFI_ADRES2;
        this.X_SWIFI_INSTL_FLOOR =x_SWIFI_INSTL_FLOOR;
        this.X_SWIFI_INSTL_MBY = x_SWIFI_INSTL_MBY;
        this.X_SWIFI_INSTL_TY = x_SWIFI_INSTL_TY;
        this.X_SWIFI_SVC_SE = x_SWIFI_SVC_SE;
        this.X_SWIFI_CMCWR = x_SWIFI_CMCWR;
        this.X_SWIFI_CNSTC_YEAR = x_SWIFI_CNSTC_YEAR;
        this.X_SWIFI_INOUT_DOOR = x_SWIFI_INOUT_DOOR;
        this.X_SWIFI_REMARS3 = x_SWIFI_REMARS3;
        this.LNT = LNT;
        this.LAT = LAT;
        this.WORK_DTTM = WORK_DTTM;
    }
}
