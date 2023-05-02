package nazmul.firstfirebaseandroid;

public class MyRow {

    private String note;

    public MyRow(String text) {
        this.note = text;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return note;
    }
}
