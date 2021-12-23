package kr.omen.pico.controller;

import kr.omen.pico.domain.ChatMessage;
import kr.omen.pico.domain.dto.ChatMessageDTO;
import kr.omen.pico.domain.dto.ResponseDTO;
import kr.omen.pico.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChatMessageController {

    private final ChatMessageService chatMessageService;

    @PostMapping("/chatmessage")
    public ResponseDTO.chatMessageResponse sendMessage(@RequestBody ChatMessageDTO.Create dto){
        ChatMessage chatMessage = chatMessageService.sendMessage(dto);
        return new ResponseDTO.chatMessageResponse(chatMessage);
    }

    @GetMapping("/chatmessage/{chatroomidx}")
    public ResponseDTO.chatMessageListResponse findAllMessage(@PathVariable Long chatroomidx){
        List<ChatMessageDTO.Card> chatMessageList = chatMessageService.findMessageListByChatRoom(chatroomidx);
        return new ResponseDTO.chatMessageListResponse(chatMessageList);
    }
}
