//an object that keeps the guesses results (bulls and cows)
public class InputGuess {

    private String number;
    private int bulls;
    private int cows;
    private String rand;

    public InputGuess (String num, String r1){
        this.rand = r1;
        this.number = num;
        this.bulls = 0;
        this.cows = 0;
    }

    public void checkBullsAndCows (){
        String s1 = String.valueOf(this.rand);
        char[] r = new char[s1.length()];
        char[] c = new char[this.number.length()];
        for(int i = 0; i<this.number.length();i++){
            c[i] = this.number.charAt(i);
        }
        for(int j=0; j<s1.length(); j++){
            r[j] = s1.charAt(j);
        }

        int countBulls = 0;
        for (int i = 0; i<s1.length(); i++){
            if (r[i] == c[i])
                countBulls++;
        }
        this.bulls = countBulls;

        int countCows = 0;
        for (int i = 0; i<s1.length(); i++){
            for (int j = 0; j<this.number.length(); j++) {
                if (r[i] == c[j] && i != j)
                    countCows++;
            }
        }
        this.cows = countCows;
    }

    public int getBulls(){
        return this.bulls;
    }

    public int getCows(){
        return this.cows;
    }

    public String toString(){
        return "the guess is: " +this.number +"\n bulls: "+this.bulls + "\n cows: "+this.cows+"\n";
    }
}
