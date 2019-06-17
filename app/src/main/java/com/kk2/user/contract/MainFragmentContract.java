package com.kk2.user.contract;

import android.content.Context;

import com.kk2.user.base.BaseView;


/**
 * Created by ahuo on 17-9-19.
 */

public interface MainFragmentContract {

    interface MainFragmentView extends BaseView {

    }

    interface MainFragmentBiz {

        void getUsers(Context context);

    }

}
