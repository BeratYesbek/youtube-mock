package beratyesbek.youtube.mock.model.common;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    public static final Boolean DEFAULT_DELETED_VALUE = false;

    @Id
    @UuidGenerator
    @Column(name = "ID", nullable = false, updatable = false)
    private String id;

    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private OffsetDateTime updatedAt;

    @Column(name = "DELETED", nullable = false)
    private Boolean deleted = DEFAULT_DELETED_VALUE;

    @PrePersist
    protected void prePersist() {
        createdAt = OffsetDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        updatedAt = OffsetDateTime.now();
    }

}
