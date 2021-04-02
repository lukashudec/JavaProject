package calendar.dbtry;

public class postcodeApiObject {
    int id;
    String text;
    int number;

    public postcodeApiObject(int id, String text, int number) {
        this.id=id;
        this.text=text;
        this.number=number;
    }

    public void toConsole() {
        System.out.println(id+" : "+text+" : "+number);
    }
}
