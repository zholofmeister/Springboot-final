package com.zzy.homework.service;

import com.zzy.homework.entity.Student;
import com.zzy.homework.util.SpringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class student_service {
    public Student checkLoginWeb(String stu_id, String stu_pwd){
        String sqlTxt = "select * from stu_info where stu_id='"
                +  stu_id + "' and stu_pwd='"
                + stu_pwd + "'";
        //System.out.println(sqlTxt);
        //访问数据库

        JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        Student student = null;
        if (list.size() > 0){
            student = new Student();
            student.setStu_id(list.get(0).get("stu_id").toString());
            student.setStu_name(list.get(0).get("stu_name").toString());
            student.setStu_age(Integer.parseInt(list.get(0).get("stu_age").toString()));
            student.setStu_phone(list.get(0).get("stu_phone").toString());
            student.setStu_pro(list.get(0).get("stu_pro").toString());
            student.setStu_pwd(list.get(0).get("stu_pwd").toString());
            student.setStu_sex(list.get(0).get("stu_sex").toString());
            student.setStu_sum(Integer.parseInt(list.get(0).get("stu_sum").toString()));
            student.setStu_limit(Integer.parseInt(list.get(0).get("stu_limit").toString()));
            student.setStu_allowed(Integer.parseInt(list.get(0).get("stu_allowed").toString()));
        }
        return student;
    }

    public Student findStudent(String stu_id){
        String sqlTxt = "select * from stu_info where stu_id='"
                +  stu_id + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        Student student = null;
        if (list.size() > 0){
            student = new Student();
            student.setStu_id(list.get(0).get("stu_id").toString());
            student.setStu_name(list.get(0).get("stu_name").toString());
            student.setStu_age(Integer.parseInt(list.get(0).get("stu_age").toString()));
            student.setStu_phone(list.get(0).get("stu_phone").toString());
            student.setStu_pro(list.get(0).get("stu_pro").toString());
            student.setStu_pwd(list.get(0).get("stu_pwd").toString());
            student.setStu_sex(list.get(0).get("stu_sex").toString());
            student.setStu_sum(Integer.parseInt(list.get(0).get("stu_sum").toString()));
            student.setStu_limit(Integer.parseInt(list.get(0).get("stu_limit").toString()));
            student.setStu_allowed(Integer.parseInt(list.get(0).get("stu_allowed").toString()));
        }
        return student;
    }

    public int changePasswordWeb(String no, String pwd) {
        String sqlTxt = "select * from stu_info where stu_no='"
                +  no + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        Student student = null;
        if (list.size() == 0){
            return 0;
        }
        String sqlTxt2 = "update stu_info set stu_pwd = '" + pwd +"' where stu_no = '" + no + "'";
        return 1;
    }
}
