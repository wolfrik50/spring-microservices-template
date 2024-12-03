package io.wulfcodes.user.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import io.wulfcodes.user.model.data.UserData;
import io.wulfcodes.user.model.entity.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserData userData);

    UserData toData(User user);

    List<UserData> toDataList(Iterable<User> users);
}
