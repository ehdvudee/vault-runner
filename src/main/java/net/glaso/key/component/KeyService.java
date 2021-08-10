package net.glaso.key.component;

import lombok.AllArgsConstructor;
import net.glaso.key.domain.CryptoParam;
import net.glaso.key.domain.Key;
import net.glaso.key.domain.KeyStore;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class KeyService {

    private final KeyStoreRepository keyStoreRepository;
    private final VaultService vaultService;

    public KeyStore insertKeyStore(KeyStore keyStore) {
        return this.keyStoreRepository.save(keyStore);
    }

    public void createKey(Key key) {
        key.setKeyTag(UUID.randomUUID().toString());

        this.vaultService.createKey(key);
    }

    public String encrypt(String keyTag, CryptoParam param) {
        return this.vaultService.encrypt(keyTag, param.getBase64Plaintext());
    }

    public String getKey(String keyTag) {
        return this.vaultService.exportKey(keyTag);
    }
}
