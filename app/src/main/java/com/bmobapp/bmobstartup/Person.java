package com.bmobapp.bmobstartup;

import cn.bmob.v3.BmobObject;

public class Person extends BmobObject {
    //姓名
    String name;
    //地址
    String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
