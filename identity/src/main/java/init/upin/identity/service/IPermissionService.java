package init.upin.identity.service;

import init.upin.identity.dto.request.PermissionRequest;
import init.upin.identity.dto.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse create(PermissionRequest request);
    List<PermissionResponse> getAll();
    void delete(String permissionId);
}
