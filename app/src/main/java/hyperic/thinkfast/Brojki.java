package hyperic.thinkfast;

/**
 * Created by Alek Ivanovski on 11/21/2016.
 */

public class Brojki {
    private int broj1;
    private int broj2;
    private int rez;
    private int znak;
    private boolean tocnost;

    public boolean getTocnost(){return tocnost;}

    public Brojki(){
        GenerateNumbers g = new GenerateNumbers();
        int znak = g.generatOpe();
        if(znak == 0){
            int prvSob = g.generateNum() + 1;
            int vtorSob = g.generateNum() + 1;
            int rez;
            boolean tocnost = g.generatePre();
            if(tocnost){
                rez = prvSob + vtorSob;
            }
            else {
                boolean plusmin = g.generatePre();
                if(plusmin){
                    rez = prvSob + vtorSob + (g.generateNum()%5);
                }
                else
                    rez = prvSob + vtorSob - (g.generateNum()%5);
                if(prvSob + vtorSob == rez)
                    tocnost = true;
            }
            broj1 = prvSob;
            broj2 = vtorSob;
            this.rez = rez;
            this.znak = znak;
            this.tocnost = tocnost;
        }
        else if (znak == 1){
            int prvBr = g.generateNum() + 1;
            int vtorBr = g.generateNum() + 1;
            if(prvBr < vtorBr){
                int tmp = vtorBr;
                vtorBr = prvBr;
                prvBr = tmp;
            }
            int rez;
            boolean tocnost = g.generatePre();
            if(tocnost){
                rez = prvBr - vtorBr;
            }
            else {
                boolean plusmin = g.generatePre();
                if(plusmin){
                    rez = prvBr - vtorBr + (g.generateNum()%7);
                }
                else
                    rez = prvBr - vtorBr - (g.generateNum()%7);
                if (prvBr - vtorBr == rez)
                    tocnost = true;
            }
            broj1 = prvBr;
            broj2 = vtorBr;
            this.rez = rez;
            this.znak = znak;
            this.tocnost = tocnost;
        }
        else {
            int prvBr = g.generateNum() % 12;
            int vtorBr = g.generateNum() % 12;
            int rez;
            boolean tocnost = g.generatePre();
            if(tocnost){
                rez = prvBr * vtorBr;
            }
            else {
                boolean plusmin = g.generatePre();
                if(plusmin){
                    rez = (prvBr * vtorBr) + (g.generateNum()%5);
                }
                else
                    rez = (prvBr * vtorBr) - (g.generateNum()%5);
                if(prvBr * vtorBr == rez)
                    tocnost = true;
            }
            broj1 = prvBr;
            broj2 = vtorBr;
            this.rez = rez;
            this.znak = znak;
            this.tocnost = tocnost;
        }
    }
    @Override
    public String toString(){
        String s = new String();
        s += broj1;
        if(znak == 0) s += '+';
        else if(znak == 1) s += '-';
        else s += 'x';
        s += broj2;
        s += '=';
        s += rez;
        return s;
    }
}
