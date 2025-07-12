package init.upin.identity.dto.response;

import java.util.Date;

public record TokenInfo(String token, Date expiryDate) {
}
