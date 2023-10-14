package com.aos.core.jpa;

import org.hibernate.dialect.Database;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolutionInfo;
import org.hibernate.engine.jdbc.dialect.spi.DialectResolver;
import org.springframework.cglib.proxy.Enhancer;

public class NoForeignKeyDialectResolver implements DialectResolver {
    @Override
    public Dialect resolveDialect(DialectResolutionInfo info) {
        for ( Database database : Database.values() ) {
            Dialect dialect = database.createDialect( info );
            if ( dialect != null ) {
                NoForeignKeyInterceptor myInterceptor = new NoForeignKeyInterceptor();
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(dialect.getClass());
                enhancer.setCallback(myInterceptor);
                return (Dialect) enhancer.create();
            }
        }

        return null;
    }
}