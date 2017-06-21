package com.sun.biologyproject.bean;

/**
 * Created by liangyuyi on 2017/6/21.
 */
public class Adress {
    private static Adress adressInstance = new Adress();//类句柄
    private String adress;
    private Adress(){
    }

    public static Adress getAdressInstance(){
        return adressInstance;
    }

    /**
     * 设置地址的接口
     * 使用格式：Adress.getAdressInstance().setAdress(str);
     * @param adress
     */
    public void setAdress(String adress){
        this.adress = adress;
    }

    /**
     * 获取地址的接口
     * 使用格式：String str = Adress.getAdressInstance().getAdress();
     * @return
     */
    public String getAdress(){
        return adress;
    }
}
