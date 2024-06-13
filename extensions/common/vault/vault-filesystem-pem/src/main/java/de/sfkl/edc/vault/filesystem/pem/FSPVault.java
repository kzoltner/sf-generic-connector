package de.sfkl.edc.vault.filesystem.pem;

import org.eclipse.edc.spi.result.Result;
import org.eclipse.edc.spi.security.Vault;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class FSPVault implements Vault {
    Map<String, String> secrets = new HashMap<String, String>();

    @Override
    public @Nullable String resolveSecret(String key) {
        return secrets.get(key);
    }

    @Override
    public Result<Void> storeSecret(String key, String value) {
        secrets.put(key, value);
        return Result.success();
    }

    @Override
    public Result<Void> deleteSecret(String key) {
        secrets.remove(key);
        return Result.success();
    }
}
