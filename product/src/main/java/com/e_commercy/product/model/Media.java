package com.e_commercy.product.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "media")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String fileType;
    private String filePath;
}
