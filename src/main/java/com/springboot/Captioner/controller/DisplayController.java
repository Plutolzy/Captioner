package com.springboot.Captioner.controller;

import com.springboot.Captioner.model.DialogueDTO;
import com.springboot.Captioner.model.Play;
import com.springboot.Captioner.model.UserResponse;
import com.springboot.Captioner.repository.PlayRepository;
import com.springboot.Captioner.service.DialogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class DisplayController {
    @Autowired
    PlayRepository playRepository;
    @Autowired
    DialogueService dialogueService;

//    @PostMapping("/display")
//    public ResponseEntity<UserResponse> registerUser(@RequestBody PlayDTOBean playDTOBean) {
//        // 播放成功
//        UserResponse response = new UserResponse();
//        response.setSuccess(true);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/currentdialogue")
    @ResponseBody
    public List<DialogueDTO> getCurrentAndFutureDialogue(@RequestParam("playTitle") String playTitle) {
        // 根据播放标题获取当前播放的信息
        System.out.println(playTitle);
        Play currentPlay = playRepository.findByTitle(playTitle);
        if (currentPlay == null) {
            // 如果找不到当前播放信息，返回空列表或错误信息
            return List.of(); // 返回空列表
        }

        // 获取当前时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String nowString = dateTimeFormatter.format(LocalDateTime.now());
        String playStartTime = dateTimeFormatter.format(currentPlay.getStartTime());

        System.out.println(dialogueService.getCurrentDialogueList(currentPlay.getSubtitle(), playStartTime, nowString));
        UserResponse response = new UserResponse();
        response.setSuccess(true);
        // 获取当前及之后的对话列表

        List<DialogueDTO> currentDialogueList = dialogueService.getCurrentDialogueList(currentPlay.getSubtitle(), playStartTime, nowString);


        System.out.println(currentDialogueList);

        return currentDialogueList;
    }
}
