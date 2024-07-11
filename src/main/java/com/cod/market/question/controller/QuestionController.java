package com.cod.market.question.controller;

import com.cod.market.member.entity.Member;
import com.cod.market.member.service.MemberService;
import com.cod.market.product.entity.Product;
import com.cod.market.product.service.ProductService;
import com.cod.market.question.entity.Question;
import com.cod.market.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;
    private final MemberService memberService;
    private final ProductService productService;

    @PostMapping("/create/{id}")
    public String create(Principal principal, @PathVariable("id") Long id, @RequestParam("content") String content) {
        Member member = memberService.findByUsername(principal.getName());
        Product product = productService.getProduct(id);
        this.questionService.create(content, member, product);

        return "redirect:/product/detail/%s".formatted(id);
    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id, Model model) {
        Question question = questionService.getQuestion(id);

        model.addAttribute("question", question);

        return "question/modify";
    }

    @PostMapping("/modify/{id}")
    public String modify(Principal principal, @PathVariable("id") Long id, @RequestParam("content") String content) {
        Question question = questionService.getQuestion(id);
        questionService.modify(question, content);
        long productId = question.getProduct().getId();

        return "redirect:/product/detail/%s".formatted(productId);
    }
}
