package service;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface EntityJesse {

	String value() default "";
}
