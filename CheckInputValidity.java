// An object that get a String input/randomized number as field, and know
// to return true if the input that was received is valid.
public class CheckInputValidity {

    private String input;

    public CheckInputValidity(String i){
        this.input = i;
    }

    // return true if theres are duplicates of digit in the input number.
    private boolean checkDuplicates (){
        boolean ans1 = false;
        char[] ch = new char[this.input.length()];
        for (int i = 0; i <  this.input.length(); i++) {
            ch[i] =  this.input.charAt(i);
        }

        for (int i = 0; i < ch.length; i++){
            for(int j = i+1; j < ch.length; j++){
                if (i!=j && ch[i] == ch[j])
                    ans1 = true;
            }
        }
        return ans1;
    }
    //return true if there are 4 digit number in the input number.
    private boolean checkNumDigit (String n){
        boolean ans2 = true;
        if (n.length() != 4)
            ans2 = false;
        return ans2;
    }
    //return true if there are only digit numbers in the input number.
    private boolean checkLetters(){
        boolean ans3 = true;
        for (int i = 0; i <  this.input.length(); i++){
            if( this.input.charAt(i) < '0' ||  this.input.charAt(i) > '9'){
                ans3 = false;
                break;
            }
        }
        return ans3;
    }

    public boolean check(){
        boolean dupRes, onlyNumRes;
        boolean  digitNumRes = false;
        dupRes = checkDuplicates();
        onlyNumRes = checkLetters();
        if (onlyNumRes)
            digitNumRes = checkNumDigit( this.input);
        if ((!dupRes) && (digitNumRes) /*&& (onlyNumRes)*/)
            return true;
        return false;
    }

    public void setInput(String s){
        this.input = s;
    }

}
