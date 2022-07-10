package academy.prog;

public class Checker {

    public boolean passChecker(String pass) {
        char[] passArr = pass.toCharArray();
        if ((passArr.length+1) < 10)
            return false;
        else
            return true;
    }
}
