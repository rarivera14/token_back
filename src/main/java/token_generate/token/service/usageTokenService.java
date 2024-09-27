package token_generate.token.service;

import token_generate.token.models.UsageToken;
import token_generate.token.models.User;
import token_generate.token.utils.GenericException;

import java.util.List;

public interface usageTokenService {
    public UsageToken updateUsageToken(Long user_id, Long token_id) throws GenericException;
}
