package emenu.client.menu.app;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import emenu.client.dao.AbstractDAO;
import emenu.client.db.dto.TaiKhoanDTO;

public class AuthenticationManager {
    private static AuthenticationManager mInstance;

    public static final AuthenticationManager getInstance() {
        if (mInstance == null)
            mInstance = new AuthenticationManager();

        return mInstance;
    }

    private BasicClientCookie mAccountCookie = new BasicClientCookie(
            TaiKhoanDTO.CL_TEN_TAI_KHOAN, "");
    private BasicClientCookie mPasswordCookie = new BasicClientCookie(
            TaiKhoanDTO.CL_MAT_KHAU, "");

    private CookieStore mCookieStore;
//    private boolean mExpired;

    public AuthenticationManager() {
        initCookies();
//        setExpired(true);
    }

    private void initCookies() {
        mAccountCookie.setDomain(AbstractDAO.getServerDomain());
        mAccountCookie.setPath("/");

        mPasswordCookie.setDomain(AbstractDAO.getServerDomain());
        mPasswordCookie.setPath("/");
    }

//    public void setExpired(boolean expired) {
//        mExpired = expired;
//    }
//
//    public boolean isExpired() {
//        return mExpired;
//    }

    public CookieStore getCookieStore() {
//        if (mExpired)
//            return null;

        return mCookieStore;
    }

    public void setCookieStore(CookieStore cookieStore) {
        mCookieStore = cookieStore;

        mCookieStore.addCookie(mAccountCookie);
        mCookieStore.addCookie(mPasswordCookie);
    }
}
