package dev.struchkov.example;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GeneratorPost {

    private final PostRepository postRepository;

    public void generate() {
        for (int i = 0; i < 10000; i++) {
            final Post post = new Post();
            post.setId(UUID.randomUUID());
            post.setTitle("Post " + i);
            post.setCreateOn(LocalDateTime.now().minusDays(1L).plusMinutes(i));
            postRepository.save(post);
        }
    }

}
