package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.Dialogue;
import com.springboot.Captioner.service.DialogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AddDialogueController {
    @Autowired
    DialogueService dialogueService;

    @RequestMapping(value = {"/adddialogue"}, method = RequestMethod.GET)
    public ModelAndView addDialogues() {
        ModelAndView modelAndView = new ModelAndView();
        Dialogue dialogue = new Dialogue();
        modelAndView.addObject("dialogue", dialogue);
        modelAndView.setViewName("adddialogue"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = "/adddialogue", method = RequestMethod.POST)
    public ModelAndView addDialogue(@Valid Dialogue dialogue, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please add correct details!");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else if (dialogueService.isDialoguePresent(dialogue)) {
            modelAndView.addObject("successMessage", "Dialogue already exists!");
        } else {
            dialogueService.saveDialogue(dialogue);
            modelAndView.addObject("successMessage", "Dialogue added successfully!");
        }
        modelAndView.addObject("dialogue", new Dialogue());
        modelAndView.setViewName("adddialogue");
        return modelAndView;
    }
//
//    @GetMapping("/viewplays")
//    public String viewPlays(Model model) {
//        List<PlayDTO> plays = playService.getAllPlays();
//        model.addAttribute("plays", plays); // 将 plays 数据添加到模型中
//        return "viewplays"; // 返回视图名称
//    }
}
