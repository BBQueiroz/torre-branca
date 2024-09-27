package com.donyx.torrebranca.domain.posts;

import com.donyx.torrebranca.domain.users.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_POSTS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Post {

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

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "conteudo", length = 5000)
    private String conteudo;

    @Column(name = "DATA_HORA_INICIO")
    private LocalDateTime dataHoraInicio;

    @ElementCollection
    @CollectionTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    private List<String> tags;
}
