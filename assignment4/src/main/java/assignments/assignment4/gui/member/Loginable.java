package assignments.assignment4.gui.member; //package assignment4.gui.member

public interface Loginable {    //interface loginable
    boolean login(String id, String password);
    void logout();
    String getPageName();

}
