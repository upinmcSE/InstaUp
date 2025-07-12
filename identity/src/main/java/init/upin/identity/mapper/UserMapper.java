package init.upin.identity.mapper;

import init.upin.identity.dto.request.UserCreationRequest;
import init.upin.identity.dto.request.UserUpdateRequest;
import init.upin.identity.dto.response.UserResponse;
import init.upin.identity.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
