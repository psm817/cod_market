package com.cod.market.question.controller;

import com.cod.market.member.entity.Member;
import com.cod.market.member.service.MemberService;
import com.cod.market.product.entity.Product;
import com.cod.market.product.service.ProductService;
import com.cod.market.question.entity.Question;
import com.cod.market.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
