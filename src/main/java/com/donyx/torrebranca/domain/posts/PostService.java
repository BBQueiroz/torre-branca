package com.donyx.torrebranca.domain.posts;

import com.donyx.torrebranca.domain.posts.dtos.NewPostDTO;
import com.donyx.torrebranca.domain.posts.dtos.UpdatePostDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public Post Create(NewPostDTO dto){
        Post post = new Post(dto);
        return postRepository.save(post);
    }
    public List<Post> list(){
        return postRepository.findAll();
    }

    public Post findOne(Long id){
        return postRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void update(Long id, UpdatePostDTO updatePostDTO){

    }

    @Transactional
    public void delete(Long id){
        Post post = postRepository.findById(id).orElseThrow();

        post.inativar();

    }
}
