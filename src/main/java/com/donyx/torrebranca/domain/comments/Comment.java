package com.donyx.torrebranca.domain.comments;

import com.donyx.torrebranca.domain.posts.Post;
import com.donyx.torrebranca.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_COMMENTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_POST_SEQ")
    @SequenceGenerator(name = "TB_POST_SEQ", sequenceName = "TB_POST_SEQ", allocationSize = 1)
    @Column(name = "ID_POST")
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO")
    private User autor;

    @Column(name = "conteudo", length = 5000)
    private String conteudo;

    @Column(name = "CREATE_DATETIME")
    private LocalDateTime createDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private Post post;
}
