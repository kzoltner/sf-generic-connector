package de.sfkl.edc.vault.filesystem.pem;

import org.eclipse.edc.spi.security.AbstractPrivateKeyResolver;
import org.jetbrains.annotations.Nullable;

public class FSPPrivateKeyResolver extends AbstractPrivateKeyResolver {
    private String privateKey;
    private String connectorId;

    public FSPPrivateKeyResolver(String privateKey, String connectorId) {
        this.privateKey = privateKey;
        this.connectorId = connectorId;
    }

    @Override
    public <T> @Nullable T resolvePrivateKey(String id, Class<T> keyType) {
        if(this.connectorId.equals((id))) {
            return keyType.cast(getParser(keyType).parse(this.privateKey));
        }
        return null;
    }
}
