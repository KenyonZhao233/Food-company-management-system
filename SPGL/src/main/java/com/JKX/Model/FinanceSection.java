package com.JKX.Model;

import java.sql.SQLDataException;
import java.sql.SQLException;

public class FinanceSection {
    private Staff sta;

    public FinanceSection(Staff s) throws SQLException
    {
        this.sta = s;
        if(!sta.Login())
            throw new SQLException();
    }

    public String[][] Search() throws SQLException
    {
        return sta.Search("select * from finance order by finance_id DESC");
    }

    public String[][] Query(String s) throws SQLException
    {
        return sta.Search("select * from finance where finance_id like '%"+ s + "%' order by finance_id DESC");
    }

}
