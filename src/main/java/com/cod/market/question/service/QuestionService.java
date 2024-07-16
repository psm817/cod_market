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
        Question q = Question.builder()
                .content(content)
                .member(member)
                .product(product)
                .build();

        questionRepository.save(q);
    }

    public Question getQuestion(Long id) {
        Optional<Question> question = questionRepository.findById(id);

        if (question.isPresent()) {
            return question.get();
        } else {
            throw new RuntimeException("question not found");
        }
    }

    public void modify(Question question, String content) {
        question.setModifyDate(LocalDateTime.now());
        question.setContent(content);
//        Question modifyQuestion = question.toBuilder()
//                .content(content)
//                .build();

        questionRepository.save(question);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }
}
