package io.wulfcodes.common.model.entity;

import java.lang.reflect.Field;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("users")
@Data @NoArgsConstructor @AllArgsConstructor
public class User implements Persistable<String> {

    @Transient
    private boolean isNewUser;

    @Id
    @Column("id")
    private String id;

    @Column("name")
    private String name;

    @Column("email")
    private String email;

    @Column("about")
    private String about;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNewUser;
    }
}
