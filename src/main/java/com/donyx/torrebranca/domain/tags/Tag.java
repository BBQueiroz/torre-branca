package com.donyx.torrebranca.domain.tags;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_TAGS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_TAG_SEQ")
    @SequenceGenerator(name = "TB_TAG_SEQ", sequenceName = "TB_TAG_SEQ", allocationSize = 1)
    @Column(name = "ID_TAG")
    private Long id;

    @Column(name = "NOME")
    private String nome;

}
