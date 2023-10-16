package com.example.Splitwise_Backend.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
public class friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;
}
