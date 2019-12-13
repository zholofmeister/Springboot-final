package com.zzy.homework.controller;

import com.zzy.homework.entity.Book;
import com.zzy.homework.entity.Borrow;
import com.zzy.homework.entity.Student;
import com.zzy.homework.service.book_service;
import com.zzy.homework.service.borrow_service;
import com.zzy.homework.service.student_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CStudent_Web {

    @Autowired
    private student_service stu_svc;

    @Autowired
    private book_service book_svc;

    @Autowired
    private borrow_service borrow_svc;

    @RequestMapping("show_login")
    private String showLogin() {return "pages/examples/login";}

    @RequestMapping("reco_pwd")
    private String forgetPassword() {return "pages/examples/recover-password";}

    @RequestMapping("change_pwd")
    private String changePassword(HttpServletRequest request) {
        String no = request.getParameter("stu_no");
        String pwd = request.getParameter("stu_pwd");
        int ok = stu_svc.changePasswordWeb(no, pwd);
        return "pages/examples/recover-password";
    }

    @RequestMapping("show_main")
    private String showMain() {return "pages/examples/projects";}

    @RequestMapping("check_login_web")
    public String checkLoginWeb(HttpServletRequest request, Model model){
        Student student = null;
        String id = request.getParameter("stu_id");
        String pwd = request.getParameter("stu_pwd");
        if (request.getParameter("identity").equals("admin")) {
            HttpSession  session = request.getSession();
            session.setAttribute("admin_id", id);
            session.setAttribute("admin_pwd", pwd);
            return "redirect:check_admin_login_web";
        }

        student = stu_svc.checkLoginWeb(id, pwd);
        if (student == null)
            return "redirect:show_login";
        HttpSession session = request.getSession();
        session.setAttribute("stu_info", student);
        List<Book> book_list = book_svc.show_all_book_info();
        model.addAttribute("book_list", book_list);
        //System.out.println(student.getStu_id());
        model.addAttribute("student", student);
        return "pages/examples/projects";
    }

    @RequestMapping("stu_borrow_book")
    private String stuBorrowBook(HttpServletRequest request, Model model) {
        String stu_id = request.getParameter("stu_id");
        String book_id = request.getParameter("book_id");
        String stu_pwd = request.getParameter("stu_pwd");

        Student student = stu_svc.checkLoginWeb(stu_id, stu_pwd);
        Book book = book_svc.findBook(book_id);
        int flag = book_svc.borrowBook(student, book);

        student = stu_svc.checkLoginWeb(stu_id, stu_pwd);
        HttpSession session = request.getSession();
        session.setAttribute("stu_info", student);
        List<Book> book_list = book_svc.show_all_book_info();
        model.addAttribute("book_list", book_list);
        //System.out.println(student.getStu_id());
        model.addAttribute("student", student);
        return "pages/examples/projects";
    }

    @RequestMapping("show_projects")
    private String showProject(HttpServletRequest request, Model model) {
        String stu_id = request.getParameter("stu_id");
        String stu_pwd = request.getParameter("stu_pwd");
        Student student = stu_svc.checkLoginWeb(stu_id, stu_pwd);
        List<Book> book_list = book_svc.show_all_book_info();
        model.addAttribute("book_list", book_list);
        model.addAttribute("student", student);
        return "pages/examples/projects";
    }

    @RequestMapping("stu_borrowed_book")
    private String stuBorrowedBook(HttpServletRequest request, Model model) {
        String stu_id = request.getParameter("stu_id");
        Student student = stu_svc.findStudent(stu_id);
        List<Borrow> borrow_list = borrow_svc.show_stu_borrowed_book(stu_id);
        model.addAttribute("borrow_list", borrow_list);
        model.addAttribute("student", student);
        return "pages/examples/view_stu_borrowed_book";
    }

    @RequestMapping("add_more_time")
    private String addMoreTime(HttpServletRequest request, Model model) {
        String unique_id = request.getParameter("unique_id");
        int now_time = Integer.parseInt(request.getParameter("more_time"));
        int allowed_days = Integer.parseInt(request.getParameter("allowed_days"));
        String stu_id = request.getParameter("stu_id");
        int flag = borrow_svc.addTime(unique_id, now_time, allowed_days);
        Student student = stu_svc.findStudent(stu_id);
        List<Borrow> borrow_list = borrow_svc.show_stu_borrowed_book(stu_id);
        model.addAttribute("borrow_list", borrow_list);
        model.addAttribute("student", student);
        return "pages/examples/view_stu_borrowed_book";
    }

    @RequestMapping("pay_borrow_money")
    private String payBorrowMoney(HttpServletRequest request, Model model) {
        String unique_id = request.getParameter("unique_id");
        int now_num = Integer.parseInt(request.getParameter("now_num"));
        int allowed_days = Integer.parseInt(request.getParameter("allowed_days"));
        int flag = borrow_svc.payMoney(unique_id, now_num, allowed_days);
        String stu_id = request.getParameter("stu_id");
        Student student = stu_svc.findStudent(stu_id);
        List<Borrow> borrow_list = borrow_svc.show_stu_borrowed_book(stu_id);
        model.addAttribute("borrow_list", borrow_list);
        model.addAttribute("student", student);
        return "pages/examples/view_stu_borrowed_book";
    }
}
