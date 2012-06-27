package emenu.client.db.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class DonViTinhDTO {
    public static final String TABLE_NAME = "DonViTinh";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_DON_VI_TINH = "MaDonViTinh";

    private Integer mId;
    private Integer mMaDonViTinh;

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_DON_VI_TINH)) {
            values.put(CL_MA_DON_VI_TINH, jsonObj.getInt(CL_MA_DON_VI_TINH));
        }
        return values;
    }
    
    public static List<DonViTinhDTO> fromArrayCursor(Cursor cursor) {
        List<DonViTinhDTO> list = new ArrayList<DonViTinhDTO>();
        
        while (cursor.moveToNext()) {
            DonViTinhDTO obj = DonViTinhDTO.fromCursor(cursor);
            list.add(obj);
        }
        
        return list;
    }

    private static DonViTinhDTO fromCursor(Cursor cursor) {
        DonViTinhDTO obj = new DonViTinhDTO();
        int i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_DON_VI_TINH)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        
        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaDonViTinh() {
        return mMaDonViTinh;
    }

    public void setMaDonViTinh(Integer maDonViTinh) {
        mMaDonViTinh = maDonViTinh;
    }
}
