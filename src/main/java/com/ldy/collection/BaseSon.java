package com.ldy.collection;

/**
 * Created by yanz3 on 1/12/17.
 */
public class BaseSon extends Base {

    private String son1;

    public BaseSon(String item1, String item2, String son1) {
        super(item1, item2);
        this.son1 = son1;
    }

    @Override
    public String toString() {
        return "BaseSon{" +
                "son1='" + son1 + '\'' +
                "} " + super.toString();
    }

    public static void main(String[] args) {
        BaseSon son = new BaseSon("1", "A", "Son");
        Base base = son;
        System.out.println(base);
    }
}
