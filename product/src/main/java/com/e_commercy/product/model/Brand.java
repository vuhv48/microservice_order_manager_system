package com.e_commercy.product.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brand")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; //nike sport
    private String slug; //nike-sport
    private boolean isPublished;

}
