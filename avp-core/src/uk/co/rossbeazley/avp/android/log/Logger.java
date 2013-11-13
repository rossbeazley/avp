package uk.co.rossbeazley.avp.android.log;

public interface Logger {

    void debug(String message);

    Logger NULL = new Logger() {
        @Override
        public void debug(String message) {
        }
    };
}
