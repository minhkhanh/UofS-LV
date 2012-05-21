package client.menu.db.dto;

import android.database.Cursor;
import android.provider.BaseColumns;

public class KhuVucDTO {
    public static final String TABLE_NAME = "KhuVuc";

    public static final String CL_ID = BaseColumns._ID;
    public static final String CL_MA_KHU_VUC = "MaKhuVuc";
    public static final String CL_TEN_KHU_VUC = "TenKhuVuc";
    public static final String CL_MO_TA = "MoTa";
    
    int mId;
    int mMaKhuVuc;
    String mTenKhuVuc;
    String mMoTa;

    public static final KhuVucDTO extractFrom(Cursor cursor) {
        KhuVucDTO obj = new KhuVucDTO();
        int i;
        if ((i = cursor.getColumnIndex(CL_ID)) != -1) {
            obj.mId = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_MA_KHU_VUC)) != -1) {
            obj.mMaKhuVuc = cursor.getInt(i);
        }
        if ((i = cursor.getColumnIndex(CL_TEN_KHU_VUC)) != -1) {
            obj.mTenKhuVuc = cursor.getString(i);
        }
        if ((i = cursor.getColumnIndex(CL_MO_TA)) != -1) {
            obj.mMoTa = cursor.getString(i);
        }

        return obj;
    }

    public int getMaKhuVuc() {
        return mMaKhuVuc;
    }

    public void setMaKhuVuc(int maKhuVuc) {
        this.mMaKhuVuc = maKhuVuc;
    }

    public String getTenKhuVuc() {
        return mTenKhuVuc;
    }

    public void setTenKhuVuc(String tenKhuVuc) {
        this.mTenKhuVuc = tenKhuVuc;
    }

    public String getMoTa() {
        return mMoTa;
    }

    public void setMoTa(String moTa) {
        this.mMoTa = moTa;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    @Override
    public String toString() {
        return getTenKhuVuc();
    }
}
