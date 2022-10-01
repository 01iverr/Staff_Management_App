package gr.aueb.sweng22.team05.view.HomePage;
public class HomePageViewStub implements HomePageView {
    String inppassword;
    String inpusername;
    private String errorMessage;
    private String errorTitle;
    @Override
    public String gettheinputUsername() { return inpusername; }
    public void settheinputUsername(String usn) {inpusername=usn; }
    @Override
    public String getthepasswd() { return inppassword; }
    public void setthepasswd(String a) {inppassword=a; }
    @Override
    public void showErrorMessage(String title, String message)
    {this.errorTitle = title;this.errorMessage = message; }
    public String getErrorTitle() {
        return errorTitle;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}

