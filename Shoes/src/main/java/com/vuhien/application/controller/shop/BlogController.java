package com.vuhien.application.controller.shop;

import com.vuhien.application.entity.Post;
import com.vuhien.application.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {

    @Autowired
    private PostService postService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);
    @GetMapping("/tin-tuc")
    public String getPostPages(Model model,
                               @RequestParam(defaultValue = "1", required = false) Integer page) {

        Page<Post> posts = postService.getListPost(page);
        LOGGER.info("get list post"+ posts.getContent());
        model.addAttribute("posts", posts.getContent());
        model.addAttribute("totalPages", posts.getTotalPages());
        model.addAttribute("currentPage", posts.getPageable().getPageNumber() + 1);
        return "shop/post";
    }

    @GetMapping("/tin-tuc/{slug}/{id}")
    public String getPostDetail(Model model, @PathVariable long id){
        Post post = postService.getPostById(id);
        List<Post> postList = postService.getLatestPostsNotId(id);
        LOGGER.info("get lates post");
        model.addAttribute("post",post);
        model.addAttribute("postList",postList);

        return "shop/post-detail";
    }

}
