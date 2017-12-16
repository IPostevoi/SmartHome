package ru.sbt.mipt.oop.Exceptions;

/**
 * Created by bakla410 on 15.12.17.
 */
public class HomeException extends Exception {

    private String body;
    private Exception e;

    public HomeException(Exception e, String msg) {
        this.e = e;
        this.body = msg;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Exception getE() {
        return e;
    }

    public void setE(Exception e) {
        this.e = e;
    }
}
