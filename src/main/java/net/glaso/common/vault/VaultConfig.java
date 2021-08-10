package net.glaso.common.vault;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

@Configuration
public class VaultConfig extends AbstractVaultConfiguration {

    @Value("${vault.ip}")
    private String vaultIp;

    @Value("${vault.port}")
    private Integer vaultPort;

    @Value("${vault.token}")
    private String vaultToken;

    @Value("${vault.scheme}")
    private String scheme;

    @Override
    public VaultEndpoint vaultEndpoint() {
        VaultEndpoint vaultEndpoint = VaultEndpoint.create(vaultIp, vaultPort);
        vaultEndpoint.setScheme(scheme);

        return vaultEndpoint;
    }

    @Override
    public ClientAuthentication clientAuthentication() {
        return new TokenAuthentication(vaultToken);
    }
}
