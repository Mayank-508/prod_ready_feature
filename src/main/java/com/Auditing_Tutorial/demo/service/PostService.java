package com.Auditing_Tutorial.demo.service;

import com.Auditing_Tutorial.demo.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPost();

    PostDto createNewPost(PostDto inputPost);

    PostDto getPostById(Long postId);

    PostDto updatePost(PostDto inputPost, Long postId);
}
