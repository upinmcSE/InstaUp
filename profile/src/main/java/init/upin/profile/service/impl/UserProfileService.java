package init.upin.profile.service.impl;

import init.upin.profile.dto.request.ProfileCreationRequest;
import init.upin.profile.dto.request.ProfileUpdateRequest;
import init.upin.profile.dto.response.UserProfileResponse;
import init.upin.profile.entity.UserProfile;
import init.upin.profile.mapper.UserProfileMapper;
import init.upin.profile.repository.UserProfileRepository;
import init.upin.profile.service.IUserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserProfileService implements IUserProfileService {
    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse createProfile(ProfileCreationRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        userProfile = userProfileRepository.save(userProfile);

        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @Override
    public UserProfileResponse updateProfile(ProfileUpdateRequest request) {
        return null;
    }

    @Override
    public UserProfileResponse getProfile(String id) {
        UserProfile userProfile =
                userProfileRepository.findById(id).orElseThrow(() -> new RuntimeException("Profile not found"));

        return userProfileMapper.toUserProfileResponse(userProfile);
    }
}
