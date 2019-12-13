package com.zzy.homework.service;

import com.zzy.homework.entity.Admin;
import com.zzy.homework.entity.Borrow;
import com.zzy.homework.util.SpringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class admin_service {

        public Admin checkAdminLoginWeb(String admin_id, String admin_pwd){
            String sqlTxt = "select * from admin_info where admin_id='"
                    +  admin_id + "' and admin_pwd='"
                    + admin_pwd + "'";

            JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
            List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
            Admin admin = null;
            if (list.size() > 0){
                admin = new Admin();
                admin.setAdmin_id(list.get(0).get("admin_id").toString());
                admin.setAdmin_name(list.get(0).get("admin_name").toString());
                admin.setAdmin_phone(list.get(0).get("admin_phone").toString());
                admin.setAdmin_pwd(list.get(0).get("admin_pwd").toString());
            }
            return admin;
        }

    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //不同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //同一年
        {
            return day2-day1;
        }
    }

    public List<Borrow> getAllBorrowInfo() throws ParseException {
        List<Borrow> borrowList = new ArrayList<Borrow>();
        String sqlTxt = "select * from borrow";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for ( Map<String, Object> map : list) {
            SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd ");
            Borrow borrow = new Borrow();
            Date pre = (Date)map.get("pre_time");
            Date date = new Date();
            int gap = differentDays(pre, date);
            int allow = Integer.parseInt(map.get("allowed_days").toString());
            int res = (int) (100 * (gap * 1.0 / allow));
            borrow.setPre_time(pre);
            borrow.setStu_id(map.get("stu_id").toString());
            borrow.setBook_id(map.get("book_id").toString());
            borrow.setStatus(Integer.parseInt(map.get("status").toString()));
            borrow.setUnique_id(Integer.parseInt(map.get("unique_id").toString()));
            borrow.setNxt_time((Date) map.get("nxt_time"));
            borrow.setAllowed_days(allow);
            borrow.setStu_name(map.get("stu_name").toString());
            borrow.setBook_name(map.get("book_name").toString());
            borrow.setStu_sex(map.get("stu_sex").toString());
            borrow.setPercentage(res);
            borrow.setMore_time(0);
            borrowList.add(borrow);
        }
        return borrowList;
    }

    public Borrow viewStuInfo(String uniqueid) {
        String sqlTxt = "select * from borrow where unique_id="
                +  uniqueid + "";
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        Borrow borrow = new Borrow();
        Date now = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tmp = format.format(now);
        if (list.size() > 0){
            borrow.setStu_name(list.get(0).get("stu_name").toString());
            borrow.setPre_time((Date)list.get(0).get("pre_time"));
            borrow.setStu_id(list.get(0).get("stu_id").toString());
            borrow.setBook_id(list.get(0).get("book_id").toString());
            borrow.setStatus(Integer.parseInt(list.get(0).get("status").toString()));
            borrow.setUnique_id(Integer.parseInt(list.get(0).get("unique_id").toString()));
            borrow.setNxt_time((Date) list.get(0).get("nxt_time"));
            borrow.setAllowed_days(Integer.parseInt(list.get(0).get("allowed_days").toString()));
            borrow.setBook_name(list.get(0).get("book_name").toString());
            borrow.setStu_sex(list.get(0).get("stu_sex").toString());
            borrow.setNow_time(tmp);
            borrow.setMore_time(0);
            int past = differentDays((Date) list.get(0).get("pre_time"), now);
            borrow.setPast_days(past);
        }
        return borrow;
    }

    public int mbi(int unique_id, int num) {
        String sqlTxt = "update borrow set allowed_days = " + num + " where unique_id = " + unique_id;
        System.out.println(sqlTxt);
        JdbcTemplate jdbcTemplate = (JdbcTemplate) SpringUtil.applicationContext.getBean("jdbcTemplate");
        int flag = jdbcTemplate.update(sqlTxt);
        return flag;
    }
}
