package org.example.online_qa_web_advance1.service;

import org.example.online_qa_web_advance1.model.DiscussionTopic;
import org.example.online_qa_web_advance1.model.Reply;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ForumService {

    private final ConcurrentHashMap<Integer, DiscussionTopic> topics = new ConcurrentHashMap<>();
    private final AtomicInteger topicIdCounter = new AtomicInteger(100);
    private final AtomicInteger replyIdCounter = new AtomicInteger(200);

    public List<DiscussionTopic> getAllTopics() {
        return new ArrayList<>(topics.values());
    }

    public DiscussionTopic getTopicById(int id) {
        return topics.get(id);
    }

    public DiscussionTopic createTopic(DiscussionTopic topic) {
        int id = topicIdCounter.getAndIncrement();
        topic.setId(id);
        topics.put(id, topic);
        return topic;
    }

    public Reply addReply(Reply reply) {
        DiscussionTopic topic = topics.get(reply.getTopicId());
        if (topic != null) {
            int id = replyIdCounter.getAndIncrement();
            reply.setId(id);
            topic.addReply(reply);
            return reply;
        }
        return null;
    }
}