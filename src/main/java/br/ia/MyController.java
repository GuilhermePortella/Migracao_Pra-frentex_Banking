package br.ia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
   private final chatClient chatClient;

   public MyController(ChatClient.Builder chatClientBuilder) {
      this.chatClient = chatClientBuilder.build();
   }

   @GetMapping("/ai")
   String generation(String userInput) {
      return this.chatClient.prompt()
            .user(userInput)
            .call()
            .content();
   }
}
