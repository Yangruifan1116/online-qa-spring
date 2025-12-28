package org.example.online_qa_web_advance1.controller;

import org.example.online_qa_web_advance1.model.Reply;
import org.example.online_qa_web_advance1.model.User;
import org.example.online_qa_web_advance1.service.ForumService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ForumService forumService;

    @PostMapping
    public String addReply(@RequestParam int topicId,
                           @RequestParam String content,
                           HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        if (content != null && !content.trim().isEmpty()) {
            Reply reply = new Reply();
            reply.setTopicId(topicId);
            reply.setContent(content.trim());
            reply.setAuthor(user.getUsername());

            forumService.addReply(reply);
        }

        return "redirect:/topics?action=view&id=" + topicId;
    }
}