package com.JKX.Model;
import com.JKX.Model.SearchTables.*;

import java.sql.SQLException;

public class SearchSection {

    public Staff staff;

    public SearchSection(Staff staff)
    {
        this.staff=staff;
    }


    public WorkShop[] SearchWorkShopByProductId(String product_id) throws SQLException
    {
        String sql="select workshop.cj_id,workshop.cj_fzr,workshop.cj_productid,product.product_name,workshop.cj_num " +
                "from workshop,product " +
                "where workshop.cj_productid = product.product_id and workshop.cj_productid = '"+product_id+"' ";
        String ans[][] =staff.Search(sql);
        WorkShop workShops[]=new WorkShop[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            workShops[i-1]=new WorkShop(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4]);
        }
        return workShops;
    }

    public WorkShop[] SearchWorkShopByCheJianId(String cj_id) throws SQLException
    {
        String sql="select workshop.cj_id,workshop.cj_fzr,workshop.cj_productid,product.product_name,workshop.cj_num " +
                "from workshop,product " +
                "where workshop.cj_productid = product.product_id and workshop.cj_id = '"+cj_id+"' ";
        String ans[][] =staff.Search(sql);
        WorkShop workShops[]=new WorkShop[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            workShops[i-1]=new WorkShop(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4]);
        }
        return workShops;
    }

    public WorkShop[] SearchAllWorkShop() throws SQLException
    {
        String sql="select workshop.cj_id,workshop.cj_fzr,workshop.cj_productid,product.product_name,workshop.cj_num " +
                "from workshop,product " +
                "where workshop.cj_productid = product.product_id";
        String ans[][] =staff.Search(sql);
        WorkShop workShops[]=new WorkShop[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            workShops[i-1]=new WorkShop(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4]);
        }
        return workShops;
    }

    public Pick[] SearchPickByCustomId(String custom_id) throws SQLException
    {
        String sql="select pick.order_id,pick.order_type,pick.order_custom,customer.customer_name,customer.customer_tele,pick.order_date " +
                "from pick,customer " +
                "where pick.order_custom = customer.customer_id and pick.order_custom = '"+custom_id+"' ";
        String ans[][] =staff.Search(sql);
        Pick picks[]=new Pick[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            picks[i-1]=new Pick(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return picks;
    }

    public Pick[] SearchPickByOrderId(String order_id) throws SQLException
    {
        String sql="select pick.order_id,pick.order_type,pick.order_custom,customer.customer_name,customer.customer_tele,pick.order_date " +
                "from pick,customer " +
                "where pick.order_custom = customer.customer_id and pick.order_id = '"+order_id+"' ";
        String ans[][] =staff.Search(sql);
        Pick picks[]=new Pick[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            picks[i-1]=new Pick(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return picks;
    }

    public Pick[] SearchAllPick() throws SQLException
    {
        String sql="select pick.order_id,pick.order_type,pick.order_custom,customer.customer_name,customer.customer_tele,pick.order_date " +
                "from pick,customer " +
                "where pick.order_custom = customer.customer_id";
        String ans[][] =staff.Search(sql);
        Pick picks[]=new Pick[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            picks[i-1]=new Pick(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return picks;
    }

    public Product[] SearchProductByName(String product_name) throws SQLException
    {
        String sql="select * from product where product.product_name = '"+product_name+"' ";
        String ans[][] =staff.Search(sql);
        Product products[]=new Product[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            products[i-1]=new Product(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return products;
    }

    public Product[] SearchProductById(String product_id) throws SQLException
    {
        String sql="select * from product where product.product_id = '"+product_id+"' ";
        String ans[][] =staff.Search(sql);
        Product products[]=new Product[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            products[i-1]=new Product(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return products;
    }

    public Product[] SearchAllProduct() throws SQLException
    {
        String sql="select * from product";
        String ans[][] =staff.Search(sql);
        Product products[]=new Product[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            products[i-1]=new Product(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return products;
    }

    public Warehouse[] SearchAllWarehouse() throws SQLException
    {
        String sql="select * from storehouse";
        String ans[][] =staff.Search(sql);
        Warehouse warehouses[]=new Warehouse[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            warehouses[i-1]=new Warehouse(ans[i][0],ans[i][1]);
        }
        return warehouses;
    }

    public Project[] SearchProjectByProductId(String product_id) throws SQLException
    {
        String sql="select * from project where project.produce_wp = '"+product_id+"'";
        String ans[][] =staff.Search(sql);
        Project projects[]=new Project[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            projects[i-1]=new Project(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5],ans[i][6],ans[i][7],ans[i][8]);
        }
        return projects;
    }

    public Project[] SearchProjectByStartDate(String start,String end) throws SQLException
    {
        String sql="select * from project where project.produce_sdate between '"+start+"' and '"+end+"' ";
        String ans[][] =staff.Search(sql);
        Project projects[]=new Project[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            projects[i-1]=new Project(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5],ans[i][6],ans[i][7],ans[i][8]);
        }
        return projects;
    }

    public Project[] SearchAllProject() throws SQLException
    {
        String sql="select * from project";
        String ans[][] =staff.Search(sql);
        Project projects[]=new Project[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            projects[i-1]=new Project(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5],ans[i][6],ans[i][7],ans[i][8]);
        }
        return projects;
    }

    public Finance[] SearchFinanceByInfo(String info)throws SQLException
    {
        String sql="select * from finance where finance.finance_inf like '%%"+ info +"%%'";
        String ans[][] =staff.Search(sql);
        Finance finances[]=new Finance[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            finances[i-1]=new Finance(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4]);
        }
        return finances;
    }

    public Finance[] SearchFinanceByDate(String start,String end)throws SQLException
    {
        String sql="select * from finance where finance.finance_time between '"+start+"' and '"+end+"' ";
        String ans[][] =staff.Search(sql);
        Finance finances[]=new Finance[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            finances[i-1]=new Finance(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4]);
        }
        return finances;
    }

    public Finance[] SearchAllFinance()throws SQLException
    {
        String sql="select * from finance";
        String ans[][] =staff.Search(sql);
        Finance finances[]=new Finance[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            finances[i-1]=new Finance(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4]);
        }
        return finances;
    }

    public Refund[] SearchRefundByDate(String start,String end)throws SQLException
    {
        String sql="select refund.return_id,refund.order_money,refund.order_id,refund.return_date,refund.return_reason,refund.return_state,customer.customer_id,customer.customer_name,customer.customer_tele " +
                "from refund,customer,orders " +
                "where refund.order_id = orders.order_id and orders.order_custom = customer.customer_id and refund.return_date between '"+start+"' and '"+end+"' ";
        String ans[][] =staff.Search(sql);
        Refund refunds[]=new Refund[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            refunds[i-1]=new Refund(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5],ans[i][6],ans[i][7],ans[i][8]);
        }
        return refunds;
    }

    public Refund[] SearchRefundByCustomId(String custom_id)throws SQLException
    {
        String sql="select refund.return_id,refund.order_money,refund.order_id,refund.return_date,refund.return_reason,refund.return_state,customer.customer_id,customer.customer_name,customer.customer_tele " +
                "from refund,customer,orders " +
                "where refund.order_id = orders.order_id and orders.order_custom = customer.customer_id and customer.customer_id = '"+custom_id+"' ";
        String ans[][] =staff.Search(sql);
        Refund refunds[]=new Refund[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            refunds[i-1]=new Refund(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5],ans[i][6],ans[i][7],ans[i][8]);
        }
        return refunds;
    }

    public Refund[] SearchRefundByOrderId(String order_id)throws SQLException
    {
        String sql="select refund.return_id,refund.order_money,refund.order_id,refund.return_date,refund.return_reason,refund.return_state,customer.customer_id,customer.customer_name,customer.customer_tele " +
                "from refund,customer,orders " +
                "where refund.order_id = orders.order_id and orders.order_custom = customer.customer_id and refund.order_id = '"+order_id+"' ";
        String ans[][] =staff.Search(sql);
        Refund refunds[]=new Refund[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            refunds[i-1]=new Refund(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5],ans[i][6],ans[i][7],ans[i][8]);
        }
        return refunds;
    }

    public Refund[] SearchAllRefund()throws SQLException
    {
        String sql="select refund.return_id,refund.order_money,refund.order_id,refund.return_date,refund.return_reason,refund.return_state,customer.customer_id,customer.customer_name,customer.customer_tele " +
                "from refund,customer,orders " +
                "where refund.order_id = orders.order_id and orders.order_custom = customer.customer_id";
        String ans[][] =staff.Search(sql);
        Refund refunds[]=new Refund[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            refunds[i-1]=new Refund(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5],ans[i][6],ans[i][7],ans[i][8]);
        }
        return refunds;
    }

    public Unpaid[] SearchUnpaidByCustomId(String custom_id)throws SQLException
    {
        String sql="select unpaid.order_id,unpaid.order_date,unpaid.oreder_mn,customer.customer_id,customer.customer_name,customer.customer_tele " +
                "from unpaid,customer " +
                "where unpaid.order_custom = customer.customer_id and customer.customer_id = '"+custom_id+"' ";
        String ans[][] =staff.Search(sql);
        Unpaid unpaids[]=new Unpaid[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            unpaids[i-1]=new Unpaid(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return unpaids;
    }

    public Unpaid[] SearchUnpaidByOrderId(String order_id)throws SQLException
    {
        String sql="select unpaid.order_id,unpaid.order_date,unpaid.oreder_mn,customer.customer_id,customer.customer_name,customer.customer_tele " +
                "from unpaid,customer " +
                "where unpaid.order_custom = customer.customer_id and unpaid.order_id = '"+order_id+"' ";
        String ans[][] =staff.Search(sql);
        Unpaid unpaids[]=new Unpaid[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            unpaids[i-1]=new Unpaid(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return unpaids;
    }

    public Unpaid[] SearchUnpaidByDate(String start,String end)throws SQLException
    {
        String sql="select unpaid.order_id,unpaid.order_date,unpaid.oreder_mn,customer.customer_id,customer.customer_name,customer.customer_tele " +
                "from unpaid,customer " +
                "where unpaid.order_custom = customer.customer_id and unpaid.order_date between '"+start+"' and '"+end+"' ";
        String ans[][] =staff.Search(sql);
        Unpaid unpaids[]=new Unpaid[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            unpaids[i-1]=new Unpaid(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return unpaids;
    }

    public Unpaid[] SearchAllUnpaid()throws SQLException
    {
        String sql="select unpaid.order_id,unpaid.order_date,unpaid.oreder_mn,customer.customer_id,customer.customer_name,customer.customer_tele " +
                "from unpaid,customer " +
                "where unpaid.order_custom = customer.customer_id";
        String ans[][] =staff.Search(sql);
        Unpaid unpaids[]=new Unpaid[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            unpaids[i-1]=new Unpaid(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return unpaids;
    }

    public RawRecord[] SearchRawRecordById(String raw_id)throws SQLException
    {
        String sql="select raw.raw_id,raw.raw_name,raw_rec.raw_date,raw_rec.raw_lx,raw_rec.raw_num,raw_rec.raw_fzrid " +
                "from raw,raw_rec " +
                "where raw.raw_id = raw_rec.raw_id and raw.raw_id = '"+raw_id+"' ";
        String ans[][] =staff.Search(sql);
        RawRecord rawRecords[]=new RawRecord[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            rawRecords[i-1]=new RawRecord(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return rawRecords;
    }

    public RawRecord[] SearchRawRecordByDate(String start,String end)throws SQLException
    {
        String sql="select raw.raw_id,raw.raw_name,raw_rec.raw_date,raw_rec.raw_lx,raw_rec.raw_num,raw_rec.raw_fzrid " +
                "from raw,raw_rec " +
                "where raw.raw_id = raw_rec.raw_id and raw_rec.raw_date between '"+ start +"' and '"+end+"' ";
        String ans[][] =staff.Search(sql);
        RawRecord rawRecords[]=new RawRecord[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            rawRecords[i-1]=new RawRecord(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return rawRecords;
    }

    public RawRecord[] SearchAllRawRecord()throws SQLException
    {
        String sql="select raw.raw_id,raw.raw_name,raw_rec.raw_date,raw_rec.raw_lx,raw_rec.raw_num,raw_rec.raw_fzrid " +
                "from raw,raw_rec " +
                "where raw.raw_id = raw_rec.raw_id";
        String ans[][] =staff.Search(sql);
        RawRecord rawRecords[]=new RawRecord[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            rawRecords[i-1]=new RawRecord(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return rawRecords;
    }

    public RawKC[] SearchRawKCByIn(String raw_in)throws SQLException
    {
        String sql="select raw.raw_name,raw.raw_id,sum(raw_rm),raw_ck.raw_in " +
                "from raw,raw_ck " +
                "where raw.raw_id = raw_ck.raw_id and raw_ck.raw_in = '"+raw_in+"' "+
                "group by raw.raw_name,raw.raw_id,raw_ck.raw_in";
        String ans[][] =staff.Search(sql);
        RawKC raws[]=new RawKC[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            raws[i-1]=new RawKC(ans[i][0],ans[i][1],ans[i][2],ans[i][3]);
        }
        return raws;
    }

    public RawKC[] SearchRawKCById(String raw_id)throws SQLException
    {
        String sql="select raw.raw_name,raw.raw_id,sum(raw_rm),raw_ck.raw_in " +
                "from raw,raw_ck " +
                "where raw.raw_id = raw_ck.raw_id and raw.raw_id = '"+raw_id+"' "+
                "group by raw.raw_name,raw.raw_id,raw_ck.raw_in";
        String ans[][] =staff.Search(sql);
        RawKC raws[]=new RawKC[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            raws[i-1]=new RawKC(ans[i][0],ans[i][1],ans[i][2],ans[i][3]);
        }
        return raws;
    }

    public RawKC[] SearchAllRawKC()throws SQLException
    {
        String sql="select raw.raw_name,raw.raw_id,sum(raw_rm),raw_ck.raw_in " +
                "from raw,raw_ck " +
                "where raw.raw_id = raw_ck.raw_id " +
                "group by raw.raw_name,raw.raw_id,raw_ck.raw_in";
        String ans[][] =staff.Search(sql);
        RawKC raws[]=new RawKC[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            raws[i-1]=new RawKC(ans[i][0],ans[i][1],ans[i][2],ans[i][3]);
        }
        return raws;
    }

    public Raw[] SearchRawByName(String raw_name) throws SQLException
    {
        String sql="select * from raw where raw_name = '"+raw_name+"'";
        String ans[][] =staff.Search(sql);
        Raw raws[]=new Raw[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            raws[i-1]=new Raw(ans[i][0],ans[i][1],ans[i][2],ans[i][3]);
        }
        return raws;
    }

    public Raw[] SearchRawById(String raw_id) throws SQLException
    {
        String sql="select * from raw where raw_id = '"+raw_id+"'";
        String ans[][] =staff.Search(sql);
        Raw raws[]=new Raw[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            raws[i-1]=new Raw(ans[i][0],ans[i][1],ans[i][2],ans[i][3]);
        }
        return raws;
    }

    public Raw[] SearchAllRaw() throws SQLException
    {
        String sql="select * from raw";
        String ans[][] =staff.Search(sql);
        Raw raws[]=new Raw[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            raws[i-1]=new Raw(ans[i][0],ans[i][1],ans[i][2],ans[i][3]);
        }
        return raws;
    }

    public ProductKuCun[] SearchKuCunByCKID(String cangku_id) throws SQLException
    {
        String sql="select product.product_id,product.product_name,sum(product_ck.product_rm),product_ck.product_in " +
                "from product,product_ck " +
                "where product.product_id = product_ck.product_id and product_ck.product_in = '"+cangku_id+"' "+
                "group by product_id,product_in";
        String ans[][] =staff.Search(sql);
        ProductKuCun productKuCuns[]=new ProductKuCun[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            productKuCuns[i-1]=new ProductKuCun(ans[i][0],ans[i][1],ans[i][2],ans[i][3]);
        }
        return productKuCuns;
    }

    public ProductKuCun[] SearchKuCunByName(String product_name) throws SQLException
    {
        String sql="select product.product_id,product.product_name,sum(product_ck.product_rm),product_ck.product_in " +
                "from product,product_ck " +
                "where product.product_id = product_ck.product_id and product.product_name = '"+product_name+"' "+
                "group by product_id,product_in";
        String ans[][] =staff.Search(sql);
        ProductKuCun productKuCuns[]=new ProductKuCun[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            productKuCuns[i-1]=new ProductKuCun(ans[i][0],ans[i][1],ans[i][2],ans[i][3]);
        }
        return productKuCuns;
    }

    public ProductKuCun[] SearchAllKuCun() throws SQLException
    {
        String sql="select product.product_id,product.product_name,sum(product_ck.product_rm),product_ck.product_in " +
                "from product,product_ck " +
                "where product.product_id = product_ck.product_id " +
                "group by product_id,product_in";
        String ans[][] =staff.Search(sql);
        ProductKuCun productKuCuns[]=new ProductKuCun[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            productKuCuns[i-1]=new ProductKuCun(ans[i][0],ans[i][1],ans[i][2],ans[i][3]);
        }
        return productKuCuns;
    }

    public MyCustom[] SearchAllCustomers() throws SQLException
    {
        String sql="select customer.customer_id,customer_type.type_name,customer.customer_name,customer.customer_wg,customer.customer_tele " +
                "from customer,customer_type " +
                "where customer.customer_tp = customer_type.type_id";
        String ans[][] =staff.Search(sql);
        MyCustom customs[]=new MyCustom[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            customs[i-1]=new MyCustom(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4]);
        }
        return customs;
    }

    public MyCustom[] SearchCustomersById(String custom_id) throws SQLException
    {
        String sql="select customer.customer_id,customer_type.type_name,customer.customer_name,customer.customer_wg,customer.customer_tele " +
                "from customer,customer_type " +
                "where customer.customer_tp = customer_type.type_id and customer.customer_id = '"+custom_id+"'";
        String ans[][] =staff.Search(sql);
        MyCustom customs[]=new MyCustom[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            customs[i-1]=new MyCustom(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4]);
        }
        return customs;
    }

    public MyCustom[] SearchCustomersByTele(String custom_tele) throws SQLException
    {
        String sql="select customer.customer_id,customer_type.type_name,customer.customer_name,customer.customer_wg,customer.customer_tele " +
                "from customer,customer_type " +
                "where customer.customer_tp = customer_type.type_id and customer.customer_tele = '"+custom_tele+"'";
        String ans[][] =staff.Search(sql);
        MyCustom customs[]=new MyCustom[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            customs[i-1]=new MyCustom(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4]);
        }
        return customs;
    }

    public OneOrderRecord[] SearchAllOrders() throws SQLException
    {
        String sql="select * from orders";
        String ans[][] =staff.Search(sql);
        OneOrderRecord orders[]=new OneOrderRecord[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            orders[i-1]=new OneOrderRecord(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5],ans[i][6],ans[i][7]);
        }
        return orders;
    }

    public OneOrderRecord[] SearchOrdersById(String order_id) throws SQLException
    {
        String sql="select * from orders where order_id = '"+order_id+"'";
        String ans[][] =staff.Search(sql);
        OneOrderRecord orders[]=new OneOrderRecord[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            orders[i-1]=new OneOrderRecord(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5],ans[i][6],ans[i][7]);
        }
        return orders;
    }

    public OneOrderRecord[] SearchOrdersByDate(String start,String end) throws SQLException
    {
        String sql="select * from orders where order_date between '"+ start +"' and '"+ end +"'";
        String ans[][] =staff.Search(sql);
        OneOrderRecord orders[]=new OneOrderRecord[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            orders[i-1]=new OneOrderRecord(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5],ans[i][6],ans[i][7]);
        }
        return orders;
    }

    public ProductSales[] SearchAllSales() throws SQLException
    {
        String sql="SELECT product.product_name,product.product_id, SUM(orders.order_num) AS sums,product.product_p1,product.product_p2,product.product_p3 " +
                "FROM product, orders " +
                "where product.product_name = orders.order_product AND orders.order_type != '已取消' " +
                "GROUP BY product.product_name,product.product_id,product.product_p1,product.product_p2,product.product_p3 " +
                "ORDER BY sums DESC;";
        String ans[][] =staff.Search(sql);
        ProductSales sales[]=new ProductSales[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            sales[i-1]=new ProductSales(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return sales;
    }

    public ProductSales[] SearchSalesById(String product_id) throws SQLException
    {
        String sql="SELECT product.product_name,product.product_id, SUM(orders.order_num) AS sums,product.product_p1,product.product_p2,product.product_p3 " +
                "FROM product, orders " +
                "where product.product_name = orders.order_product AND orders.order_type != '已取消' and product.product_id = '"+product_id+"' "+
                "GROUP BY product.product_name,product.product_id,product.product_p1,product.product_p2,product.product_p3 " +
                "ORDER BY sums DESC;";
        String ans[][] =staff.Search(sql);
        ProductSales sales[]=new ProductSales[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            sales[i-1]=new ProductSales(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return sales;
    }

    public ProductSales[] SearchSalesByName(String product_name) throws SQLException
    {
        String sql="SELECT product.product_name,product.product_id, SUM(orders.order_num) AS sums,product.product_p1,product.product_p2,product.product_p3 " +
                "FROM product, orders " +
                "where product.product_name = orders.order_product AND orders.order_type != '已取消' and product.product_name = '"+product_name+"' "+
                "GROUP BY product.product_name,product.product_id,product.product_p1,product.product_p2,product.product_p3 " +
                "ORDER BY sums DESC;";
        String ans[][] =staff.Search(sql);
        ProductSales sales[]=new ProductSales[ans.length-1];
        for(int i=1;i<ans.length;i++)
        {
            sales[i-1]=new ProductSales(ans[i][0],ans[i][1],ans[i][2],ans[i][3],ans[i][4],ans[i][5]);
        }
        return sales;
    }
}
