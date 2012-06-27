package emenu.client.menu.db.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

public class TiGiaDTO {
    public static final String TABLE_NAME = "TiGia";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_TI_GIA = "MaTiGia";
    public static final String CL_KI_HIEU = "KiHieu";
    public static final String CL_GIA_TRI = "GiaTri";
    public static final String CL_CAP_NHAT = "ThoiDiemCapNhat";

    private Integer mId;
    private Integer mMaTiGia;
    private String mKiHieu;
    private Float mGiaTri;
    private Date mThoiDiemCapNhat;
    
    public static List<TiGiaDTO> fromArrayCursor(Cursor cursor) {
        List<TiGiaDTO> list = new ArrayList<TiGiaDTO>();
        
        while (cursor.moveToNext()) {
            TiGiaDTO obj = TiGiaDTO.fromCursor(cursor);
            list.add(obj);
        }
        
        return list;
    }

    public static final TiGiaDTO fromCursor(Cursor cursor) {
        TiGiaDTO obj = new TiGiaDTO();
        Integer i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_TI_GIA)) != -1) {
            obj.mMaTiGia = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_KI_HIEU)) != -1) {
            obj.mKiHieu = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_GIA_TRI)) != -1) {
            obj.mGiaTri = cursor.getFloat(i);
        }
        if ((i = cursor.getColumnIndex(CL_CAP_NHAT)) != -1) {
//            obj.mThoiDiemCapNhat = cursor.getString(i);
        }

        return obj;
    }

    public static ContentValues toContentValues(JSONObject jsonObj) throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_TI_GIA)) {
            values.put(CL_MA_TI_GIA, jsonObj.getInt(CL_MA_TI_GIA));
        }
        if (!jsonObj.isNull(CL_KI_HIEU)) {
            values.put(CL_KI_HIEU, jsonObj.getString(CL_KI_HIEU));
        }
        if (!jsonObj.isNull(CL_GIA_TRI)) {
            values.put(CL_GIA_TRI, jsonObj.getDouble(CL_GIA_TRI));
        }
        if (!jsonObj.isNull(CL_CAP_NHAT)) {
            values.put(CL_CAP_NHAT, jsonObj.getString(CL_CAP_NHAT));
        }
        return values;
    }
    
    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaTiGia() {
        return mMaTiGia;
    }

    public void setMaTiGia(Integer maTiGia) {
        mMaTiGia = maTiGia;
    }

    public Float getGiaTri() {
        return mGiaTri;
    }

    public void setGiaTri(Float giaTri) {
        mGiaTri = giaTri;
    }

    public Date getThoiDiemCapNhat() {
        return mThoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(Date thoiDiemCapNhat) {
        mThoiDiemCapNhat = thoiDiemCapNhat;
    }
    public String getKiHieu() {
        return mKiHieu;
    }

    public void setKiHieu(String kiHieu) {
        mKiHieu = kiHieu;
    }

}
