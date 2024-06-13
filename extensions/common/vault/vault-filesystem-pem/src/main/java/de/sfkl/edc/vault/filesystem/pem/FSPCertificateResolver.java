package de.sfkl.edc.vault.filesystem.pem;

import org.eclipse.edc.spi.EdcException;
import org.eclipse.edc.spi.security.CertificateResolver;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateFactory;

public class FSPCertificateResolver implements CertificateResolver {
    private X509Certificate certificate;
    private final String connectorId;

    public FSPCertificateResolver(String certificate, String connectorId) {
        this.connectorId = connectorId;

        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            this.certificate = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(certificate.getBytes()));
        } catch(Exception e) {
            System.err.println("Could not read certificate file");
            e.printStackTrace();
            throw new EdcException(e);
        }
    }

    @Override
    public @Nullable X509Certificate resolveCertificate(String id) {
        if(this.connectorId.equals(id)) {
            return this.certificate;
        }
        return null;
    }
}
