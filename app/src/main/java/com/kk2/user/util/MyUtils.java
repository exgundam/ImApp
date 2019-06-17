package com.kk2.user.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;

/**
 * Created by ahuo on 17-9-20.
 */

public class MyUtils {
    //隐藏软键盘
    public static void hideSoftKeyboard(Context context, EditText editText) {
        if (editText == null || context == null) return;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    // 显示软键盘
    public static void showSoftKeyboard(Context context, EditText editText) {
        if (editText == null || context == null) return;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        if (imm == null) return;
        imm.showSoftInput(editText, InputMethodManager.SHOW_FORCED);
    }

    //解码格式
    public static String Base64DecodeUtf8(String message) {
        String result = "";
        try {
            result = new String(Base64.decode(message.getBytes("UTF-8"), Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String Base64EncodeUtf8(String message) {
        String result = "";
        try {
            result = new String(Base64.encode(message.getBytes("UTF-8"), Base64.NO_WRAP), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String Base64EncodeToString(String message){
        return Base64.encodeToString(message.getBytes(),Base64.DEFAULT);
    }

}
