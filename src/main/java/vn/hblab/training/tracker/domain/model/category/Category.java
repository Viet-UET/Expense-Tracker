package vn.hblab.training.tracker.domain.model.category;

import java.util.UUID;

public class Category {
    private final UUID id;
    private final String name;
    private final String userId;

    public Category(String name, String userId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Category name không được trống");
        }
        this.id = UUID.randomUUID();
        this.name = name;
        this.userId = userId;
    }

    public Category(UUID id, String name, String userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public UUID getId()       { return id; }
    public String getName()   { return name; }
    public String getUserId() { return userId; }
}
