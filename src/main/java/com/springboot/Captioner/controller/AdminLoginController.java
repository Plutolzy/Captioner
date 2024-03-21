package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.Admin;
import com.springboot.Captioner.model.UserDTO;
import com.springboot.Captioner.repository.AdminRepository;
import com.springboot.Captioner.service.AdminService;
import com.springboot.Captioner.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminLoginController {
    @Autowired
    AdminService adminService;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        Admin admin = new Admin();
        modelAndView.addObject("admin", admin);
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

//    @RequestMapping(value = {"/register"}, method = RequestMethod.GET)
//    public ModelAndView register() {
//        ModelAndView modelAndView = new ModelAndView();
//        Admin admin = new Admin();
//        modelAndView.addObject("admin", admin);
//        modelAndView.setViewName("register"); // resources/template/register.html
//        return modelAndView;
//    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home"); // resources/template/home.html
        return modelAndView;
    }

    @GetMapping("/manageusers")
    public String manageUsers(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users); // 将 plays 数据添加到模型中
        return "manageusers"; // 返回视图名称
    }

//    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
//    public ModelAndView adminHome(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin"); // resources/template/admin.html
//        return modelAndView;
//    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ModelAndView registerAdmin(@Valid Admin admin, BindingResult bindingResult, ModelMap modelMap) {
//        ModelAndView modelAndView = new ModelAndView();
//        if (bindingResult.hasErrors()) {
//            modelAndView.addObject("successMessage", "Please add correct details!");
//            modelMap.addAttribute("bindingResult", bindingResult);
//        } else if (adminService.isAdminPresent(admin)) {
//            modelAndView.addObject("successMessage", "Admin already exists!");
//        } else {
//            adminService.saveAdmin(admin);
//            modelAndView.addObject("successMessage", "Admin registered successfully!");
//        }
//        modelAndView.addObject("admin", new Admin());
//        modelAndView.setViewName("register");
//        return modelAndView;
//    }

}
