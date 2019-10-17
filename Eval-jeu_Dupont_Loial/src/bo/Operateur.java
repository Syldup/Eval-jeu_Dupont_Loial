package bo;

import java.util.*;

public class Operateur {
    private Stack<String> pile;
    private List<String> opUnaire;
    private List<String> opBinaire;

    public Operateur () {
        opUnaire = new ArrayList<String>();
        opUnaire.add("inv");
        opUnaire.add("rac");

        opBinaire = new ArrayList<String>();
        opBinaire.add("+");
        opBinaire.add("-");
        opBinaire.add("*");
        opBinaire.add("/");

        pile = getExpretion(2);
    }

    public int getRmdInt(int min, int max) {
        return (int) (Math.random() * (max-min) + min);
    }
    public int getRmdLong(long min, long max, int modulo) {
        int tmp = (int) (Math.random() * (max-min) + min);
        return tmp - tmp%modulo;
    }

    public double getRmdDouble(int min, int max, int decimal, int modulo) {
        int factor = (int) Math.pow(10, decimal);
        return (double) (getRmdLong(min* factor, max* factor, modulo)) / factor;
    }

    public double getRmdNumber(int type) {
        switch (type) {
            case 0: return getRmdDouble(-10, 10, 0, 1);
            case 1: return getRmdDouble(-10, 10, 0, 1);
            case 2: return getRmdDouble(-10, 10, 0, 1);
            default: return getRmdDouble(-10, 10, 0, 1);
        }
    }

    public Stack<String> getExpretion(int len) {
        Stack <String> pile = new Stack<String>();
        int nbOp = opUnaire.size() + opBinaire.size();
        while (len > 0) {
            len--;
            pile.add(Double.toString(getRmdNumber(getRmdInt(0, 5))));
            int idx = getRmdInt(0, nbOp);
            if (idx < opUnaire.size()) {
                pile.add(opUnaire.get(idx));
            } else {
                pile.add(Double.toString(getRmdNumber(getRmdInt(0, 5))));
                pile.add(opUnaire.get(idx - opUnaire.size()));
            }
        }
        return pile;
    }

    public void setNextNum(Stack pile) {
        while (opUnaire.contains(pile.peek()) || opBinaire.contains(pile.peek()))
            pile.push(pile.pop());
    }

    public String calculResult() {
        Stack <String> pile = new Stack <String>();
        pile.addAll(this.pile);

        setNextNum(pile);
        double result = Double.parseDouble(pile.pop());

        while (!pile.empty()) {
            if (opUnaire.contains(pile.peek())) {
                switch (pile.pop()) {
                    case "rec":
                        if (result < 0)
                            return "NaN";
                        result = Math.sqrt(result);
                        break;
                    case "inv":
                        if (result == 0)
                            return "NaN";
                        result = 1 / result;
                        break;
                }
            } else if (opBinaire.contains(pile.peek())) {
                setNextNum(pile);
                switch (pile.peek()) {
                    case "+": result += Double.parseDouble(pile.pop()); break;
                    case "-": result -= Double.parseDouble(pile.pop()); break;
                    case "*": result *= Double.parseDouble(pile.pop()); break;
                    case "/":
                        if (result == 0)
                            return "NaN";
                        result /= Double.parseDouble(pile.pop());
                        break;
                }
            } else pile.push(pile.pop());
        }
        return Double.toString(result);
    }

    @Override
    public String toString() {
        Stack <String> pile = new Stack <String>();
        pile.addAll(this.pile);

        StringBuilder sb = new StringBuilder();
        boolean elemente = false;
        boolean requirNb = true;
        while (!pile.empty()) {
            if (requirNb) {
                if (!opUnaire.contains(pile.peek()) && !opBinaire.contains(pile.peek())) {
                    requirNb = false;
                    sb.append(pile.pop());
                } else pile.push(pile.pop());

            } else if (!requirNb && opUnaire.contains(pile.peek())) {
                sb.insert(0, pile.pop()).insert(0, "( ").append(" )");
                elemente = true;
            } else if (!requirNb &&opBinaire.contains(pile.peek())) {
                switch (pile.peek()) {
                    case "+":
                    case "-":
                        sb.append(" ").append(pile.pop()).append(" ");
                        elemente = false;
                        break;
                    case "*":
                    case "/":
                        if (!elemente)
                            sb.insert(0, "( ").append(" )");
                        sb.append(pile.pop());
                        elemente = true;
                        break;
                }
                requirNb = true;
            } else pile.push(pile.pop());
        }
        return sb.toString();
    }

    public void createExpretion() {
        pile = getExpretion(5);
    }
}
