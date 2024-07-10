package com.cod.market.question.service;

import com.cod.market.member.entity.Member;
import com.cod.market.product.entity.Product;
import com.cod.market.question.entity.Question;
import com.cod.market.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void create(String content, Member member, Product product) {
        Question q = new Question();
        q.setCreateDate(LocalDateTime.now());
        q.setContent(content);
        q.setMember(member);
        q.setProduct(product);
        questionRepository.save(q);
    }

    public List<Question> getQuestion() {
        return questionRepository.findAll();
    }
}
