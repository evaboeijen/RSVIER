package service;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface EntityJesse {

	Class<?> entityName();
}
