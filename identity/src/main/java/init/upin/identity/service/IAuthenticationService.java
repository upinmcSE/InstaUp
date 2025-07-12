package init.upin.identity.service;

import com.nimbusds.jose.JOSEException;
import init.upin.identity.dto.request.AuthenticationRequest;
import init.upin.identity.dto.request.IntrospectRequest;
import init.upin.identity.dto.request.LogoutRequest;
import init.upin.identity.dto.request.RefreshRequest;
import init.upin.identity.dto.response.AuthenticationResponse;
import init.upin.identity.dto.response.IntrospectResponse;

import java.text.ParseException;

public interface IAuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request) throws JOSEException, ParseException;
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
