package weekly414;

public class convertDateToBinary {
    public static void main(String[] args) {
        convertDateToBinary obj = new convertDateToBinary();
        System.out.println(obj.convertDateToBinary("1900-01-01"));
    }

    public String convertDateToBinary(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(5, 7);
        String day = date.substring(8);

        return toBinary(year) + "-" + toBinary(month) + "-" + toBinary(day);
    }

    public String toBinary(String decimal) {
        int decimalNo = Integer.parseInt(decimal);

        int quotient = decimalNo;
        String binary = "";
        while (quotient != 0) {
            int reminder = quotient % 2;
            quotient = quotient / 2;
            binary = reminder + binary;
        }

        return binary;
    }

}
