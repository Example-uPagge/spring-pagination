package dev.struchkov.example;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    @Query("SELECT p FROM Post p")
    Slice<Post> findAllSlice(Pageable pageable);

    Page<Post> findAllByTitleLikeIgnoreCase(String titleLike, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.title like %:title%")
    Page<Post> findAllByTitleJpql(@Param("title") String title, Pageable pageable);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM POST WHERE TITLE LIKE %?1%",
            countQuery = "SELECT count(*) FROM POST WHERE TITLE LIKE %?1%"
    )
    Page<Post> findAllByTitleNative(String title, Pageable pageable);

}
