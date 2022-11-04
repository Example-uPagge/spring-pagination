package dev.struchkov.example;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GeneratorPost {

    private final PostRepository postRepository;

    @Transactional
    @EventListener
    public void generate(ContextRefreshedEvent event) {
        final List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            final Post post = new Post();
            post.setId(UUID.randomUUID());
            post.setTitle("Post " + i);
            post.setCreateOn(LocalDateTime.now().minusDays(1L).plusMinutes(i));
            posts.add(post);
        }
        postRepository.saveAll(posts);
    }

}
