export enum NotificationType {
  SMS,
  EMAIL,
  PUSH,
  WHATSAPP,
}
export class Notification {
  sendSMSNotification(message: String) {
    console.log("MEssage", message);
  }
  sendEmailNotification(message: string) {
    console.log("Email Notification", message);
  }
  sendPushNotification(message: string) {
    console.log("Push ", message);
  }
  sendWhatsAppNotification(message: string) {
    console.log("message", message);
  }
}
