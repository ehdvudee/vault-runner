package net.glaso.key;

import lombok.AllArgsConstructor;
import net.glaso.common.code.EnumErrCode;
import net.glaso.common.excpetions.VaultRunnerException;
import net.glaso.key.component.KeyService;
import net.glaso.key.domain.CryptoParam;
import net.glaso.key.domain.Key;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@AllArgsConstructor
@RestController
@RequestMapping("keys")
public class KeyController {

    private final KeyService keyService;

//    @PostMapping("key-store")
//    public ResponseEntity<KeyStore> createKeyStore(@RequestBody KeyStore keyStore)  {
//        keyStore = this.keyService.insertKeyStore(keyStore);
//
//        return ResponseEntity.ok(keyStore);
//    }

    @PostMapping("/createKey")
    public ResponseEntity<?> createKey(@RequestBody Key key) {
        if (key.getKeyType() == null) {
            throw new VaultRunnerException(EnumErrCode.ERR_ILLEGAL_ARGUMENT, EnumErrCode.ERR_ILLEGAL_ARGUMENT.getErrMsg() + " - KeyType is invalid");
        }
        if (ObjectUtils.isEmpty(key.getKeyName())) {
            throw new VaultRunnerException(EnumErrCode.ERR_ILLEGAL_ARGUMENT, EnumErrCode.ERR_ILLEGAL_ARGUMENT.getErrMsg() + " - KeyName is invalid");
        }

        this.keyService.createKey(key);

        return ResponseEntity.ok(key);
    }

    @GetMapping("/{keyTag}")
    public ResponseEntity<?> getKey(@PathVariable("keyTag")String keyTag) {
        String ciphertext = this.keyService.getKey(keyTag);

        return ResponseEntity.ok(Collections.singletonMap("ciphertext", ciphertext));
    }

    @PostMapping("/{keyTag}/encrypt")
    public ResponseEntity<?> encrypt(@PathVariable("keyTag")String keyTag, @RequestBody CryptoParam cryptoParam) {
        String ciphertext = this.keyService.encrypt(keyTag, cryptoParam);

        return ResponseEntity.ok(Collections.singletonMap("ciphertext", ciphertext));
    }

}
