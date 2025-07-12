package init.upin.identity.mapper;

import init.upin.identity.dto.request.RoleRequest;
import init.upin.identity.dto.response.RoleResponse;
import init.upin.identity.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
