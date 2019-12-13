package com.zzy.homework.service;

import com.zzy.homework.entity.Borrow;
import com.zzy.homework.util.SpringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class borrow_service {

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

    public Borrow getStudent(String unique_id) {
        Borrow borrow = new Borrow();
        String sqlTxt = "select * from borrow where unique_id=" + unique_id + "";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list= jdbcTemplate.queryForList(sqlTxt);
        if (list.size() > 0){
            borrow.setStu_name(list.get(0).get("stu_name").toString());
            borrow.setPre_time((Date) list.get(0).get("pre_time"));
            borrow.setStu_id(list.get(0).get("stu_id").toString());
            borrow.setBook_id(list.get(0).get("book_id").toString());
            borrow.setStatus(Integer.parseInt(list.get(0).get("status").toString()));
            borrow.setUnique_id(Integer.parseInt(list.get(0).get("unique_id").toString()));
            borrow.setNxt_time((Date) list.get(0).get("nxt_time"));
            borrow.setAllowed_days(Integer.parseInt(list.get(0).get("allowed_days").toString()));
            borrow.setBook_name(list.get(0).get("book_name").toString());
            borrow.setStu_sex(list.get(0).get("stu_sex").toString());
            borrow.setMore_time(0);
        }
        return borrow;
    }

    public List<Borrow> show_stu_borrowed_book(String stu_id) {
        List<Borrow> borrowList = new ArrayList<Borrow>();
        String sqlTxt = "select * from borrow where stu_id = '" + stu_id + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list= jdbcTemplate.queryForList(sqlTxt);
        for ( Map<String, Object> map : list) {
            Borrow borrow = new Borrow();
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd ");
            borrow.setStu_name(map.get("stu_name").toString());
            Date tmp = (Date) map.get("pre_time");
            Date now = new Date();
            int past = differentDays(tmp, now);
            int allowed = (int) map.get("allowed_days");
            int rem = allowed - past;
            borrow.setRemain_days(rem);
            int percen = (int)((double)(allowed - past) * 100 / (double)allowed);
            System.out.println("past = " + past);
            System.out.println("allowed = " + allowed);
            System.out.println("rem = " + rem);
            System.out.println("percen = " + percen);
            String pre = format.format(tmp);
            borrow.setStu_name(map.get("stu_name").toString());
            borrow.setString_version_per_time(pre);
            borrow.setUnique_id(Integer.parseInt(map.get("unique_id").toString()));
            borrow.setStu_id(map.get("stu_id").toString());
            borrow.setBook_id(map.get("book_id").toString());
            borrow.setStatus(Integer.parseInt(map.get("status").toString()));
            borrow.setAllowed_days(allowed);
            borrow.setBook_name(map.get("book_name").toString());
            borrow.setStu_sex(map.get("stu_sex").toString());
            borrow.setPast_days(past);
            borrow.setPercentage(percen);
            borrow.setMore_time(Integer.parseInt(map.get("more_time").toString()));
            borrow.setMore_time(Integer.parseInt(map.get("more_time").toString()));
            borrowList.add(borrow);
        }
        return borrowList;
    }

    public int addTime(String unique_id, int now_time, int allowed_days) {
        String sqlTxt = "update borrow set more_time = " + (now_time + 1) + " where unique_id = " + unique_id;
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        int flag = jdbcTemplate.update(sqlTxt);
        String sqlTxt2 = "update borrow set allowed_days = " + (allowed_days + 7) + " where unique_id = " + unique_id;
        int flag2 = jdbcTemplate.update(sqlTxt2);
        return 1;
    }

    public int payMoney(String unique_id, int num, int allowed_days) {
        String sqlTxt = "update borrow set allowed_days = " + (allowed_days - num + 7) + " where unique_id = " + unique_id;
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        int flag = jdbcTemplate.update(sqlTxt);
        return 1;
    }
}
