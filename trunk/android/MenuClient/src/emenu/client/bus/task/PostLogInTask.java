package emenu.client.bus.task;

import emenu.client.dao.TaiKhoanDAO;

public class PostLogInTask extends CustomAsyncTask<Void, Void, Boolean> {

    private String mName;
    private String mPass;

    public PostLogInTask(String name, String pass) {
        mName = name;
        mPass = pass;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            return TaiKhoanDAO.getInstance().postLogIn(mName, mPass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
