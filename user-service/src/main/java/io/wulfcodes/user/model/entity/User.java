package io.wulfcodes.user.model.entity;

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
    @Column("u_id")
    private String id;

    @Column("u_name")
    private String name;

    @Column("u_email")
    private String email;

    @Column("u_about")
    private String about;

    public User modify(User that) {
        Field[] fields = that.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);

                Object thisValue = field.get(this);
                Object thatValue = field.get(that);

                if (Objects.nonNull(thatValue) && !Objects.equals(thatValue, thisValue))
                    field.set(this, thatValue);

            } catch (IllegalAccessException e) {

            } finally {
                field.setAccessible(false);
            }
        }

        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNewUser;
    }
}
