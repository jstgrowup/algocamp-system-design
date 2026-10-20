import { Notification } from "./notification";

export class SMSNotification implements Notification {
  sendMessage(message: string) {
    console.log("SMS notification: " + message);
  }
}
