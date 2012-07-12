package emenu.client.bus.task;

import java.util.ArrayList;
import java.util.List;

import emenu.client.dao.DanhMucDAO;
import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.menu.app.MenuApplication;
import emenu.client.menu.fragment.CategoryListFragment;
import emenu.client.menu.fragment.CategoryListFragment.CategoryNode;

public class RestoreCategoryTreeTask extends
        CustomAsyncTask<Void, Void, List<CategoryListFragment.CategoryNode>> {

    private List<CategoryNode> mTree;
    private Integer mLangId = MenuApplication.getInstance().customerLocale.getLangId();

    public RestoreCategoryTreeTask(List<CategoryNode> tree) {
        mTree = tree;
    }

    @Override
    protected List<CategoryNode> doInBackground(Void... params) {
        try {
            List<CategoryNode> result = new ArrayList<CategoryListFragment.CategoryNode>();
            for (CategoryNode node : mTree) {
                DanhMucDaNgonNguDTO category = DanhMucDAO.getInstance().objByCategoryId(
                        node.danhMuc.getMaDanhMuc(), mLangId);

                node.danhMuc = category;
                result.add(node);
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<CategoryListFragment.CategoryNode>();
        }
    }
}
