package token_generate.token.service.impl;

import org.springframework.stereotype.Service;
import token_generate.token.models.UsageToken;
import token_generate.token.repository.UsageTokenRepository;
import token_generate.token.repository.UserRepository;
import token_generate.token.service.usageTokenService;
import token_generate.token.utils.GenericException;

import java.util.Date;

@Service
public class usageTokenImpl implements usageTokenService {

    private final UsageTokenRepository usageTokenRepository;

    public usageTokenImpl(UsageTokenRepository usageTokenRepository) {
        this.usageTokenRepository = usageTokenRepository;
    }

    @Override
    public UsageToken updateUsageToken(Long user_id, Long token_id) throws GenericException {
        UsageToken response;
        try {
            response = usageTokenRepository.findHistoricalToken(user_id, token_id);

            if (response != null) {
                response.setUsedAt(new Date());
                usageTokenRepository.save(response);
            }else{
                UsageToken usageToken = new UsageToken();
                usageToken.setUserId(user_id);
                usageToken.setTokenId(token_id);
                usageToken.setUsedAt(new Date());
                usageTokenRepository.saveAndFlush(usageToken);
            }
        } catch (Exception e) {
            throw new GenericException(e.getMessage(), 500);
        }
        return response;
    }
}
