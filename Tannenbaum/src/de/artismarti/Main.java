package de.artismarti;

public class Main {

    public static void main(String[] args) throws IllegalArgumentException {
        try {
            int size = Integer.parseInt(args[0]);
            boolean star = Boolean.parseBoolean(args[1]);
            System.out.println(Tannenbaum.draw(size, star));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Use command: Tannenbaum [size] ([true|false])");
        }
    }
}
