package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.Dialogue;
import com.springboot.Captioner.service.DialogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AddDialogueController {
    @Autowired
    DialogueService dialogueService;

    @RequestMapping(value = {"/adddialogue"}, method = RequestMethod.GET)
    public ModelAndView addDialogues() {
        ModelAndView modelAndView = new ModelAndView();
        Dialogue dialogue = new Dialogue();
        modelAndView.addObject("dialogue", dialogue);
        modelAndView.setViewName("adddialogue");
        return modelAndView;
    }

    @RequestMapping(value = "/adddialogue", method = RequestMethod.POST)
    public ModelAndView addDialogue(@Valid Dialogue dialogue, BindingResult bindingResult, ModelMap modelMap, @RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please add correct details!");
            modelMap.addAttribute("bindingResult", bindingResult);
        } else if (dialogueService.isDialoguePresent(dialogue)) {
            modelAndView.addObject("successMessage", "Dialogue already exists!");
        } else {
            try {
                List<Dialogue> dialogues = parseSRTFile(file.getInputStream());
                dialogueService.saveAllDialogues(dialogues, dialogue.getSubtitle());
                modelAndView.addObject("successMessage", "Dialogue added successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                modelAndView.addObject("successMessage", "Failed to upload file or parse dialogue!");
            }
        }
        modelAndView.addObject("dialogue", new Dialogue());
        modelAndView.setViewName("adddialogue");
        return modelAndView;
    }

    private List<Dialogue> parseSRTFile(InputStream inputStream) throws IOException {
        List<Dialogue> dialogues = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder textBuilder = new StringBuilder(); // 使用 StringBuilder 来构建多行文本内容

            String startTime = null;
            String endTime = null;

            boolean readingText = false; // 标记是否正在读取对话文本

            while ((line = reader.readLine()) != null) {
                // 如果当前行包含 '-->'，说明是时间行，开始分离时间
                if (line.contains("-->")) {
                    String[] times = line.split(" --> ");
                    String[] starttimes = times[0].split(",");
                    String[] endtimes = times[1].split(",");
                    startTime = starttimes[0].trim();
                    endTime = endtimes[0].trim();
                    readingText = true; // 开始读取对话文本
                } else if (readingText && !line.trim().isEmpty()) {
                    // 如果当前正在读取对话文本，并且当前行不为空行，将当前行的文本内容添加到文本构建器中
                    textBuilder.append(line).append("\n");
                } else if (readingText && line.trim().isEmpty()) {
                    // 如果当前正在读取对话文本，并且当前行是空行，说明当前对话文本读取完毕，创建对话对象并添加到列表中
                    String text = textBuilder.toString().trim();
                    dialogues.add(new Dialogue(startTime, endTime, text));
                    textBuilder.setLength(0); // 清空文本构建器
                    readingText = false; // 结束读取对话文本
                }
            }

            // 如果循环结束时还有未添加的对话文本，需要添加最后一个对话
            if (readingText && textBuilder.length() > 0) {
                String text = textBuilder.toString().trim();
                dialogues.add(new Dialogue(startTime, endTime, text));
            }
        }

        return dialogues;
    }
}
