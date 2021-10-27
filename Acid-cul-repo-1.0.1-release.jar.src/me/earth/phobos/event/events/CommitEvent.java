package me.earth.phobos.event.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import me.earth.phobos.event.EventPriority;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CommitEvent {
  EventPriority priority() default EventPriority.NONE;
}


/* Location:              C:\Users\42060\Downloads\Acid_4\Acid-cul-repo-1.0.1-release.jar!\me\earth\phobos\event\events\CommitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */