package init.upin.profile.grpc;

import init.upin.profile.grpc.gen.ProfileCreationRequest;
import init.upin.profile.grpc.gen.ProfileGrpcServiceGrpc;
import init.upin.profile.grpc.gen.UserProfileResponse;
import init.upin.profile.service.IUserProfileService;
import io.grpc.stub.StreamObserver;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProfileGrpcServiceImpl extends ProfileGrpcServiceGrpc.ProfileGrpcServiceImplBase {
    IUserProfileService userProfileService;

    @Override
    public void createProfile(ProfileCreationRequest request, StreamObserver<UserProfileResponse> responseObserver) {
        var createdProfile = userProfileService.createProfile(request);

        // Gửi response cho client
        responseObserver.onNext(createdProfile);
        responseObserver.onCompleted();
    }
}
