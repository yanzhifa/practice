package com.ldy.java8.interfc;

import java.util.function.Supplier;

/**
 * Created by yanz3 on 5/3/18.
 */
public class InterfaceTest {

    private interface Defaultable {
        default String notRequired() {
            return "Default implementation";
        }
    }

    private static class DefaultableImpl implements Defaultable {
    }

    private static class OverridableImpl implements Defaultable {
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    private interface DefaultableFactory {
        // Interfaces now allow static methods
        static Defaultable create(Supplier<Defaultable> supplier ) {
            return supplier.get();
        }
    }

    public static void main( String[] args ) {
        Defaultable defaultable = DefaultableFactory.create( DefaultableImpl::new );
        System.out.println( defaultable.notRequired() );

        defaultable = DefaultableFactory.create( OverridableImpl::new );
        System.out.println( defaultable.notRequired() );
    }
}


