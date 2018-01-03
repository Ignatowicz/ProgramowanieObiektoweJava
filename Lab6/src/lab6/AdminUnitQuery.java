package lab6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class AdminUnitQuery {

    public AdminUnitList src;
    public Predicate<AdminUnit> p = a->true;
    public Comparator<AdminUnit> cmp;

    int limit = Integer.MAX_VALUE;
    int offset = 0;

    /**
     * Ustawia listę jako przetwarzane źródło
     * @param src
     * @return this
     */
    public AdminUnitQuery selectFrom(AdminUnitList src){
        AdminUnitList list = new AdminUnitList();
        list.units = new ArrayList<>(src.units);
        this.src = list;
        return this;
    }

    /**
     *
     * @param pred - ustawia predykat p
     * @return this
     */
    public AdminUnitQuery where(Predicate<AdminUnit> pred){
        this.p = pred;
        return this;
    }

    /**
     * Wykonuje operację p = p and pred
     * @param pred
     * @return this
     */
    public AdminUnitQuery and(Predicate<AdminUnit> pred){

        return this;
    }
    /**
     * Wykonuje operację p = p or pred
     * @param pred
     * @return this
     */
    public AdminUnitQuery or(Predicate<AdminUnit> pred){
        return this;
    }

    /**
     * Ustawia komparator cmp
     * @param cmp
     * @return this
     */
    public AdminUnitQuery sort(Comparator<AdminUnit> cmp){
        return this;
    }

    /**
     * Ustawia limit
     * @param limit
     * @return this
     */
    public AdminUnitQuery limit(int limit){
        return this;
    }
    /**
     * Ustawia offset
     * @param offset
     * @return this
     */
    public AdminUnitQuery offset(int offset){
        return this;
    }

    /**
     * Wykonuje zapytanie i zwraca wynikową listę
     * @return przefiltrowana i opcjonalnie posortowana lista (uwzględniamy także offset/limit)
     */
    public AdminUnitList execute(){
        AdminUnitList aul = new AdminUnitList();

        return aul;
    }
}
