package init.upin.identity.service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import init.upin.identity.dto.response.TokenInfo;
import init.upin.identity.entity.User;

import java.text.ParseException;

public interface ITokenService {
    TokenInfo generateToken(User user, String scope);
    SignedJWT verifyToken(String token) throws JOSEException, ParseException;
}
