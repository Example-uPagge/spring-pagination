package dev.struchkov.example;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostRepository repository;

    @GetMapping
    public Page<Post> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") Integer limit
    ) {
        return repository.findAll(PageRequest.of(offset, limit));
    }

    @GetMapping("exampleSort")
    public Page<Post> getAllAndSort(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit,
            @RequestParam("sort") String sortField
    ) {
        return repository.findAll(
                PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, sortField))
        );
    }

    @GetMapping("exampleEnumSort")
    public Page<Post> getAllAndEnumSort(
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit,
            @RequestParam("sort") PostSort sort
    ) {
        return repository.findAll(
                PageRequest.of(offset, limit, sort.getSortValue())
        );
    }

    @GetMapping("exampleJpql")
    public Page<Post> getAllJpql(
            @RequestParam("title") String title,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return repository.findAllByTitleJpql(title, PageRequest.of(offset, limit));
    }

    @GetMapping("exampleSqlNative")
    public Page<Post> getAllSqlNative(
            @RequestParam("title") String title,
            @RequestParam("offset") Integer offset,
            @RequestParam("limit") Integer limit
    ) {
        return repository.findAllByTitleNative(title, PageRequest.of(offset, limit));
    }

}
