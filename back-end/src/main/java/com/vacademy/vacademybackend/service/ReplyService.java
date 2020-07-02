package com.vacademy.vacademybackend.service;

import com.vacademy.vacademybackend.database.ReplyRepository;
import com.vacademy.vacademybackend.model.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    public List<Reply> getAllReplies() {
        return replyRepository.findAll();
    }

    public Reply addReply(Reply reply) {
        replyRepository.save(reply);
        return replyRepository.findReplyByRepliedbyAndQidAndTimestamp(reply.getRepliedby(), reply.getQid(), reply.getTimestamp());
    }
}
