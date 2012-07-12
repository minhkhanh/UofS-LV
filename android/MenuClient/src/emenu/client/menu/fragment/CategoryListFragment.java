package emenu.client.menu.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;
import emenu.client.bus.loader.RootCategoryListLoader;
import emenu.client.bus.task.CustomAsyncTask;
import emenu.client.bus.task.CustomAsyncTask.OnPostExecuteListener;
import emenu.client.bus.task.LoadChildCategoryListTask;
import emenu.client.bus.task.RestoreCategoryTreeTask;
import emenu.client.db.dto.DanhMucDTO;
import emenu.client.db.dto.DanhMucDaNgonNguDTO;
import emenu.client.menu.R;
import emenu.client.menu.adapter.ExpandableCategoryAdapter;
import emenu.client.menu.fragment.CategoryListFragment.CategoryNode;

public class CategoryListFragment extends ListFragment implements
        LoaderCallbacks<List<DanhMucDaNgonNguDTO>>,
        OnPostExecuteListener<Integer, Void, List<DanhMucDaNgonNguDTO>> {
    public static final String KEY_CAT_INDENT_LIST = "KEY_CAT_INDENT_LIST";
    public static final String KEY_CAT_STATE_LIST = "KEY_CAT_STATE_LIST";
    public static final String KEY_CAT_ID_LIST = "KEY_CAT_ID_LIST";

    public static final String KEY_SEL_CAT = "KEY_SEL_CAT";
    private static final String KEY_CAT_TREE = "KEY_CAT_TREE";

    private int mSelIndex = -1;
    private RestoreCategoryTreeTask mRestoreCatTreeTask;
    private List<CategoryNode> mCategoryTree;
    private ExpandableCategoryAdapter mListAdapter;

    public static class CategoryNode implements Parcelable {
        public static final int EXPANDED = 1;
        public static final int COLLAPSED = -1;
        public static final int NORMAL = 0;

        public DanhMucDaNgonNguDTO danhMuc;
        public int indent = 0;
        public int state = NORMAL;

        public CategoryNode() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(danhMuc.getMaDanhMuc());
            dest.writeInt(indent);
            dest.writeInt(state);
        }

        public static final Parcelable.Creator<CategoryNode> CREATOR = new Parcelable.Creator<CategoryNode>() {
            public CategoryNode createFromParcel(Parcel in) {
                return new CategoryNode(in);
            }

            public CategoryNode[] newArray(int size) {
                return new CategoryNode[size];
            }
        };

        private CategoryNode(Parcel in) {
            danhMuc = new DanhMucDaNgonNguDTO();
            danhMuc.setMaDanhMuc(in.readInt());
            indent = in.readInt();
            state = in.readInt();
        }
    }

    private OnPostExecuteListener<Void, Void, List<CategoryNode>> mOnPostRestoreTree = new OnPostExecuteListener<Void, Void, List<CategoryNode>>() {
        @Override
        public void onPostExecute(CustomAsyncTask<Void, Void, List<CategoryNode>> task,
                List<CategoryNode> result) {
            if (result.size() > 0) {
                mCategoryTree.clear();
                mCategoryTree.addAll(result);
                mListAdapter.setTreeData(mCategoryTree);
                mListAdapter.notifyDataSetChanged();

                if (mSelIndex != -1)
                    showDetails(mListAdapter.getItem(mSelIndex).danhMuc.getMaDanhMuc());
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCategoryTree = new ArrayList<CategoryNode>();

        if (savedInstanceState != null) {
            mSelIndex = savedInstanceState.getInt(KEY_SEL_CAT, -1);

            Parcelable[] tree = savedInstanceState.getParcelableArray(KEY_CAT_TREE);
            if (tree != null) {
                List<CategoryNode> savedTree = new ArrayList<CategoryListFragment.CategoryNode>();
                for (Parcelable p : tree) {
                    savedTree.add((CategoryNode) p);
                }

                mRestoreCatTreeTask = new RestoreCategoryTreeTask(savedTree);
                mRestoreCatTreeTask.setOnPostExecuteListener(mOnPostRestoreTree)
                        .execute();
            }
        }

        mListAdapter = new ExpandableCategoryAdapter(getActivity(),
                new ArrayList<CategoryNode>());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter(mListAdapter);
        getListView().setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        if (mSelIndex == -1) {
            getLoaderManager().initLoader(0, null, this);
            showDetails(0);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<DanhMucDaNgonNguDTO>> loader) {
        mListAdapter.clear();
        mListAdapter.notifyDataSetChanged();

        mCategoryTree.clear();
    }

    @Override
    public void onLoadFinished(Loader<List<DanhMucDaNgonNguDTO>> loader,
            List<DanhMucDaNgonNguDTO> result) {
        mCategoryTree.clear();
        for (DanhMucDaNgonNguDTO d : result) {
            CategoryNode node = new CategoryNode();
            node.danhMuc = d;
            mCategoryTree.add(node);
        }

        mListAdapter.clear();
        mListAdapter.setTreeData(mCategoryTree);
        mListAdapter.notifyDataSetChanged();
    }

    @Override
    public Loader<List<DanhMucDaNgonNguDTO>> onCreateLoader(int id, Bundle args) {
        return new RootCategoryListLoader(getActivity());
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        mSelIndex = position;
        DanhMucDaNgonNguDTO danhMucCha = mListAdapter.getItem(position).danhMuc;
        int treePosition = mListAdapter.getTreePosition(danhMucCha.getMaDanhMuc());

        if (mListAdapter.getItem(position).state == CategoryNode.EXPANDED) {
            mListAdapter.collapse(position, treePosition);
            mListAdapter.notifyDataSetChanged();
        } else {
            if (mListAdapter.isChildrenLoaded(treePosition)) {
                mListAdapter.expand(position, treePosition);
                mListAdapter.notifyDataSetChanged();
            } else {
                LoadChildCategoryListTask task = new LoadChildCategoryListTask();
                task.setOnPostExecuteListener(CategoryListFragment.this);
                task.getExtras().putInt("position", position);
                task.getExtras().putInt("treePosition", treePosition);
                task.execute(danhMucCha.getMaDanhMuc());
            }
        }

        showDetails(danhMucCha.getMaDanhMuc());
    }

    void showDetails(Integer maDanhMuc) {
        DishListFragment dishList = (DishListFragment) getFragmentManager()
                .findFragmentById(R.id.paneDishList);

        if (dishList == null || dishList.getMaDanhMuc() == null
                || dishList.getMaDanhMuc() != maDanhMuc) {
            dishList = new DishListFragment(maDanhMuc);

            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.paneDishList, dishList);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        List<CategoryNode> completeTree = mListAdapter.createCompleteTree();
        outState.putInt(KEY_SEL_CAT, mSelIndex);
        outState.putParcelableArray(KEY_CAT_TREE,
                completeTree.toArray(new Parcelable[] {}));
    }

    @Override
    public void onPostExecute(
            CustomAsyncTask<Integer, Void, List<DanhMucDaNgonNguDTO>> task,
            List<DanhMucDaNgonNguDTO> result) {
        if (result.size() > 0) {
            Bundle ex = task.getExtras();
            int position = ex.getInt("position");
            int treePosition = ex.getInt("treePosition");
            mListAdapter.expand(position, treePosition, result);
            mListAdapter.notifyDataSetChanged();
        }
    }
}
