package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.PlayDTO;
import com.springboot.Captioner.service.DialogueService;
import com.springboot.Captioner.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AddPlayController {
    @Autowired
    PlayService playService;

    @Autowired
    DialogueService dialogueService;


    @GetMapping("/addplay")
    public String showAddPlayForm(Model model) {
        List<String> subtitleNames = dialogueService.getAllSubtitleNames(); // 获取数据库中已有的subtitle_name列表
        model.addAttribute("subtitleNames", subtitleNames); // 将subtitle_name列表添加到模型中
        model.addAttribute("play", new Play());
        return "addplay";
    }

    @PostMapping("/addplay")
    public ModelAndView addPlay(@Valid Play play, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please add correct details!");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else if (playService.isPlayPresent(play)) {
            modelAndView.addObject("successMessage", "Play already exists!");
        } else {
            playService.savePlay(play);
            modelAndView.addObject("successMessage", "Play added successfully!");
        }

        modelAndView.addObject("play", new Play());
        modelAndView.setViewName("addplay");
        return modelAndView;
    }


    @GetMapping("/viewplays")
    public String viewPlays(Model model) {
        List<PlayDTO> plays = playService.getAllPlays();
        model.addAttribute("plays", plays); // 将 plays 数据添加到模型中
        return "viewplays"; // 返回视图名称
    }
}
