package net.pd.aldaaya.common;

public class AldaayaException extends Exception {

    public AldaayaException(String msg) {
	super(msg);
    }

    public AldaayaException(String msg, Throwable cause) {
	super(msg, cause);
    }

    public AldaayaException(Exception e) {
	super(e);
    }

    /**
     *
     */
    private static final long serialVersionUID = -6913547944608757439L;

}
