package net.glaso.business.key;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyStoreRepository extends JpaRepository<KeyStore, Long> {

}
