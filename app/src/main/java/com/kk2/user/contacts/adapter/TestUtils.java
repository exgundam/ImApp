package com.kk2.user.contacts.adapter;

import android.content.Context;

import com.kk2.user.R;
import com.kk2.user.contacts.search.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by you on 2017/9/11.
 */

public class TestUtils {

    public static List<Contact> contactList(Context context) {
        List<Contact> contactList = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        String[] names = {"梅艳芳","刘德华","张国荣","周星驰","brant","蔡依林","莫文蔚","刘会杰","德莱文","易建联","费玉清","高建翔"};
        for (int i = 0; i < names.length; i++) {
            int urlIndex = random.nextInt(URLS.length - 1);
            int url = URLS[urlIndex];
            contactList.add(new Contact(names[i], url));
        }
        return contactList;
    }


    static int[] URLS = {R.drawable.head_icon_1, R.drawable.head_icon_1, R.drawable.head_icon_2, R.drawable.head_icon_2};

}
