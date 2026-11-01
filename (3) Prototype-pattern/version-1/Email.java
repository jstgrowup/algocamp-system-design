public class Email {
    private String sender;
    private String reciever;
    private String subject;
    private String body;

    Email(String sender, String reciever, String subject, String body) {
        this.sender = sender;
        this.reciever = reciever;
        this.subject = subject;
        this.body = body;
    }

    // Copy Constructor
    // Builds a new Email from an existing one. It must read the values from the
    // `email` parameter. (`this.sender = sender;` would assign the field to itself
    // and leave it null, because there is no `sender` parameter here.)
    Email(Email email) {
        this.sender = email.sender;
        this.reciever = email.reciever;
        this.subject = email.subject;
        this.body = email.body;
    }

    // Prints the email so we can see what a copy contains.
    public void displayEmail() {
        System.out.println("From: " + sender + ", To: " + reciever
                + ", Subject: " + subject + ", Body: " + body);
    }

    // Getters and setters
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
