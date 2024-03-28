//package com.springboot.Captioner.controller;
//
//import com.springboot.Captioner.model.Dialogue;
//import com.springboot.Captioner.service.DialogueService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class DialogueUploadController {
//
//    @Autowired
//    private DialogueService dialogueService;
//
//    @PostMapping("/adddialogue")
//    public String uploadSubtitle(@RequestParam("file") MultipartFile file, @RequestParam("subtitleId") int subtitleId) {
//        if (file.isEmpty()) {
//            // 文件为空，返回错误信息
//            return "redirect:/admin?error=File is empty";
//        }
//
//        try {
//            // 读取上传的文件内容
//            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
//            List<Dialogue> dialogueList = parseSubtitleFile(reader);
//
//            // 保存字幕信息到数据库
//            dialogueService.saveAllDialogues(dialogueList, subtitleId);
//
//            // 重定向到管理员界面，并提示上传成功
//            return "redirect:/home?success=Subtitle uploaded successfully";
//        } catch (IOException e) {
//            // 文件读取错误，返回错误信息
//            return "redirect:/home?error=Failed to read subtitle file";
//        }
//    }
//
//    private List<Dialogue> parseSubtitleFile(BufferedReader reader) throws IOException {
//        List<Dialogue> dialogueList = new ArrayList<>();
//        String line;
//
//        while ((line = reader.readLine()) != null) {
//            // 解析每一行字幕，创建 Dialogue 对象并添加到列表中
//            // 这里需要根据具体的字幕文件格式进行解析，下面仅作示例
//            String[] parts = line.split("-->");
//            if (parts.length == 2) {
//                String startTime = parts[0].trim();
//                String endTime = parts[1].trim();
//                String text = reader.readLine().trim(); // 读取字幕文本内容
//                Dialogue dialogue = new Dialogue(startTime, endTime, text);
//                dialogueList.add(dialogue);
//            }
//        }
//
//        return dialogueList;
//    }
//}
