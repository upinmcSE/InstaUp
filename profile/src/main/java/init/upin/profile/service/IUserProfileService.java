package init.upin.profile.service;

import init.upin.profile.dto.request.ProfileCreationRequest;
import init.upin.profile.dto.request.ProfileUpdateRequest;
import init.upin.profile.dto.response.UserProfileResponse;

public interface IUserProfileService {
    UserProfileResponse createProfile(ProfileCreationRequest request);
    UserProfileResponse updateProfile(ProfileUpdateRequest request);
    UserProfileResponse getProfile(String id);
}
