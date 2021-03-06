package io.xujiaji.hnbc.fragment;

import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import io.xujiaji.hnbc.R;
import io.xujiaji.hnbc.activity.MainActivity;
import io.xujiaji.hnbc.adapter.MainBottomRecyclerAdapter;
import io.xujiaji.hnbc.config.C;
import io.xujiaji.hnbc.contract.CollectContract;
import io.xujiaji.hnbc.fragment.base.BaseRefreshFragment;
import io.xujiaji.hnbc.model.entity.Post;
import io.xujiaji.hnbc.presenter.CollectPresenter;
import io.xujiaji.hnbc.utils.ActivityUtils;

/**
 * Created by jiana on 16-11-20.
 * 收藏
 */

public class CollectFragment extends BaseRefreshFragment<Post, CollectPresenter> implements CollectContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.mainBottomRecycler)
    RecyclerView mainBottomRecycler;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout swipeLayout;

    public static CollectFragment newInstance() {
        return new CollectFragment();
    }

    @Override
    protected void onInit() {
        super.onInit();
        swipeLayout.setRefreshing(true);
    }

    @Override
    protected BaseQuickAdapter<Post, BaseViewHolder> getAdapter() {
        return new MainBottomRecyclerAdapter();
    }

    @Override
    protected SwipeRefreshLayout getSwipeLayout() {
        return swipeLayout;
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return mainBottomRecycler;
    }

    @Override
    protected void onListener() {
        super.onListener();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBack();
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }

    @Override
    public boolean clickBack() {
        if (super.clickBack()) {
            return true;
        }
        setDeleted(true);
        if (presenter.isMe()) {
            MainActivity.clickMenuItem(C.M_Menu.HOME);
        } else {
            MainActivity.startFragment(C.fragment.USER_INFO);
        }
        return true;
    }

    @Override
    public void showTitle(@StringRes int title) {
        ActivityUtils.initBar(toolbar, title);//初始化标题
    }
}
