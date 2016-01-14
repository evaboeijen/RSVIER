package annotations;

import java.lang.annotation.*;

//@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Id {
	
	String value() default "";

}
