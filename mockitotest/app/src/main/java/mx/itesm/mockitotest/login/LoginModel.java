package mx.itesm.mockitotest.login;

public class LoginModel implements Login.Model
{
    private Login.Presenter presenter;

    public LoginModel(Login.Presenter presenter)
    {
        this.presenter = presenter;
    }
    @Override
    public void verifyUser(String user, String password) {
        if(user.equals("android")&&password.equals("12345"))
           presenter.validUser();
        else
            presenter.data_error();
    }
}
