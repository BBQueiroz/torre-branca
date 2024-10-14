package com.donyx.torrebranca.controllers;

import com.donyx.torrebranca.domain.posts.Post;
import com.donyx.torrebranca.domain.posts.PostService;
import com.donyx.torrebranca.domain.posts.dtos.NewPostDTO;
import com.donyx.torrebranca.domain.posts.dtos.PostDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/post")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping("/newPost")
    public ResponseEntity<PostDTO> create(@RequestBody @Valid NewPostDTO dto, UriComponentsBuilder uriComponentsBuilder){
        Post post = service.create(dto);
        PostDTO postDTO = new PostDTO(post);
        URI uri = uriComponentsBuilder.path("/post/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).body(postDTO);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<PostDTO>> findAll(){
        List<Post> posts = service.list();
        List<PostDTO> postDTOList = posts.stream().map(PostDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(postDTOList);
    }

}
