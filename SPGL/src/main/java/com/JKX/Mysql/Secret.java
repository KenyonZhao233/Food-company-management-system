package com.JKX.Mysql;




public class Secret {
    public static String encode(String value, char secret)
    {
        byte[] bt=value.getBytes();
        for(int i=0;i<bt.length;i++)
        {
            bt[i]=(byte)(bt[i]^(int)secret);
        }
        String newresult=new String(bt,0,bt.length);
        return newresult;
    }

}
