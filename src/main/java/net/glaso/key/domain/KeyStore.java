package net.glaso.key.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "key_store")
public class KeyStore {

    @Id
    @Column(name = "key_store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int keyStoreId;

    @Column(name = "key_store_name")
    private String keyStoreName;

}
