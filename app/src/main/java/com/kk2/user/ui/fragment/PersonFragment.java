package com.kk2.user.ui.fragment;

import android.widget.ImageView;

import com.ahuo.tool.imageloader.GlideLoaderUtil;
import com.kk2.user.R;
import com.kk2.user.base.BaseTitleFragment;
import com.kk2.user.core.AppConfig;
import com.kk2.user.ui.widget.MyAppBar;

import butterknife.BindView;

/**
 * Created by ahuo on 17-9-19.
 */

public class PersonFragment extends BaseTitleFragment {
    @BindView(R.id.ivUserPhoto)
    ImageView mIvUserPhoto;

    @Override
    public void setPresenter() {

    }

    @Override
    public MyAppBar.TitleConfig getTitleViewConfig() {
        return buildDefaultConfig(getString(R.string.fragment_title_person));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    public void initData() {

        /*String getCircle= ReqEntity.getBuilder()
                .setMsgType(ChatMsgType.TriggerCirclePushTask)
                .setWeChatId(UserInfo.weChatId)
                .setStartTime(System.currentTimeMillis())
                .buildJsonToString();
        MyLog.e(getCircle);*/
        GlideLoaderUtil.loadNormalImage(getActivity(), AppConfig.APP_LOGO, GlideLoaderUtil.LOAD_IMAGE_DEFAULT_ID, mIvUserPhoto);
    }

    @Override
    public void refresh() {

    }


}
