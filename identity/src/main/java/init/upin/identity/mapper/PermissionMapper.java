package init.upin.identity.mapper;

import init.upin.identity.dto.request.PermissionRequest;
import init.upin.identity.dto.response.PermissionResponse;
import init.upin.identity.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
