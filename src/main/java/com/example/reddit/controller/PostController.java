package com.example.reddit.controller;

import com.example.reddit.dto.PostRequest;
import com.example.reddit.dto.PostResponse;
import com.example.reddit.service.PostService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** PostController */
@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@CrossOrigin
public class PostController {

  private final PostService postService;

  @PostMapping
  public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
    postService.save(postRequest);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<PostResponse>> getAllPosts() {
    return ResponseEntity.status(HttpStatus.OK).body(postService.getAllPosts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(id));
  }

  @GetMapping(params = "subredditId")
  public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@RequestParam Long subredditId) {
    return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsBySubreddit(subredditId));
  }

  @GetMapping(params = "username")
  public ResponseEntity<List<PostResponse>> getPostsByUsername(@RequestParam String username) {
    return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsByUsername(username));
  }
}
