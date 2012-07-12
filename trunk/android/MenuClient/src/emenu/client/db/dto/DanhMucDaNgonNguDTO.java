package emenu.client.db.dto;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

public class DanhMucDaNgonNguDTO implements Parcelable {
    public static final String TABLE_NAME = "ChiTietDanhMucDaNgonNgu";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_DANH_MUC = "MaDanhMuc";
    public static final String CL_MA_NGON_NGU = "MaNgonNgu";
    public static final String CL_TEN_DANH_MUC = "TenDanhMuc";
    public static final String CL_MO_TA_DANH_MUC = "MoTaDanhMuc";

    public static final String CL_ID_QN = TABLE_NAME + "." + BaseColumns._ID;
    public static final String CL_MA_DANH_MUC_QN = TABLE_NAME + ".MaDanhMuc";
    public static final String CL_MA_NGON_NGU_QN = TABLE_NAME + "." + "MaNgonNgu";
    public static final String CL_TEN_DANH_MUC_QN = TABLE_NAME + "." + "TenDanhMuc";
    public static final String CL_MO_TA_DANH_MUC_QN = TABLE_NAME + "." + "MoTaDanhMuc";

    public static final String[] allColumns() {
        return new String[] { CL_ID_QN, CL_MA_DANH_MUC_QN, CL_MA_NGON_NGU_QN,
                CL_TEN_DANH_MUC_QN, CL_MO_TA_DANH_MUC_QN };
    }

    private Integer mId;
    private Integer mMaDanhMuc;
    private Integer mMaNgonNgu;
    private String mTenDanhMuc;
    private String mMoTaDanhMuc;

    public static final ContentValues toContentValues(JSONObject jsonObj)
            throws JSONException {
        ContentValues values = new ContentValues();
        if (!jsonObj.isNull(CL_MA_DANH_MUC)) {
            values.put(CL_MA_DANH_MUC, jsonObj.getInt(CL_MA_DANH_MUC));
        }
        if (!jsonObj.isNull(CL_MA_NGON_NGU)) {
            values.put(CL_MA_NGON_NGU, jsonObj.getInt(CL_MA_NGON_NGU));
        }
        if (!jsonObj.isNull(CL_TEN_DANH_MUC)) {
            values.put(CL_TEN_DANH_MUC, jsonObj.getString(CL_TEN_DANH_MUC));
        }
        if (!jsonObj.isNull(CL_MO_TA_DANH_MUC)) {
            values.put(CL_MO_TA_DANH_MUC, jsonObj.getString(CL_MO_TA_DANH_MUC));
        }

        return values;
    }

    public static final List<DanhMucDaNgonNguDTO> fromArrayCursor(Cursor cursor) {
        List<DanhMucDaNgonNguDTO> list = new ArrayList<DanhMucDaNgonNguDTO>();

        while (cursor.moveToNext()) {
            DanhMucDaNgonNguDTO obj = DanhMucDaNgonNguDTO.fromCursor(cursor);
            list.add(obj);
        }

        return list;
    }

    public static final DanhMucDaNgonNguDTO fromCursor(Cursor cursor) {
        DanhMucDaNgonNguDTO obj = new DanhMucDaNgonNguDTO();
        int i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_DANH_MUC)) != -1) {
            obj.mMaDanhMuc = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_NGON_NGU)) != -1) {
            obj.mMaNgonNgu = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_DANH_MUC)) != -1) {
            obj.mTenDanhMuc = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_MO_TA_DANH_MUC)) != -1) {
            obj.mMoTaDanhMuc = cursor.getString(i);
        }

        return obj;
    }
    
    public DanhMucDaNgonNguDTO() {
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaDanhMuc() {
        return mMaDanhMuc;
    }

    public void setMaDanhMuc(Integer maDanhMuc) {
        this.mMaDanhMuc = maDanhMuc;
    }

    public Integer getMaNgonNgu() {
        return mMaNgonNgu;
    }

    public void setMaNgonNgu(Integer maNgonNgu) {
        this.mMaNgonNgu = maNgonNgu;
    }

    public String getTenDanhMuc() {
        return mTenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.mTenDanhMuc = tenDanhMuc;
    }

    public String getMoTaDanhMuc() {
        return mMoTaDanhMuc;
    }

    public void setMoTaDanhMuc(String moTaDanhMuc) {
        this.mMoTaDanhMuc = moTaDanhMuc;
    }

    @Override
    public String toString() {
        return getTenDanhMuc();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mMaDanhMuc);
        dest.writeInt(mMaNgonNgu);
        dest.writeString(mMoTaDanhMuc);
        dest.writeString(mTenDanhMuc);
    }

    public static final Parcelable.Creator<DanhMucDaNgonNguDTO> CREATOR = new Parcelable.Creator<DanhMucDaNgonNguDTO>() {
        public DanhMucDaNgonNguDTO createFromParcel(Parcel in) {
            return new DanhMucDaNgonNguDTO(in);
        }

        public DanhMucDaNgonNguDTO[] newArray(int size) {
            return new DanhMucDaNgonNguDTO[size];
        }
    };

    private DanhMucDaNgonNguDTO(Parcel in) {
        mId = in.readInt();
        mMaDanhMuc = in.readInt();
        mMaNgonNgu = in.readInt();
        mMoTaDanhMuc = in.readString();
        mTenDanhMuc = in.readString();
    }
}
