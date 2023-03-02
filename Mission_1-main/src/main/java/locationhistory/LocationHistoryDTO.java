package locationhistory;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class LocationHistoryDTO {
    private String id;
    private String lat;
    private String lnt;
    private String time_log;



}
