package de.sfkl.edc.vault.filesystem.pem;

import org.eclipse.edc.runtime.metamodel.annotation.BaseExtension;
import org.eclipse.edc.runtime.metamodel.annotation.Extension;
import org.eclipse.edc.runtime.metamodel.annotation.Provider;
import org.eclipse.edc.runtime.metamodel.annotation.Provides;
import org.eclipse.edc.spi.EdcException;
import org.eclipse.edc.spi.security.CertificateResolver;
import org.eclipse.edc.spi.security.PrivateKeyResolver;
import org.eclipse.edc.spi.security.Vault;
import org.eclipse.edc.spi.system.ServiceExtension;
import org.eclipse.edc.spi.system.ServiceExtensionContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

@BaseExtension
@Provides({PrivateKeyResolver.class, CertificateResolver.class})
@Extension(value = FSPVaultExtension.NAME)
public class FSPVaultExtension implements ServiceExtension {
    public static final String NAME = "FS PEM Fake Vault";

    public static final String PRIVATE_KEY_FILE = "sfkl.pem.file.private";
    public static final String CERT_KEY_FILE = "sfkl.pem.file.certificate";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public void initialize(ServiceExtensionContext context) {
        String privatePemFile = context.getSetting(PRIVATE_KEY_FILE, "private-key.pem");
        String certPemFile = context.getSetting(CERT_KEY_FILE, "cert.pem");

        String connectorId = context.getConnectorId();

        String privateKey = null;
        String certificate = null;

        try {
            privateKey = Files.readString(Paths.get(privatePemFile), Charset.defaultCharset());
            certificate = Files.readString(Paths.get(certPemFile), Charset.defaultCharset());
        } catch (IOException e) {
            System.err.println("Needs private-key and cert to start");
            throw new EdcException(e);
        }

        context.registerService(PrivateKeyResolver.class, new FSPPrivateKeyResolver(privateKey, connectorId));
        context.registerService(CertificateResolver.class, new FSPCertificateResolver(certificate, connectorId));
    }

    @Provider
    public Vault vault(ServiceExtensionContext context) {
        return new FSPVault();
    }
}