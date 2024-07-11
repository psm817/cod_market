package com.cod.market.question.service;

import com.cod.market.member.entity.Member;
import com.cod.market.product.entity.Product;
import com.cod.market.question.entity.Question;
import com.cod.market.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public Question getQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);

        if(question.isPresent()) {
            return question.get();
        } else {
            throw new RuntimeException("question not found");
        }
    }

    public void modify(Question question, String content) {
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());

        questionRepository.save(question);
    }
}