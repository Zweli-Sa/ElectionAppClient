package com.pk.electionappclient.domain;

public enum Education {
    PODSTAWOWE,
    ZAWODOWE,
    ŚREDNIE,
    LICENCJAT,
    INŻYNIER,
    MAGISTER,
    DOKTOR,
    PROFESOR;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
