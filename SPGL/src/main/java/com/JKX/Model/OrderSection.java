package com.JKX.Model;

import java.sql.SQLDataException;
import java.sql.SQLException;

public class OrderSection {
    private Staff sta;

    public OrderSection(Staff s) throws SQLException
    {
        this.sta = s;
        if(!sta.Login())
            throw new SQLException();
    }

    public String[][] unpaidSearch() throws SQLException
    {
        return sta.Search("select * from unpaid");
    }

}
