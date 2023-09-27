package com.example.Splitwise_Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "created_at")
    private DateFormat createdAt;

    @Column(name = "updated_at")
    private DateFormat updatedAt;

}
