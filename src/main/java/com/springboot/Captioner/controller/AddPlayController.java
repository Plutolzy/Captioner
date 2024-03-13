package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.Plays;
import com.springboot.Captioner.service.PlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AddPlayController {
    @Autowired
    PlayService playService;

    @RequestMapping(value = {"/addPlay"}, method = RequestMethod.GET)
    public ModelAndView addPlays(){
        ModelAndView modelAndView = new ModelAndView();
        Plays play = new Plays();
        modelAndView.addObject("play", play);
        modelAndView.setViewName("addPlay"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value = "/addPlay", method = RequestMethod.POST)
    public ModelAndView addPlay(@Valid Plays play, BindingResult bindingResult, ModelMap modelMap){
        ModelAndView modelAndView = new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "Please add correct details!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }else if(playService.isPlayPresent(play)){
            modelAndView.addObject("successMessage", "Play already exists!");
        }else {
            playService.savePlay(play);
            modelAndView.addObject("successMessage", "Play added successfully!");
        }
        modelAndView.addObject("play", new Plays());
        modelAndView.setViewName("addPlay");
        return modelAndView;
    }
}
