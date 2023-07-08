package br.edu.barriga.service.external;

import br.edu.barriga.domain.Conta;

public interface ContaEvent {
    public enum EventType {
        CREATED,
        UPDATED,
        DELETED
    }

    void dispatch(Conta conta, EventType type);
}
