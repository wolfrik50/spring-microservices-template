package io.wulfcodes.user.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import io.wulfcodes.user.model.data.UserData;
import io.wulfcodes.user.model.entity.User;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "userId", target = "id")
    User toEntity(UserData userData);

    @Mapping(source = "id", target = "userId")
    UserData toData(User user);

    List<UserData> toDataList(Iterable<User> users);

}
