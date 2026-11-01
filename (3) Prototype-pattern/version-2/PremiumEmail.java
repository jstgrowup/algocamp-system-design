public class PremiumEmail extends Email {
    // Only the NEW fields live here. sender, reciever, subject and body already exist
    // in Email, so they must not be declared again (a second copy would just shadow them).
    private String cc;
    private String bcc;

    PremiumEmail(String sender, String reciever, String subject, String body, String cc, String bcc) {
        // A child constructor must call a parent constructor first. Email has no
        // no-argument constructor, so we have to say which one to use.
        super(sender, reciever, subject, body);
        this.cc = cc;
        this.bcc = bcc;
    }
    // Copy Constructor taking a PremiumEmail, so cc and bcc are copied too.
    // When you pass a PremiumEmail, Java picks this one because it is the closest match.
    PremiumEmail(PremiumEmail premiumEmail) {
        super(premiumEmail); // copies the parent's fields
        this.cc = premiumEmail.cc;
        this.bcc = premiumEmail.bcc;
    }

    @Override
    public PremiumEmail copy() {
        return new PremiumEmail(this);
    }
    public String getCc() {
        return cc;
    }
    public void setCc(String cc) {
        this.cc=cc;
    }
    public String getBcc() {
        return bcc;
    }
    public void setBcc(String bcc) {
        this.bcc=bcc;
    }
}
