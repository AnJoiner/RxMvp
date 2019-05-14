package com.coder.mvp.retrofit;

public class ApiException extends Exception {

    public static final int SOCKET_TIMEOUT_CODE = 101;
    public static final int CONNECT_CODE = 102;
    public static final int UNKNOWN_HOST_CODE = 103;
    public static final int STATUS_CODE = 104;
    public static final int CONVERT_CODE = 105;


    private int code;
    private String message;

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}