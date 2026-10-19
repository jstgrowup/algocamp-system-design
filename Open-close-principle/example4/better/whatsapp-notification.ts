import { Notification } from "./notification";

export class WhatsAppNotification implements Notification {
  sendMessage(message: string) {
    console.log("WhatsApp notification: " + message);
  }
}
