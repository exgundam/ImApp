package com.kk2.user.core;


import com.kk2.user.BuildConfig;

/**
 * Created on 17-8-1
 *
 * @author liuhuijie
 */

public interface AppConfig {

    String API_HOST = BuildConfig.API_HOST;

    String API_HOST_WEB = BuildConfig.API_HOST+"hello";

    String APP_LOGO="https://i1.whymtj.com/uploads/tu/201905/9999/c0a02826ba.jpg";

    String APP_LOG_TAG = "MY_APP";

    String APP_SP_NAME = "a_huo_share_data";

}
