package init.upin.identity.grpc;

import init.upin.identity.dto.request.ProfileCreationRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import init.upin.identity.grpc.gen.ProfileGrpcServiceGrpc;
import init.upin.identity.grpc.gen.UserProfileResponse;

@Service
public class ProfileGrpcClient {
    @GrpcClient("profile-service")
    private ProfileGrpcServiceGrpc.ProfileGrpcServiceBlockingStub profileStub;

    public UserProfileResponse createProfile(ProfileCreationRequest request) {
        init.upin.identity.grpc.gen.ProfileCreationRequest req = init.upin.identity.grpc.gen.ProfileCreationRequest.newBuilder()
                .setUserId(request.getUserId())
                .setFullName(request.getFullName())
                .build();
        return profileStub.createProfile(req);
    }
}
