/**
 * Write a description of class ResidentialZone here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ResidentialZone extends Zone
{
    public static final int ID = 1;
    public static final int[] MARKERS = { 400, 401, 402, 403, 404, 405, 406, 407, 408 };

    /*
     * ACCESSORS *
     */
    
    public static int count() {
        Integer count = Zone.counts.get((Integer)ID);
        return count == null ? 0 : count;
    }
}
