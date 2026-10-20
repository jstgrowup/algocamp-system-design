import { Notification, NotificationType } from "./notification";

// This class is modified every time we introduce or remove a type of notification -> violates OCP
export class NotificationSender {
  private notification = new Notification();

  sendNotifications(types: NotificationType[], message: string) {
    for (const type of types) {
      switch (type) {
        case NotificationType.SMS:
          this.notification.sendSMSNotification(message);
          break;
        case NotificationType.EMAIL:
          this.notification.sendEmailNotification(message);
          break;
        case NotificationType.PUSH:
          this.notification.sendPushNotification(message);
          break;
        case NotificationType.WHATSAPP:
          this.notification.sendWhatsAppNotification(message);
          break;
        default:
          throw new Error("Unknown notification type: " + type);
      }
    }
  }
}
