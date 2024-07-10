package edu.school21.printer.logic;

public class IncorrectColorException extends RuntimeException {
    public IncorrectColorException() {
        super("Illegal color specified!");
    }
}
