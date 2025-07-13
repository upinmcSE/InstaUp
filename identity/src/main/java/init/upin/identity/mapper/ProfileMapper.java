package init.upin.identity.mapper;

import init.upin.identity.dto.request.ProfileCreationRequest;
import init.upin.identity.dto.request.UserCreationRequest;
import init.upin.identity.dto.response.UserProfileResponse;
import init.upin.identity.dto.response.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
    UserResponse toUserResponse(UserProfileResponse request);
}
