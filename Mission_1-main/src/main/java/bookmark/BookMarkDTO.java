package bookmark;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookMarkDTO {
    private String BOOKMARK_ID;

    private String BOOKMARK_GROUP_ID;
    private String BOOKMARK_GROUP_NM;
    private String BOOKMARK_WIFI_NM;
    private String X_SWIFI_MGR_NO;
    private String BOOKMARK_CD;

}
