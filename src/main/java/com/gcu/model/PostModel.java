package com.gcu.model;

@Entity
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String text;
    private int postNumber;

    // Constructors, getters, and setters
}
