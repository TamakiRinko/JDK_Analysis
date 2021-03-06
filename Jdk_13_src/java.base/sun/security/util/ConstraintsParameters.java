/*
 * Copyright (c) 2016, 2019, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package sun.security.util;

import sun.security.validator.Validator;

import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Timestamp;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * This class contains parameters for checking against constraints that extend
 * past the publicly available parameters in java.security.AlgorithmConstraints.
 *
 * This is currently passed between PKIX, AlgorithmChecker,
 * and DisabledAlgorithmConstraints.
 */
public class ConstraintsParameters {
    /*
     * The below 3 values are used the same as the permit() methods
     * published in java.security.AlgorithmConstraints.
     */
    // Algorithm string to be checked against constraints
    private final String algorithm;
    // AlgorithmParameters to the algorithm being checked
    private final AlgorithmParameters algParams;
    // Public Key being checked against constraints
    private final Key publicKey;

    /*
     * New values that are checked against constraints that the current public
     * API does not support.
     */
    // A certificate being passed to check against constraints.
    private final X509Certificate cert;
    // This is true if the trust anchor in the certificate chain matches a cert
    // in AnchorCertificates
    private final boolean trustedMatch;
    // PKIXParameter date
    private final Date pkixDate;
    // Timestamp of the signed JAR file
    private final Timestamp jarTimestamp;
    private final String variant;

    public ConstraintsParameters(X509Certificate c, boolean match,
            Date pkixdate, Timestamp jarTime, String variant) {
        cert = c;
        trustedMatch = match;
        pkixDate = pkixdate;
        jarTimestamp = jarTime;
        this.variant = (variant == null ? Validator.VAR_GENERIC : variant);
        algorithm = null;
        algParams = null;
        publicKey = null;
    }

    public ConstraintsParameters(String algorithm, AlgorithmParameters params,
            Key key, String variant) {
        this.algorithm = algorithm;
        algParams = params;
        this.publicKey = key;
        cert = null;
        trustedMatch = false;
        pkixDate = null;
        jarTimestamp = null;
        this.variant = (variant == null ? Validator.VAR_GENERIC : variant);
    }


    public ConstraintsParameters(X509Certificate c) {
        this(c, false, null, null,
                Validator.VAR_GENERIC);
    }

    public ConstraintsParameters(Timestamp jarTime) {
        this(null, false, null, jarTime, Validator.VAR_GENERIC);
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public AlgorithmParameters getAlgParams() {
        return algParams;
    }

    public Key getPublicKey() {
        return publicKey;
    }
    // Returns if the trust anchor has a match if anchor checking is enabled.
    public boolean isTrustedMatch() {
        return trustedMatch;
    }

    public X509Certificate getCertificate() {
        return cert;
    }

    public Date getPKIXParamDate() {
        return pkixDate;
    }

    public Timestamp getJARTimestamp() {
        return jarTimestamp;
    }

    public String getVariant() {
        return variant;
    }
}
