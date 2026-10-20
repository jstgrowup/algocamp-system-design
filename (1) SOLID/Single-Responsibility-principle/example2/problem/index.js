class UserManager {
  add(user) {
    if (user.age < 18) {
      throw new Error("User is not adult");
    }
    console.log("User added");
  }

  delete(user) {
    console.log("User deleted");
  }

  update(user) {
    if (user.age < 18) {
      throw new Error("User is not adult");
    }
    console.log("User updated");
  }

  get(user) {
    console.log("User returned");
  }

  logUserActivity(user) {
    //   This is a violation of SRP
    console.log("User activity logged");
  }
}

module.exports = UserManager;
