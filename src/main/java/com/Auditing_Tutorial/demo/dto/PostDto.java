package com.Auditing_Tutorial.demo.dto;


import jakarta.persistence.*;
import lombok.*;



    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public class PostDto {

        private Long id;
        private String title;
        private String description;
    }

