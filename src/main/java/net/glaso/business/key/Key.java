package net.glaso.business.key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "key")
public class Key implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_id")
    private int keyId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "key_store_id")
    private int keyStoreId;

    @Column(name = "key_name")
    private String keyName;

    @Column(name = "key_tag")
    private String keyTag;

}
