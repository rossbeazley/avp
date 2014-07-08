package uk.co.rossbeazley.avp.eventbus;

import uk.co.rossbeazley.avp.eventbus.FunctionWithParameter;

public interface PayloadFunction {
    void payload(FunctionWithParameter listener);
}
