package com.zzy.homework.controller;

import com.zzy.homework.entity.Book;
import com.zzy.homework.entity.Borrow;
import com.zzy.homework.entity.Student;
import com.zzy.homework.service.admin_service;
import com.zzy.homework.service.book_service;
import com.zzy.homework.service.borrow_service;
import com.zzy.homework.service.student_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
public class CBook_Web {

    @Autowired
    private student_service student_svc;

    @Autowired
    private book_service book_svc;

    @Autowired
    private borrow_service borrow_svc;

    @Autowired
    private admin_service  admin_svc;

    @RequestMapping(value = "vbooki", method = RequestMethod.GET)
    private String viewBookInfo(HttpServletRequest request, Model model) {
        String book_id = request.getParameter("book_id");
        String stu_id = request.getParameter("stu_id");
        System.out.println(stu_id);
        Book book = book_svc.findBook(book_id);
        Student student = student_svc.findStudent(stu_id);
        model.addAttribute("book_info", book);
        model.addAttribute("student", student);
        return "pages/examples/view_book_info";
    }

    @RequestMapping("return_borrow_info")
    private String returnBorrowInfo(HttpServletRequest request, Model model) throws ParseException {
        String unique_id = request.getParameter("unique_id");
        Borrow borrow = borrow_svc.getStudent(unique_id);
        String stu_id = borrow.getStu_id();
        String book_id = borrow.getBook_id();
        Student student = student_svc.findStudent(stu_id);
        Book book = book_svc.findBook(book_id);
        int flag =  book_svc.deleteBorrowBook(student, book, borrow);
        List<Borrow> borrowList = admin_svc.getAllBorrowInfo();
        model.addAttribute("borrow_list", borrowList);
        return "pages/examples/admin-projects";
    }

    @RequestMapping("query_book_info")
    private String queryBookInfo(HttpServletRequest request, Model model) {
        String field = request.getParameter("field");
        String condition = request.getParameter("condition");
        String stu_id = request.getParameter("stu_id");
        List<Book> book = book_svc.findPartialBook(field, condition);
        Student student = student_svc.findStudent(stu_id);
        model.addAttribute("book_list", book);
        model.addAttribute("student", student);
        return "pages/examples/projects";
    }

}
