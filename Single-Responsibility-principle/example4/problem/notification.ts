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

// you might be thinking that segregating the notifications based on there service is a good idea like one class for sms and one for the email
// Now the issue is in the notification sender its a lot of ifelse tomorrow if i want to get rid of a notification sender i have to go to notification and remove that method as well
// segregating the classes is also not the best way
// here comes the open close principle
