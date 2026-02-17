package com.Auditing_Tutorial.demo.controller;

import com.Auditing_Tutorial.demo.dto.PostDto;
import com.Auditing_Tutorial.demo.entity.PostEntity;
import com.Auditing_Tutorial.demo.service.PostService;
import com.Auditing_Tutorial.demo.service.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {



       private final PostService postService;


    @GetMapping
    public List<PostDto> getAllPost()
    {
        return postService.getAllPost();
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost)
    {
        return postService.createNewPost(inputPost);
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId)
    {
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public PostDto updatePost(@RequestBody PostDto inputPost, @PathVariable
                              Long postId)
    {
        return postService.updatePost(inputPost,postId);
    }


}
