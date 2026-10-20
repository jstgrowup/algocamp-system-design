import { Notification } from "./notification";

export class PushNotification implements Notification {
  sendMessage(message: string) {
    console.log("Push notification: " + message);
  }
}
