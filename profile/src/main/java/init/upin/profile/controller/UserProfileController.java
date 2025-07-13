package init.upin.profile.controller;

import init.upin.profile.dto.response.ApiResponse;
import init.upin.profile.dto.response.UserProfileResponse;
import init.upin.profile.service.IUserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    IUserProfileService userProfileService;

    @GetMapping("/users/{profileId}")
    ApiResponse<UserProfileResponse> getProfile(@PathVariable String profileId) {
        return ApiResponse.<UserProfileResponse>builder()
                .message("User profile retrieved successfully")
                .result(userProfileService.getProfile(profileId))
                .build();
    }
}
