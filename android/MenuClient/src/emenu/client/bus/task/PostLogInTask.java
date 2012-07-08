package emenu.client.bus.task;

import org.apache.http.client.HttpClient;

import emenu.client.dao.TaiKhoanDAO;

public class PostLogInTask extends CustomAsyncTask<Void, Void, HttpClient> {

    private String mName;
    private String mPass;

    public PostLogInTask(String name, String pass) {
        mName = name;
        mPass = pass;
    }

    @Override
    protected HttpClient doInBackground(Void... params) {
        try {
            return TaiKhoanDAO.getInstance().postLogIn(mName, mPass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
