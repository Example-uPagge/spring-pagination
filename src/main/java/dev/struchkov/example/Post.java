package dev.struchkov.example;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
