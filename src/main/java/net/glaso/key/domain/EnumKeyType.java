package net.glaso.key.domain;


import com.fasterxml.jackson.annotation.JsonValue;

public enum EnumKeyType {
    AES_128("aes128-gcm96", 128),
    AES_256("aes128-gcm96", 256),
    RSA_2048("rsa-2048", 2048),
    ECDSA_p256("ecdsa-p256", 256);

    private String keyType;
    private int keyLength;

    EnumKeyType(String keyType, int keyLength) {
        this.keyType = keyType;
        this.keyLength = keyLength;
    }

    @JsonValue
    public String getKeyType() {
        return this.keyType;
    }
    public int getKeyLength() {
        return this.keyLength;
    }

}
