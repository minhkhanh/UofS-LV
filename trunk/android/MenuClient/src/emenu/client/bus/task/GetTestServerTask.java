package emenu.client.bus.task;

import emenu.client.util.U;

public class GetTestServerTask extends CustomAsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String url = params[0] + "testGetJson";

        try {
            return U.loadGetResponse(url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
