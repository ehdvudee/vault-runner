package net.glaso.key.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CryptoParam {

    private String base64Plaintext;
    private String base64Ciphertext;

}
