package example2.better.pawn;

/** Prototype pattern: one expensive creation, seven cheap clones. */
public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Pawn base = new Pawn(0, 1, "white");
        Pawn[] pawns = new Pawn[8];
        pawns[0] = base;
        for (int x = 1; x < 8; x++) {
            pawns[x] = base.clone(x, 1); // same rules, different position
        }
        System.out.println(pawns.length + " pawns created in ~" + (System.currentTimeMillis() - start)
                + "ms (problem version: 8 setups, ~200ms)");
    }
}
