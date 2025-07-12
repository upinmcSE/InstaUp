package init.upin.identity.mapper;

import init.upin.identity.dto.request.ProfileCreationRequest;
import init.upin.identity.dto.request.UserCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
