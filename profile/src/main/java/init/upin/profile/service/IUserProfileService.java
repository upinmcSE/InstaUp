package init.upin.profile.service;

//import init.upin.profile.dto.request.ProfileCreationRequest;
import init.upin.profile.dto.request.ProfileUpdateRequest;
import init.upin.profile.dto.response.UserProfileResponse;
import init.upin.profile.grpc.gen.ProfileCreationRequest;

public interface IUserProfileService {
    init.upin.profile.grpc.gen.UserProfileResponse createProfile(ProfileCreationRequest request);
    UserProfileResponse updateProfile(ProfileUpdateRequest request);
    UserProfileResponse getProfile(String id);
}
