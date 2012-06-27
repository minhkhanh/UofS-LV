package emenu.client.db.dto;

import java.sql.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;

public class VoucherDTO {
    public static final String TABLE_NAME = "Voucher";
    public static final String CL_MA_VOUCHER = "MaVoucher";
    public static final String CL_TEN_VOUCHER = "TenVoucher";
    public static final String CL_MO_TA = "MoTaVoucher";
    public static final String CL_MUC_GIA = "MucGiaApDung";
    public static final String CL_GIA_GIAM = "GiaGiam";
    public static final String CL_BAT_DAU = "BatDau";
    public static final String CL_KET_THUC = "KetThuc";

    public static final String CL_EX_VOUCHER_CODE = "VoucherCode";

    private Integer mMaVoucher;
    private String mTenVoucher;
    private String mMoTaVoucher;
    private Float mMucGiaApDung;
    private Float mGiaGiam;
    private Date mBatDau;
    private Date mKetThuc;

    public ContentValues toContentValues() {
        ContentValues v = new ContentValues();
        // v.put(CL_KET_THUC, mKetThuc.toString());
        // v.put(CL_BAT_DAU, mBatDau.toString());
        v.put(CL_GIA_GIAM, mGiaGiam);
        v.put(CL_MA_VOUCHER, mMaVoucher);
        v.put(CL_MO_TA, mMoTaVoucher);
        v.put(CL_MUC_GIA, mMucGiaApDung);
        v.put(CL_TEN_VOUCHER, mTenVoucher);

        return v;
    }

    public static VoucherDTO fromJson(JSONObject jsonObj) throws JSONException {
        VoucherDTO obj = new VoucherDTO();
        // if (!jsonObj.isNull(CL_BAT_DAU))
        // OBJ.MBATDAU = JSONOBJ.GETSTRING(CL_BAT_DAU);
        // if (!jsonObj.isNull(CL_KET_THUC))
        // obj.mKetThuc = jsonObj.getString(CL_KET_THUC);
        if (!jsonObj.isNull(CL_GIA_GIAM))
            obj.mGiaGiam = (float) jsonObj.getDouble(CL_GIA_GIAM);
        if (!jsonObj.isNull(CL_MA_VOUCHER))
            obj.mMaVoucher = jsonObj.getInt(CL_MA_VOUCHER);
        if (!jsonObj.isNull(CL_MO_TA))
            obj.mMoTaVoucher = jsonObj.getString(CL_MO_TA);
        if (!jsonObj.isNull(CL_MUC_GIA))
            obj.mMucGiaApDung = (float) jsonObj.getDouble(CL_MUC_GIA);
        if (!jsonObj.isNull(CL_TEN_VOUCHER))
            obj.mTenVoucher = jsonObj.getString(CL_TEN_VOUCHER);

        return obj;
    }

    public Integer getMaVoucher() {
        return mMaVoucher;
    }

    public void setMaVoucher(Integer maVoucher) {
        mMaVoucher = maVoucher;
    }

    public String getTenVoucher() {
        return mTenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        mTenVoucher = tenVoucher;
    }

    public String getMoTaVoucher() {
        return mMoTaVoucher;
    }

    public void setMoTaVoucher(String moTaVoucher) {
        mMoTaVoucher = moTaVoucher;
    }

    public Float getMucGiaApDung() {
        return mMucGiaApDung;
    }

    public void setMucGiaApDung(Float mucGiaApDung) {
        mMucGiaApDung = mucGiaApDung;
    }

    public Float getGiaGiam() {
        return mGiaGiam;
    }

    public void setGiaGiam(Float giaGiam) {
        mGiaGiam = giaGiam;
    }

    public Date getBatDau() {
        return mBatDau;
    }

    public void setBatDau(Date batDau) {
        mBatDau = batDau;
    }

    public Date getKetThuc() {
        return mKetThuc;
    }

    public void setKetThuc(Date ketThuc) {
        mKetThuc = ketThuc;
    }
}
