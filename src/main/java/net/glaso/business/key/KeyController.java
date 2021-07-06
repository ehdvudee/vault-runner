package net.glaso.business.key;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("key")
public class KeyController {

    private final KeyService keyService;

    @PostMapping("key-store")
    public ResponseEntity<KeyStore> createKeyStore(@RequestBody KeyStore keyStore)  {
        keyStore = this.keyService.insertKeyStore(keyStore);
<<<<<<< .merge_file_a13568
        return ResponseEntity.ok(keyStore);             
=======

        return ResponseEntity.ok(keyStore);
>>>>>>> .merge_file_a17972
    }
}
