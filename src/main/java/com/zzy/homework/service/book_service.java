package com.zzy.homework.service;

import com.zzy.homework.entity.Book;
import com.zzy.homework.entity.Borrow;
import com.zzy.homework.entity.Student;
import com.zzy.homework.util.SpringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class book_service {
    public Book findBook(String book_id){
        String sqlTxt = "select * from book_info where book_id='"
                +  book_id + "'";

        JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        Book book = null;
        if (list.size() > 0){
            book = new Book();
            book.setBook_id(list.get(0).get("book_id").toString());
            book.setBook_writer(list.get(0).get("book_writer").toString());
            book.setBook_presum(Integer.parseInt(list.get(0).get("book_presum").toString()));
            book.setBook_nowsum(Integer.parseInt(list.get(0).get("book_nowsum").toString()));
            book.setBook_boss(list.get(0).get("book_boss").toString());
            book.setBook_name(list.get(0).get("book_name").toString());
        }
        return book;
    }

    public List<Book> show_all_book_info() {
        List<Book> bookList = new ArrayList<Book>();
        String sqlTxt = "select * from book_info";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for ( Map<String, Object> map : list) {
            Book book = new Book();
            book.setBook_id(map.get("book_id").toString());
            book.setBook_writer(map.get("book_writer").toString());
            book.setBook_boss(map.get("book_boss").toString());
            book.setBook_name(map.get("book_name").toString());
            int pre_sum = Integer.parseInt(map.get("book_presum").toString());
            int now_sum = Integer.parseInt(map.get("book_nowsum").toString());
            int percentage = (int) (((double)now_sum / (double)pre_sum) * 100);
            book.setBook_presum(pre_sum);
            book.setBook_nowsum(now_sum);
            book.setPercentage(percentage);
            bookList.add(book);
        }
        return bookList;
    }

    public void deleteBookSum(String book_id, int book_nowsum) {
        String sqlTxt = "update book_info set book_nowsum = " + (book_nowsum - 1) + " where book_id = '" + book_id + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        int flag = jdbcTemplate.update(sqlTxt);
    }

    public void addStuSum(String stu_id, int stu_sum) {
        String sqlTxt = "update stu_info set stu_sum = " + (stu_sum + 1) + " where stu_id = '" + stu_id + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        int flag = jdbcTemplate.update(sqlTxt);
    }

    public void addBorrow(String stu_id, String stu_name, String stu_sex, int stu_allowed, String book_id, String book_name) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String tmp = format.format(date);
        String sqlTxt = "insert into borrow (stu_name, pre_time, stu_id, nxt_time, book_id, status, allowed_days, book_name, stu_sex, more_time) values " +
                "('" + stu_name + "','" + tmp + "','" + stu_id +  "'," + null + ",'" + book_id + "'," + 0 +  "," + stu_allowed  + ",'" + book_name + "','" + stu_sex
                + "'," + 0 + ")";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        int flag = jdbcTemplate.update(sqlTxt);
    }

    public int borrowBook(Student student, Book book) {
        deleteBookSum(book.getBook_id(), book.getBook_nowsum());
        addStuSum(student.getStu_id(), student.getStu_sum());
        addBorrow(student.getStu_id(), student.getStu_name(), student.getStu_sex(), student.getStu_allowed(), book.getBook_id(), book.getBook_name());
        return 1;
    }




    public int deleteBorrowBook(Student student, Book book, Borrow borrow) {
        addBookSum(book.getBook_id(), book.getBook_nowsum());
        deleteStuSum(student.getStu_id(), student.getStu_sum());
        deleteBorrow(borrow.getUnique_id());
        return 1;
    }

    private void addBookSum(String book_id, int book_nowsum) {
        String sqlTxt = "update book_info set book_nowsum = " + (book_nowsum + 1) + " where book_id = '" + book_id + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        int flag = jdbcTemplate.update(sqlTxt);
    }

    private void deleteBorrow(int unique_id) {
        String sqlTxt = "delete from borrow where unique_id=" + unique_id + "";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        int flag = jdbcTemplate.update(sqlTxt);
    }

    private void deleteStuSum(String stu_id, int stu_sum) {
        String sqlTxt = "update stu_info set stu_sum = " + (stu_sum - 1) + " where stu_id = '" + stu_id + "'";
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        int flag = jdbcTemplate.update(sqlTxt);
    }


    public List<Book> findPartialBook(String field, String condition) {
        List<Book> bookList = new ArrayList<Book>();
        String sqlTxt = "select * from book_info where  1=1 ";
        if (!field.equals("default")) {
            sqlTxt += " and " + field + " like '%" + condition + "%' ";
        }
        JdbcTemplate jdbcTemplate = (JdbcTemplate)
                SpringUtil.applicationContext.getBean("jdbcTemplate");
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sqlTxt);
        for (Map<String, Object> map : list) {
            Book book = new Book();
            book.setBook_id(map.get("book_id").toString());
            book.setBook_writer(map.get("book_writer").toString());
            book.setBook_boss(map.get("book_boss").toString());
            book.setBook_name(map.get("book_name").toString());
            int pre_sum = Integer.parseInt(map.get("book_presum").toString());
            int now_sum = Integer.parseInt(map.get("book_nowsum").toString());
            int percentage = (int) (((double)now_sum / (double)pre_sum) * 100);
            book.setBook_presum(pre_sum);
            book.setBook_nowsum(now_sum);
            book.setPercentage(percentage);
            bookList.add(book);
        }
        return bookList;
    }
}
