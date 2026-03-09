package com.example.projeto_spring_boot.Entity;

@Entity
@Table(name = "todos")

public class Todo{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private boolean realizado;

}