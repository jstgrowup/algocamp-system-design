import { Notification } from "./notification";

export class EmailNotification implements Notification {
  sendMessage(message: string) {
    console.log("Email notification: " + message);
  }
}
