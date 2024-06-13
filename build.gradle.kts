plugins {
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

val edcVersion = "0.3.1"

dependencies {
    // Core Connector dependencies
    // Boot: Provides BaseRuntime and boots all extensions
    implementation("org.eclipse.edc:boot:${edcVersion}") 

    // Core: Registers default and core services
    implementation("org.eclipse.edc:connector-core:${edcVersion}")

    // Jersey: Provide a WebService implementation using Jersey
    implementation("org.eclipse.edc:jersey-core:${edcVersion}")

    // Observability: Provides /check/health and other health endpoints
    implementation("org.eclipse.edc:api-observability:${edcVersion}")

    // File based connector configuration
    implementation("org.eclipse.edc:configuration-filesystem:${edcVersion}")

    // ---
    
    // Control Plane Components
    implementation("org.eclipse.edc:control-plane-core:${edcVersion}")
    implementation("org.eclipse.edc:control-plane-api-client:${edcVersion}")
    implementation("org.eclipse.edc:control-plane-api:${edcVersion}")

     // Management API (assets, contracts etc.)
    implementation("org.eclipse.edc:management-api:${edcVersion}")
    implementation("org.eclipse.edc:management-api-configuration:${edcVersion}")

    // Dataspace Protocol
    implementation("org.eclipse.edc:dsp:${edcVersion}")

    // Data Plane Selector
    implementation("org.eclipse.edc:data-plane-selector-core:${edcVersion}")
    implementation("org.eclipse.edc:data-plane-selector-api:${edcVersion}")
    implementation("org.eclipse.edc:data-plane-selector-client:${edcVersion}")

    // Data Plane
    implementation("org.eclipse.edc:data-plane-api:${edcVersion}")
    implementation("org.eclipse.edc:data-plane-core:${edcVersion}")
    implementation("org.eclipse.edc:data-plane-http:${edcVersion}")

    // Transfer
    implementation("org.eclipse.edc:transfer-data-plane:${edcVersion}")
    implementation("org.eclipse.edc:transfer-pull-http-receiver:${edcVersion}")

    // ---

    // use file based fault for private key
    // implementation("org.eclipse.edc:vault-filesystem:${edcVersion}")

    implementation(project("extensions:common:vault:vault-filesystem-pem"))

    // DID (instead of iam.mock)
    implementation("org.eclipse.edc:identity-did-core:${edcVersion}")
    implementation("org.eclipse.edc:identity-did-service:${edcVersion}")
    implementation("org.eclipse.edc:identity-did-web:${edcVersion}")

    // Identity Hub (provides credentialsVerifier for DID?)
    implementation("org.eclipse.edc:identity-hub:${edcVersion}")
    implementation("org.eclipse.edc:identity-hub-credentials-verifier:${edcVersion}")
    implementation("org.eclipse.edc:identity-hub-api:${edcVersion}")
    implementation("org.eclipse.edc:identity-hub-verifier-jwt:${edcVersion}")
    implementation("org.eclipse.edc:identity-hub-credentials-jwt:${edcVersion}")
    implementation("org.eclipse.edc:self-description-api:${edcVersion}")

    // Federated Catalog
    // implementation("org.eclipse.edc:registration-service-client:${edcVersion}")
    // implementation("org.eclipse.edc:federated-catalog-spi:${edcVersion}")
    // implementation("org.eclipse.edc:federated-catalog-core:${edcVersion}")
    // implementation("org.eclipse.edc:federated-catalog-api:${edcVersion}")
}

application {
    mainClass.set("org.eclipse.edc.boot.system.runtime.BaseRuntime")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    exclude("**/pom.properties", "**/pom.xm")
    mergeServiceFiles()
    archiveFileName.set("Connector.jar")
}
