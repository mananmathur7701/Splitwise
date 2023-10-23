package com.example.Splitwise_Backend.Friends.Entity;

import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;
}
