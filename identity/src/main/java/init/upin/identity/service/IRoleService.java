package init.upin.identity.service;

import init.upin.identity.dto.request.RoleRequest;
import init.upin.identity.dto.response.RoleResponse;

import java.util.List;

public interface IRoleService {
    RoleResponse createRole(RoleRequest request);
    List<RoleResponse> getAllRoles();
    void deleteRole(String roleId);
}
