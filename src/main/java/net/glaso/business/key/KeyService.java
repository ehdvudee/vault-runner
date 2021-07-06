package net.glaso.business.key;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class KeyService {

    private final KeyStoreRepository keyStoreRepository;

    public KeyStore insertKeyStore(KeyStore keyStore) {
        return this.keyStoreRepository.save(keyStore);
    }


}
