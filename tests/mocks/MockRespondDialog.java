package tests.mocks;

import monopoly.AcuerdosComerciales;
import monopoly.RespuestaDeDialogos;

public class MockRespondDialog implements RespuestaDeDialogos {
    public MockRespondDialog(AcuerdosComerciales deal) {}

    @Override
    public boolean getResponse() {
        return true;
    }
}
