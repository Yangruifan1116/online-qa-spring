package org.example.online_qa_web_advance1.controller;

import org.example.online_qa_web_advance1.model.DiscussionTopic;
import org.example.online_qa_web_advance1.model.User;
import org.example.online_qa_web_advance1.service.ForumService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private ForumService forumService;

    @GetMapping
    public String listTopics(Model model, HttpSession session) {
        model.addAttribute("topics", forumService.getAllTopics());
        return "topic-list";
    }

    @GetMapping(params = "action=create")
    public String createTopicPage() {
        return "create-topic";
    }

    @PostMapping
    public String createTopic(@RequestParam String title,
                              @RequestParam String content,
                              HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        if (title != null && !title.trim().isEmpty() && content != null && !content.trim().isEmpty()) {
            DiscussionTopic topic = new DiscussionTopic();
            topic.setTitle(title.trim());
            topic.setContent(content.trim());
            topic.setAuthor(user.getUsername());

            forumService.createTopic(topic);
        }

        return "redirect:/topics";
    }

    @GetMapping(params = "action=view")
    public String viewTopic(@RequestParam int id, Model model, HttpSession session) {
        DiscussionTopic topic = forumService.getTopicById(id);
        if (topic == null) {
            return "redirect:/topics";
        }

        model.addAttribute("topic", topic);
        return "topic-detail";
    }
}