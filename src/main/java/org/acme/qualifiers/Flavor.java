package org.acme.qualifiers;

@jakarta.inject.Qualifier
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.FIELD,
        java.lang.annotation.ElementType.PARAMETER, java.lang.annotation.ElementType.METHOD})
public @interface Flavor {
    double[] pcts() default {};

    final class Literal extends jakarta.enterprise.util.AnnotationLiteral<Flavor> implements Flavor {
        private final double[] pcts;

        public Literal(double... pcts) {
            this.pcts = pcts;
        }

        @Override
        public double[] pcts() {
            return pcts;
        }
    }
}