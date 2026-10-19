import { Notification } from "./notification";

// Depends on the Notification abstraction, not on concrete types.
// Adding or removing a notification type never touches this class.
export class NotificationSender {
  sendNotifications(notifications: Notification[], message: string) {
    for (const notification of notifications) {
      notification.sendMessage(message);
    }
  }
}
