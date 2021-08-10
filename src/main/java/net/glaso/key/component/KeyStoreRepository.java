package net.glaso.key.component;

import net.glaso.key.domain.KeyStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyStoreRepository extends JpaRepository<KeyStore, Long> {

}
