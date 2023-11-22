package com.example.Splitwise_Backend.Groups.DTO;

import java.sql.Timestamp;

public class GroupsDTO
{
    private int id;
    private String groupName;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public GroupsDTO(int id, String groupName, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.groupName = groupName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public GroupsDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
