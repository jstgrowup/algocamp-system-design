package prototype_v2;

// The Prototype contract: anything that can copy itself implements copy().
// <T> is the type of object that comes back (Email -> Email, PremiumEmail -> PremiumEmail),
// e.g. Prototype<Email>.
public interface Prototype<T> {
    T copy();
}
