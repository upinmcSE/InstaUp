package init.upin.identity.service;

import init.upin.identity.dto.request.UserCreationRequest;
import init.upin.identity.dto.request.UserUpdateRequest;
import init.upin.identity.dto.response.UserResponse;

public interface IUserService {
    UserResponse createUser(UserCreationRequest request);
    UserResponse updateUser(String userId, UserUpdateRequest request);
    void deleteUser(String userId);
}
