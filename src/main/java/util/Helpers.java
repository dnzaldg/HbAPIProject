package util;

public class Helpers {
    public static String generateString(int length){
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<length;i++){
            sb.append((char) (Math.random() * 26 + 'a'));
        }
        return sb.toString();

    }
}
