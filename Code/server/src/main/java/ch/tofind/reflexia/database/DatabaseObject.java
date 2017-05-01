package ch.tofind.reflexia.database;

import java.util.Date;

public interface DatabaseObject {
    public void update();
    public boolean equals(Object object);
    public int hashCode();
}

