package model;

public abstract class ToppingDecorator extends Tea {

    private Tea wrappedTea;

    private String toppingName;

    private double extraPrice;

    // =====================================================
    // CONSTRUCTOR
    // =====================================================
    protected ToppingDecorator(
            Tea tea,
            String toppingName,
            double extraPrice
    ) {

        super("", 0);

        this.wrappedTea = tea;

        this.toppingName = toppingName;

        this.extraPrice = extraPrice;
    }

    // =====================================================
    // COST
    // =====================================================
    @Override
    public double getCost() {

        return wrappedTea.getCost() + extraPrice;
    }

    // =====================================================
    // DESCRIPTION
    // =====================================================
    @Override
    public String getDescription() {

        return wrappedTea.getDescription()
                + " + "
                + toppingName;
    }

    // =====================================================
    // ABSTRACT METHODS
    // =====================================================
    protected abstract String[] getOwnIngredientNames();

    protected abstract int[] getOwnIngredientQuantities();

    // =====================================================
    // INGREDIENT NAMES
    // =====================================================
    @Override
    public String[] getIngredientNames() {

        String[] base =
                wrappedTea.getIngredientNames();

        String[] own =
                getOwnIngredientNames();

        String[] result =
                new String[base.length + own.length];

        System.arraycopy(
                base,
                0,
                result,
                0,
                base.length
        );

        System.arraycopy(
                own,
                0,
                result,
                base.length,
                own.length
        );

        return result;
    }

    // =====================================================
    // INGREDIENT QUANTITIES
    // =====================================================
    @Override
    public int[] getIngredientQuantities() {

        int[] base =
                wrappedTea.getIngredientQuantities();

        int[] own =
                getOwnIngredientQuantities();

        int[] result =
                new int[base.length + own.length];

        System.arraycopy(
                base,
                0,
                result,
                0,
                base.length
        );

        System.arraycopy(
                own,
                0,
                result,
                base.length,
                own.length
        );

        return result;
    }
}