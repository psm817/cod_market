package com.cod.market.product.controller;

import com.cod.market.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/adm/product")
public class admProductController {
    private final ProductService productService;

    @GetMapping("/create")
    public String create() {
        return "adm/product/create";
    }

    @PostMapping("/create")
    public String createContent(@RequestParam(value = "name", defaultValue = "") String name,
                                @RequestParam(value = "price", defaultValue = "0") int price,
                                @RequestParam(value = "description", defaultValue = "") String description)
    {
        productService.create(name, description, price);

        return "adm/product/create";
    }
}
