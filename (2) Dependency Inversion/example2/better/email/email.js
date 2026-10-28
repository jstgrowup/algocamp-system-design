const fs = require("fs");
const path = require("path");

// BETTER (Prototype pattern): read the template file once, clone the email for each order.
class Email {
  constructor(templateName) {
    console.log(`  reading ${templateName} from disk...`); // expensive, now done only once
    this.template = fs.readFileSync(path.join(__dirname, templateName), "utf8");
    this.body = this.template;
  }

  // Cheap copy, no file read. The template is a string (immutable), so sharing it is safe.
  clone() {
    return Object.assign(Object.create(Object.getPrototypeOf(this)), this);
  }

  populate(data) {
    this.body = this.template.replace(/\{\{(\w+)\}\}/g, (_, key) => data[key]); // only changes this email's body
    return this;
  }

  send(to) {
    console.log(`  sent to ${to}: ${this.body.replace(/\s+/g, " ").trim()}`);
  }
}

module.exports = { Email };
