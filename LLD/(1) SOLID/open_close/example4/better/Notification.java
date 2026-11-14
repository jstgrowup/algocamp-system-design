package open_close.example4.better;

/**
 * The abstraction every notification type implements (the compiler enforces it). Compare with single_responsibility/example4/problem, which uses a switch.
 */
public interface Notification {

    void sendMessage(String message);
}
