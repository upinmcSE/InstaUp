package init.upin.profile.controller;

import init.upin.profile.dto.request.ProfileCreationRequest;
import init.upin.profile.dto.response.UserProfileResponse;
import init.upin.profile.service.IUserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUserProfileController {
    IUserProfileService userProfileService;

//    @PostMapping("/internal/users")
//    UserProfileResponse createProfile(@RequestBody ProfileCreationRequest request) {
//        return userProfileService.createProfile(request);
//    }
}
