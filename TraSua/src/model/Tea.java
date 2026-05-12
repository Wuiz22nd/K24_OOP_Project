package model;

/**
 *
 * @author Minhphat
 */
public abstract class Tea implements Billable {

    private String name;
    private double basePrice;

    private Size size = Size.MEDIUM;
    private SugarLevel sugarLevel = SugarLevel.FULL;
    private IceLevel iceLevel = IceLevel.NORMAL;

    protected Tea(String name, double basePrice) {

        this.name = name;
        this.basePrice = basePrice;
    }

    // ===========================
    // SETTERS
    // ===========================
    public void setSize(Size size) {
        this.size = size;
    }

    public void setSugarLevel(SugarLevel sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public void setIceLevel(IceLevel iceLevel) {
        this.iceLevel = iceLevel;
    }

    // ===========================
    // COST
    // ===========================
    @Override
    public double getCost() {

        return basePrice * size.getPriceMultiplier();
    }

    // ===========================
    // DESCRIPTION
    // ===========================
    @Override
    public String getDescription() {

        return name
                + " ("
                + size
                + ", "
                + sugarLevel
                + ", "
                + iceLevel
                + ")";
    }

    // ===========================
    // INGREDIENTS
    // ===========================
    public abstract String[] getIngredientNames();

    public abstract int[] getIngredientQuantities();

    // ===========================
    // AUTO SCALE INGREDIENT
    // ===========================
    protected int[] scaleIngredients(
        String[] names,
        int[] base
) {

    int[] result = new int[base.length];

    double sizeFactor =
            size.getIngredientMultiplier();

    for (int i = 0; i < base.length; i++) {

        double factor = sizeFactor;

        if (names[i].equals("duong")) {

            factor *= sugarLevel
                    .getIngredientMultiplier();
        }

        if (names[i].equals("da")) {

            factor *= iceLevel
                    .getIngredientMultiplier();
        }

        result[i] =
                (int) (base[i] * factor);
    }

    return result;
}    
    public void consumeIngredients() {

    repository.InventoryRepository repo =
            new repository.InventoryRepository();

    String[] names = getIngredientNames();

    int[] quantities = getIngredientQuantities();

    for (int i = 0; i < names.length; i++) {

        repo.deduct(names[i], quantities[i]);
    }
}

    // ===========================
    // GETTERS
    // ===========================
    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public Size getSize() {
        return size;
    }

    public SugarLevel getSugarLevel() {
        return sugarLevel;
    }

    public IceLevel getIceLevel() {
        return iceLevel;
    }
}