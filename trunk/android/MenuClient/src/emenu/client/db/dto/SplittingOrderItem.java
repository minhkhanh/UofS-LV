package emenu.client.db.dto;

import org.json.JSONException;
import org.json.JSONObject;

public class SplittingOrderItem {
    public static final String COL_ITEM_ID = "ItemId";
    public static final String COL_SPLIT_QUANTITY = "QuantityToSplit";
    
    public Integer ItemId;
    public int QuantityToSplit;
    
    public JSONObject toJson() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(COL_ITEM_ID, ItemId);
        jsonObject.put(COL_SPLIT_QUANTITY, QuantityToSplit);
        
        return jsonObject;
    }
}
