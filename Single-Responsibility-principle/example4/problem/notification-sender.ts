import { Notification, NotificationType } from "./notification";

export class NotificationSender {
  private notification = new Notification();

  sendNotification(type: NotificationType, message: string) {
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
