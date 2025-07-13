package init.upin.profile.mapper;

import init.upin.profile.dto.request.ProfileCreationRequest;
import init.upin.profile.dto.response.UserProfileResponse;
import init.upin.profile.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile entity);
}
