package io.wulfcodes.user.repository;

import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import io.wulfcodes.user.model.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    @Query("SELECT u_email, u_name FROM users WHERE u_email = :email OR u_name = :name")
    Optional<User> findByEmailOrName(String email, String name);

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("""
        UPDATE 
            users 
        SET 
            u_name = :#{#user.name}, 
            u_email = :#{#user.email}, 
            u_about = :#{#user.about}
        WHERE 
            u_id = :userId 
    """)
    boolean update(@Param("userId") String userId, @Param("user") User user);

    @Modifying
    @Query("""
        UPDATE 
            users target
        SET
            target.u_name = COALESCE(:#{#source.name}, target.u_name),
            target.u_email = COALESCE(:#{#source.email}, target.u_email),
            target.u_about = COALESCE(:#{#source.about}, target.u_about)
        WHERE 
            target.u_id = :#{#source.userId}
    """)
    boolean modify(@Param("source") User source);

}
