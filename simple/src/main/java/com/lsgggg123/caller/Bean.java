package com.lsgggg123.caller;

public class Bean {

    public static final Bean INSTANCE =
            new Bean(1) {
                {
                    System.out.println(this.getClass());
                }
            };
    private final int id;
    
    public Bean(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public static void main(String[] args) {
        System.out.println("main");
    }
}