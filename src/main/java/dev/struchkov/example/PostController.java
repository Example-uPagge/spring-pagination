package dev.struchkov.example;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository repository;
    private final GeneratorPost generatorPost;

    @GetMapping("generate")
    public void generate() {
        generatorPost.generate();
    }

    @GetMapping
    public Page<Post> getAll(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return repository.findAll(PageRequest.of(offset, limit));
    }

}
