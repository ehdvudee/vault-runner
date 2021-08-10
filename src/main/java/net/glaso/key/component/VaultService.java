package net.glaso.key.component;

import lombok.AllArgsConstructor;
import net.glaso.key.domain.Key;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.TransitKeyType;
import org.springframework.vault.support.VaultTransitKeyConfiguration;
import org.springframework.vault.support.VaultTransitKeyCreationRequest;

import javax.xml.bind.DatatypeConverter;
import java.util.*;

@AllArgsConstructor
@Service
public class VaultService {

    private final VaultOperations vaultoperations;

    public void createKey(Key key) {
        VaultTransitKeyCreationRequest.VaultTransitKeyCreationRequestBuilder reqBuilder = VaultTransitKeyCreationRequest.builder().type(key.getKeyType().getKeyType());

        if (key.isConvergentEncryption()) {
            reqBuilder.convergentEncryption(true).derived(true);
        }
        if (key.isExportable()) {
            reqBuilder.exportable(true);
        }

        VaultTransitKeyCreationRequest vaultTransitKeyCreationRequest = reqBuilder.build();
        vaultoperations.opsForTransit().createKey(key.getKeyTag(), vaultTransitKeyCreationRequest);
    }

    public void destroyKey(String keyTag) {
        VaultTransitKeyConfiguration vaultTransitKeyConfiguration = VaultTransitKeyConfiguration.builder().deletionAllowed(true).build();
        vaultoperations.opsForTransit().configureKey(keyTag, vaultTransitKeyConfiguration);

        vaultoperations.opsForTransit().deleteKey(keyTag);
    }

    public String exportKey(String keyIdentifier) {
        String path = "/transit/export/encryption-key/" + keyIdentifier;
        return vaultoperations.opsForTransit().exportKey(keyIdentifier, TransitKeyType.SIGNING_KEY).getKeys().toString();
    }

    public String encrypt(String keyIdentifier, String base64Plaintext) {
        Map<String, Object> reqMap = new HashMap<>();
        List<Map<String, String>> cryptoList = new ArrayList<>();
        Map<String, String> encMap = new HashMap<>();
        for (int i=0; i<3; i++) {
            encMap.put("plaintext", base64Plaintext);
            encMap.put("context", DatatypeConverter.printBase64Binary(DatatypeConverter.parseHexBinary("a1a1a1")));
            encMap.put("nonce", "oaGhoaGhoaGhoaGh");

            cryptoList.add(encMap);
        }
        reqMap.put("batch_input",cryptoList);
        String path = "/transit/encrypt/" + keyIdentifier;
        return (String) Objects.requireNonNull(vaultoperations.write(path, reqMap))
                .getRequiredData()
//                .get("batch_results")
                .toString();


//        reqMap.put("plaintext", base64Plaintext);
//        reqMap.put("context", DatatypeConverter.printBase64Binary(DatatypeConverter.parseHexBinary("a1a1a1")));
//        reqMap.put("nonce", DatatypeConverter.printBase64Binary(DatatypeConverter.parseHexBinary("a1a1a1a1a1a1a1a1a1a1a1a1")));
//        String path = "/transit/encrypt/" + keyIdentifier;
//
//        return (String) Objects.requireNonNull(vaultoperations.write(path, reqMap))
//                .getRequiredData()
//                .get("ciphertext");
    }

    public String decrypt(String keyIdentifier, String formedCiphertext) {
        Map<String, String> reqMap = Collections.singletonMap("ciphertext", formedCiphertext);
        String path = "/transit/decrypt/" + keyIdentifier;

        return (String) Objects.requireNonNull(vaultoperations.write(path, reqMap))
                .getRequiredData()
                .get("plaintext");
    }
}
