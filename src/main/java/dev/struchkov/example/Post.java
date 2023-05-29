package dev.struchkov.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {

    @Id
    private UUID id;
    private String title;
    private LocalDateTime createOn;

}
