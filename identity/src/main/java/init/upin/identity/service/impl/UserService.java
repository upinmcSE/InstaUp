package init.upin.identity.service.impl;

import init.upin.identity.constant.PredefinedRole;
import init.upin.identity.dto.request.UserCreationRequest;
import init.upin.identity.dto.request.UserUpdateRequest;
import init.upin.identity.dto.response.UserResponse;
import init.upin.identity.entity.Role;
import init.upin.identity.entity.User;
import init.upin.identity.exception.AppException;
import init.upin.identity.exception.ErrorCode;
import init.upin.identity.grpc.ProfileGrpcClient;
import init.upin.identity.mapper.ProfileMapper;
import init.upin.identity.mapper.UserMapper;
import init.upin.identity.repository.RoleRepository;
import init.upin.identity.repository.UserRepository;
import init.upin.identity.repository.http.ProfileClient;
import init.upin.identity.service.IUserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService implements IUserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    ProfileClient profileClient;
    ProfileMapper profileMapper;
    ProfileGrpcClient profileGrpcClient;

    @Override
    @Transactional
    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);

        user.setRoles(roles);
        user = userRepository.save(user);

        // call api internal
        var profileRequest = profileMapper.toProfileCreationRequest(request);
        profileRequest.setUserId(user.getId());
//        profileClient.createProfile(profileRequest);
        profileGrpcClient.createProfile(profileRequest);

        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
