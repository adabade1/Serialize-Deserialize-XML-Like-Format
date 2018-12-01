package genericCheckpointing.util;

import java.io.IOException;

public interface Strategy {
     SerializableObject processInput(SerializableObject sObject) throws IOException;
}
