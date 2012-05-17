package client.menu.db.dto;

import client.menu.db.contract.MonAnDaNgonNguContract;
import android.database.Cursor;

public class MonAnDaNgonNguDTO {

    Integer mId;
    Integer mMaMonAn;
    Integer mMaNgonNgu;
    String mTenMonAn;
    String mMoTaMonAn;

    public static MonAnDaNgonNguDTO extractFrom(Cursor cursor) {
        MonAnDaNgonNguDTO obj = new MonAnDaNgonNguDTO();

        int index;
        if ((index = cursor.getColumnIndex(MonAnDaNgonNguContract.CL_ID)) != -1) {
            obj.mId = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(MonAnDaNgonNguContract.CL_MA_MON)) != -1) {
            obj.mMaMonAn = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(MonAnDaNgonNguContract.CL_MA_NGON_NGU)) != -1) {
            obj.mMaNgonNgu = cursor.getInt(index);
        }
        if ((index = cursor.getColumnIndex(MonAnDaNgonNguContract.CL_TEN_MON)) != -1) {
            obj.mTenMonAn = cursor.getString(index);
        }
        if ((index = cursor.getColumnIndex(MonAnDaNgonNguContract.CL_MO_TA_MON)) != -1) {
            obj.mMoTaMonAn = cursor.getString(index);
        }

        return obj;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public Integer getMaMonAn() {
        return mMaMonAn;
    }

    public void setMaMonAn(Integer maMonAn) {
        this.mMaMonAn = maMonAn;
    }

    public Integer getMaNgonNgu() {
        return mMaNgonNgu;
    }

    public void setMaNgonNgu(Integer maNgonNgu) {
        this.mMaNgonNgu = maNgonNgu;
    }

    public String getTenMonAn() {
        return mTenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.mTenMonAn = tenMonAn;
    }

    public String getMoTaMonAn() {
        return mMoTaMonAn;
    }

    public void setMoTaMonAn(String moTaMonAn) {
        this.mMoTaMonAn = moTaMonAn;
    }

}
