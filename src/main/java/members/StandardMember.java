package members;

public class StandardMember extends Member {

    public StandardMember(String memberId, String name) {
        super(memberId, name);
    }

    @Override
    public double getDiscount(double amount) {
        return 0; // no discount
    }

    @Override
    public boolean canAccessExclusive() {
        return false;
    }

    @Override
    public String getMembershipLevel() {
        return "STANDARD";
    }


    
}