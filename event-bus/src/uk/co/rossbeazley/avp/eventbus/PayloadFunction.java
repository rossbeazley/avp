package uk.co.rossbeazley.avp.eventbus;

public interface PayloadFunction<Payload_Type> {
    void payload(FunctionWithParameter<Payload_Type> listener);
}
