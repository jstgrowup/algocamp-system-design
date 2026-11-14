package interface_segregation.example2.better;

/** Small, single-capability interface: implement it only if the device really can do this. */
public interface Stapler {

    void staple(String doc);
}
