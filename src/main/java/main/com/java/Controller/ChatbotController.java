package main.com.java.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import main.com.java.Service.ChatbotServiceImplementation;



@RestController
@RequestMapping("/chatbot")
public class ChatbotController {

    @Autowired
    private ChatbotServiceImplementation chatbotService;

    @PostMapping("/ask")
    public String askChatbot(@RequestParam String question) 
    {
        return chatbotService.getResponse(question);
    }
}
