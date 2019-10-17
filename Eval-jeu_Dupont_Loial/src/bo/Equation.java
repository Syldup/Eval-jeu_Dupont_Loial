package bo;

import java.util.*;

public class Equation {
    private String expretion;
    public Equation () { }

    public void createExpretion(int len) {
        StringBuilder sb = new StringBuilder();
        sb.append(Rmd.getNumber(Rmd.getInt(0, 10)));
        while (len > 0) {
            len--;
            Operateur.addRmdTo(sb);
        }
        expretion = sb.toString();
    }

    public String calculResult() {
        Stack <Double> pile = new Stack <>();

        for (String elem : expretion.split("\\s+")) {
            try {
                Operateur op = Operateur.valueOf(elem);
                if (!op.exec(pile)) return "NaN";
            } catch (IllegalArgumentException e) {
                pile.push(Double.valueOf(elem));
            }
        }
        return Double.toString(pile.pop());
    }

    @Override
    public String toString() {
        Stack <String> pile = new Stack <>();
        Stack <Integer> prioritys = new Stack <>();
        for (String elem : expretion.split("\\s+")) {
            try {
                Operateur op = Operateur.valueOf(elem);
                op.trad(pile, prioritys);
            } catch (IllegalArgumentException e) {
                pile.push(elem);
                prioritys.push(5);
            }
        }
        return pile.pop();
    }
}

class Rmd {
    public static int getInt(int min, int max) {
        return (int) (Math.random() * (max-min) + min);
    }

    public static int getLong(long min, long max, int modulo) {
        int tmp = (int) (Math.random() * (max-min) + min);
        return tmp - tmp%modulo;
    }

    public static double getDouble(int min, int max, int decimal, int modulo) {
        int factor = (int) Math.pow(10, decimal);
        return (double) (getLong(min*factor, max*factor, modulo)) / factor;
    }

    public static double getNumber(int type) {
        switch (type) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: return getDouble(0, 10, 0, 1);
            default: return getDouble(0, 10, 2, 1);
        }
    }
}

enum Operateur {
    PLUS("+", 2, 1),
    MOINS("-", 2, 1),
    PRODUIT("*", 2, 2),
    QUOTIENT("/", 2, 3),
    INVERCE("inv", 1, 3),
    RACINE("rac", 1, 4);

    private String symb;
    private int nbParam;
    private int priority;

    Operateur(String symb, int nbParam, int priority) {
        this.symb = symb;
        this.nbParam = nbParam;
        this.priority = priority;
    }

    public static void addRmdTo(StringBuilder sb) {
        Operateur[] op = Operateur.values();
        int idx = Rmd.getInt(0, op.length);
        if (op[idx].nbParam == 2) {
            int nb = Rmd.getInt(0, 10);
            if (nb < 5 )
                sb.insert(0, " ").insert(0, Rmd.getNumber(nb));
            else
                sb.append(" ").append(Rmd.getNumber(nb));
        }
        sb.append(" ").append(op[idx].name());
    }

    private String pop(Stack<String> pile, Stack <Integer> prioritys) {
        if (prioritys.pop() < priority)
            return String.format("( %s )", pile.pop());
        return pile.pop();
    }

    public void trad(Stack<String> pile, Stack <Integer> prioritys) {
        String str;
        if (nbParam == 2)
            str = String.format("%s %s %s", pop(pile, prioritys), symb, pop(pile, prioritys));
        else str = String.format("%s %s", symb, pop(pile, prioritys));
        pile.push(str);
        prioritys.push(priority);
    }

    public boolean exec(Stack<Double> pile) {
        if (pile.size() < nbParam) return false;

        double tmp = 1;
        switch (symb) {
            case "+": pile.push(pile.pop() + pile.pop()); break;
            case "-": pile.push(pile.pop() - pile.pop()); break;
            case "*": pile.push(pile.pop() * pile.pop()); break;
            case "/": tmp = pile.pop();
            case "inv":
                if (pile.peek() == 0) return false;
                pile.push(tmp / pile.pop());
                break;
            case "rac":
                if (pile.peek() < 0) return false;
                pile.push(Math.sqrt(pile.pop()));
                break;
            default:
                return false;
        }
        return true;
    }
}
