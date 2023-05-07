package assignments.assignment3.user;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

public class Member {
    protected String id;
    protected String password;
    protected String nama;
    protected Nota[] notaList = new Nota[0];

    // Constructor of Member class.
    public Member(String nama, String id, String password) {
        this.nama = nama;
        this.id = id;
        this.password = password;
    }

    /**
     * Member authentication method with the given ID and password.
     *
     * @param id -> Member ID to authenticate.
     * @param password -> member password to authenticate.
     * @return true if the ID and password match the instance member, false otherwise.
     */
    public boolean login(String id, String password) {
        return id.equals(this.id) && authenticate(password);
    }

    /**
     * Added a new nota to the NotaList instance member.
     *
     * @param nota Nota object to add.
     */
    public void addNota(Nota nota) {
        // Add the nota to the member's notaList
        Nota[] newNotaList = new Nota[notaList.length + 1];
        System.arraycopy(notaList, 0, newNotaList, 0, notaList.length);
        newNotaList[notaList.length] = nota;
        notaList = newNotaList;
        // Add the nota no NotaManager's notaList
        NotaManager.addNota(nota);
    }

    /**
     * Member authentication method with the given password.
     *
     * @param password -> password member password to authenticate.
     * @return true if the ID and password match the instance member, false otherwise.
     */
    protected boolean authenticate(String password) {
        return this.password.equals(password);
    }

    // Getter methods of Member class.

    public String getNama() {
        return nama;
    }

    public String getId() {
        return id;
    }

    public Nota[] getNotaList() {
        return notaList;
    }
}