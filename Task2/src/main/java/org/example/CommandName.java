package org.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для маркировки классов команд.
 * Позволяет задать имя команды, по которому она будет доступна в фабрике.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CommandName {
    String value() default "UNKNOWN COMMAND";
}

