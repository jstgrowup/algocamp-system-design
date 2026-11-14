package example4.better;

/**
 * The abstraction. It is owned by the high-level side: it says what the
 * service NEEDS ("something that can send a message"), not how it is done.
 * It has one method, so a lambda can implement it.
 */
public interface MessageSender {

    void send(String to, String message);
}
