package com.vuhien.application.controller.shop;

import com.vuhien.application.entity.Comment;
import com.vuhien.application.entity.User;
import com.vuhien.application.googlesheet.SheetDataService;
import com.vuhien.application.model.request.CreateCommentPostRequest;
import com.vuhien.application.model.request.CreateCommentProductRequest;
import com.vuhien.application.security.CustomUserDetails;
import com.vuhien.application.service.CommentService;
import com.vuhien.application.utils.LoggerUtil;

import lombok.extern.log4j.Log4j2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.validation.Valid;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    
    @Autowired
    private SheetDataService sheetDataService;

    @PostMapping("/api/comments/post")
    public ResponseEntity<Object> createComment(@Valid @RequestBody CreateCommentPostRequest createCommentPostRequest) throws IOException, GeneralSecurityException {
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUser();  
        Comment comment = commentService.createCommentPost(createCommentPostRequest, user.getId());
        sheetDataService.writeComment(comment);
        return ResponseEntity.ok(comment);
    }

    @PostMapping("/api/comments/product")
    public ResponseEntity<Object> createComment(
            @Valid @RequestBody CreateCommentProductRequest createCommentProductRequest) throws IOException, GeneralSecurityException {
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getUser();    
        Comment comment = commentService.createCommentProduct(createCommentProductRequest, user.getId());
        sheetDataService.writeComment(comment);
        return ResponseEntity.ok(comment);
    }
}
