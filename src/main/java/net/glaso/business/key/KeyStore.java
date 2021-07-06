package net.glaso.business.key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


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
    @NotBlank(message = "Name is mandatory")
    private String keyStoreName;

}
