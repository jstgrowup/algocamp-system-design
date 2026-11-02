# Example 2: expensive object creation (Prototype pattern)

Two problems with the same shape: building an object is expensive, and we repeat it for no reason.

**Problem 1: pawns.** A chess board needs 8 pawns per side, each with its own position. Building a `Pawn` is expensive (it loads its movement rules), and every `new Pawn(...)` repeats that work even though all pawns of a colour share the same rules. Only `x`, `y` and colour differ. We can't just loop and stamp out copies either, because each pawn may need its own configuration.

**Problem 2: emails.** Creating an order sends an `Email`. The body is an HTML template (normally a file) filled with the customer's data. A new `Email` per order means reading the same unchanged template again and again.

**Idea:** build one *base* object (do the expensive work once), then create cheap in-memory **clones** of it and change only what differs (the position of a pawn, the data in an email). Copying data already in memory is far cheaper than redoing the file I/O or setup.

**What the code does**
- `Pawn`, `Email`: the problem versions (8 setups, 4 template reads).
- `Main`: runs both and prints the counts.

**Solved in** [`../better/pawn`](../better/pawn/) and [`../better/email`](../better/email/), using a private copy constructor and a `clone()` method.
